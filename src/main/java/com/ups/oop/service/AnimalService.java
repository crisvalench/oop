package com.ups.oop.service;

import com.ups.oop.dto.AnimalDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnimalService {
    private List<AnimalDTO> animalDTOList = new ArrayList<>();

    public ResponseEntity createAnimal(AnimalDTO animalDTO) {
        boolean wasFound = findAnimal(animalDTO.getId());
        if (wasFound) {
            String errorMessage = "Animal with id " + animalDTO.getId() + " already exists.";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        } else {
            animalDTOList.add(animalDTO);
            return ResponseEntity.status(HttpStatus.OK).body(animalDTO);
        }
    }

        private boolean findAnimal (String id){
            for (AnimalDTO animalDTO : animalDTOList) {
                if (id.equalsIgnoreCase(animalDTO.getId())) {
                    return true;
                }
            }
            return false;
        }

    public ResponseEntity getAllAnimals() {
        if(animalDTOList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Animal List not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(animalDTOList);
    }

    public ResponseEntity getAnimalById(String id) {
        for (AnimalDTO anim : animalDTOList) {
            if (id.equalsIgnoreCase(anim.getId())) {
                return ResponseEntity.status(HttpStatus.OK).body(anim);
            }
        }
        String errorMessage = "Animal with id " + id + " not found.";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    private int findIndexById(String id){
        int index = 0;
        for(AnimalDTO a : animalDTOList){
            if(id.equalsIgnoreCase(a.getId())){
                return index;
            }
            index++;
        }
        return -1;
    }

    public ResponseEntity updateAnimal(AnimalDTO animalDTO){
        int updateIndex = findIndexById(animalDTO.getId());
        if(updateIndex != -1) {
            animalDTOList.set(updateIndex, animalDTO);
            return ResponseEntity.status(HttpStatus.OK).body(animalDTO);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Animal with id " + animalDTO.getId() + " doesn't exits");
    }

    public ResponseEntity deteleAnimalById(String id) {
        String message = "Animal with id " + id;
        for (AnimalDTO anim : animalDTOList) {
            if (id.equalsIgnoreCase(anim.getId())) {
                animalDTOList.remove(anim);
                return ResponseEntity.status(HttpStatus.OK).body(message + " removed successufuly");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message + " not found");
    }
}
