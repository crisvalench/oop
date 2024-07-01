package com.ups.oop.controller;

import com.ups.oop.PooApplication;
import com.ups.oop.dto.Person;
import com.ups.oop.service.PersonService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.util.List;

@RestController
public class PersonController {
    private final PersonService personService; //Inyeccion de dependecia mediante un constructor

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/person")
    public ResponseEntity createPerson(@RequestBody Person person){
        return this.personService.createPerson(person);
    }

    @GetMapping("/get-all-people")
    public ResponseEntity getAllPeople(){
        return this.personService.getAllPeople();
    }

    @GetMapping("/get-person")
    public ResponseEntity getPersonById(@RequestParam String id){
        return this.personService.getPersonById(id);
    }

    @PutMapping("/update-person")
    public ResponseEntity updatePerson(@RequestBody Person person){
        return this.personService.updatePerson(person);
    }

    @DeleteMapping("/remove-person")
    public ResponseEntity deletePerson(@RequestParam String id){
        return this.personService.detelePersonById(id);
    }
}
