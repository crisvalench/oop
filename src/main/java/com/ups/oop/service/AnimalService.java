package com.ups.oop.service;

import com.ups.oop.dto.AnimalDTO;
import com.ups.oop.entity.Animal;
import com.ups.oop.repository.AnimalRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {
    private final AnimalRepository animalRepository;
    private List<AnimalDTO> animalDTOList = new ArrayList<>();

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

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

        Iterable<Animal> animalIterable = animalRepository.findAll();
        List<AnimalDTO> animalsList = new ArrayList<>();

        for(Animal anim : animalIterable){
            AnimalDTO animal = new AnimalDTO();
            animal.setAnimalCode(anim.getName() + "-" + anim.getBreed() + "-" + anim.getColor());
            animal.setWeight(anim.getWeight());
            animal.setHeight(anim.getHeight());
            animal.setLength(anim.getLength());
            animalsList.add(animal);
        }

        if(animalsList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Animal List not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(animalsList);
    }

    public ResponseEntity getAnimalById(String id) {
        Optional<Animal> animalOptional = animalRepository.findById(Long.valueOf(id));

        if(animalOptional.isPresent()){
            Animal animalFound = animalOptional.get();
            AnimalDTO animal = new AnimalDTO(animalFound.getAnimalId(),
                    animalFound.getName() + "-" + animalFound.getBreed() + "-" + animalFound.getColor(),
                    animalFound.getWeight(),
                    animalFound.getHeight(),
                    animalFound.getLength());
            return ResponseEntity.status(HttpStatus.OK).body(animal);
        }else{
            String errorMessage = "Animal with id " + id + " not found.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
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
