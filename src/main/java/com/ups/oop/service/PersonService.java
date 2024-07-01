package com.ups.oop.service;

import com.ups.oop.controller.PersonController;
import com.ups.oop.dto.Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ClientHttpResponseDecorator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {
    private List<Person> personList = new ArrayList<>();

    public ResponseEntity createPerson(Person person) {
        boolean wasFound = findPerson(person.getId());
        if(wasFound) {
            String errorMessage = "Person with id " + person.getId() + " already exists.";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }else {
            personList.add(person);
            return ResponseEntity.status(HttpStatus.OK).body(person);
        }
    }

    private boolean findPerson(String id) {
        for (Person person : personList) {
            if (id.equalsIgnoreCase(person.getId())) {
                return true;
            }
        }
        return false;
    }

    public ResponseEntity getAllPeople() {
        if(personList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person List not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(personList);
    }

    public ResponseEntity getPersonById(String id) {
        for (Person per : personList) {
            if (id.equalsIgnoreCase(per.getId())) {
                return ResponseEntity.status(HttpStatus.OK).body(per);
            }
        }
        String errorMessage = "Person with id " + id + " not found.";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    private int findIndexById(String id){
        int index = 0;
        for(Person p : personList){
            if(id.equalsIgnoreCase(p.getId())){
             return index;
            }
            index++;
        }
        return -1;
    }
    public ResponseEntity updatePerson(Person person){
        int updateIndex = findIndexById(person.getId());
            if(updateIndex != -1) {
                personList.set(updateIndex, person);
                return ResponseEntity.status(HttpStatus.OK).body(person);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person with id " + person.getId() + " doesn't exits");
        }

    public ResponseEntity detelePersonById(String id) {
        String message = "Person with id " + id;
        for (Person per : personList) {
            if (id.equalsIgnoreCase(per.getId())) {
                personList.remove(per);
                return ResponseEntity.status(HttpStatus.OK).body(message + " removed successufuly");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message + " not found");
    }
}
