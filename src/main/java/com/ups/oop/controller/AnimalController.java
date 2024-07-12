package com.ups.oop.controller;

import com.ups.oop.dto.Animal;
import com.ups.oop.service.AnimalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnimalController {
    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    //Create or Post Animal
    @PostMapping("/create-animal")
    public ResponseEntity createAnimal(@RequestBody Animal animal){
        return this.animalService.createAnimal(animal);
    }

    //Read or  Get Animal
    @GetMapping("/get-all-animals")
    public ResponseEntity getAllAnimals(){
        return this.animalService.getAllAnimals();
    }

    // Read or  Get Animal
    @GetMapping("/get-animal")
    public ResponseEntity getAnimalById(@RequestParam String id){
        return this.animalService.getAnimalById(id);
    }

    //Update or Put Animal
    @PutMapping("/update-animal")
    public ResponseEntity updateAnimal(@RequestBody Animal animal){
        return this.animalService.updateAnimal(animal);
    }
    //Delete Animal
    @DeleteMapping("/delete-animal")
    public ResponseEntity deleteAnimal(@RequestParam String id){
        return this.animalService.deteleAnimalById(id);
    }
}
