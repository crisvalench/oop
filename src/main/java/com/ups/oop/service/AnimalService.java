package com.ups.oop.service;

import com.ups.oop.dto.Animal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnimalService {
    private List<Animal> animalList = new ArrayList<>();

    public ResponseEntity createAnimal(Animal animal) {
        boolean wasFound = findAnimal(animal.getId());
        if (wasFound) {
            String errorMessage = "Animal with id " + animal.getId() + " already exists.";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        } else {
            animalList.add(animal);
            return ResponseEntity.status(HttpStatus.OK).body(animal);
        }
    }

        private boolean findAnimal (String id){
            for (Animal animal : animalList) {
                if (id.equalsIgnoreCase(animal.getId())) {
                    return true;
                }
            }
            return false;
        }

    public ResponseEntity getAllAnimals() {
        if(animalList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Animal List not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(animalList);
    }

    public ResponseEntity getAnimalById(String id) {
        for (Animal anim : animalList) {
            if (id.equalsIgnoreCase(anim.getId())) {
                return ResponseEntity.status(HttpStatus.OK).body(anim);
            }
        }
        String errorMessage = "Animal with id " + id + " not found.";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    private int findIndexById(String id){
        int index = 0;
        for(Animal a : animalList){
            if(id.equalsIgnoreCase(a.getId())){
                return index;
            }
            index++;
        }
        return -1;
    }

    public ResponseEntity updateAnimal(Animal animal){
        int updateIndex = findIndexById(animal.getId());
        if(updateIndex != -1) {
            animalList.set(updateIndex, animal);
            return ResponseEntity.status(HttpStatus.OK).body(animal);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Animal with id " + animal.getId() + " doesn't exits");
    }

    public ResponseEntity deteleAnimalById(String id) {
        String message = "Animal with id " + id;
        for (Animal anim : animalList) {
            if (id.equalsIgnoreCase(anim.getId())) {
                animalList.remove(anim);
                return ResponseEntity.status(HttpStatus.OK).body(message + " removed successufuly");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message + " not found");
    }
}
