package com.ups.oop.service;

import com.ups.oop.dto.PersonDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {
    private List<PersonDTO> personDTOList = new ArrayList<>();

    public ResponseEntity createPerson(PersonDTO personDTO) {
        boolean wasFound = findPerson(personDTO.getId());
        if(wasFound) {
            String errorMessage = "Person with id " + personDTO.getId() + " already exists.";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }else {
            personDTOList.add(personDTO);
            return ResponseEntity.status(HttpStatus.OK).body(personDTO);
        }
    }

    private boolean findPerson(String id) {
        for (PersonDTO personDTO : personDTOList) {
            if (id.equalsIgnoreCase(personDTO.getId())) {
                return true;
            }
        }
        return false;
    }

    public ResponseEntity getAllPeople() {
        if(personDTOList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person List not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(personDTOList);
    }

    public ResponseEntity getPersonById(String id) {
        for (PersonDTO per : personDTOList) {
            if (id.equalsIgnoreCase(per.getId())) {
                return ResponseEntity.status(HttpStatus.OK).body(per);
            }
        }
        String errorMessage = "Person with id " + id + " not found.";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    private int findIndexById(String id){
        int index = 0;
        for(PersonDTO p : personDTOList){
            if(id.equalsIgnoreCase(p.getId())){
             return index;
            }
            index++;
        }
        return -1;
    }
    public ResponseEntity updatePerson(PersonDTO personDTO){
        int updateIndex = findIndexById(personDTO.getId());
            if(updateIndex != -1) {
                personDTOList.set(updateIndex, personDTO);
                return ResponseEntity.status(HttpStatus.OK).body(personDTO);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person with id " + personDTO.getId() + " doesn't exits");
        }

    public ResponseEntity detelePersonById(String id) {
        String message = "Person with id " + id;
        for (PersonDTO per : personDTOList) {
            if (id.equalsIgnoreCase(per.getId())) {
                personDTOList.remove(per);
                return ResponseEntity.status(HttpStatus.OK).body(message + " removed successufuly");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message + " not found");
    }
}
