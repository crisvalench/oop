package com.ups.oop.service;

import com.ups.oop.dto.PersonDTO;
import com.ups.oop.entity.Person;
import com.ups.oop.repository.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    private List<PersonDTO> personDTOList = new ArrayList<>();

    public ResponseEntity createPerson(PersonDTO personDTO) {
        String personId = personDTO.getId();

        Optional<Person> personOptional = personRepository.findByPersonId(personId);

        if (personOptional.isPresent()) {
            String errorMessage = "Person with id " + personId + " already exists.";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }else{
            if(personDTO.getName().contains(" ")) {
                Person person = new Person();
                person.setPersonId(personId);
                String[] nameStrings = personDTO.getName().split(" ");
                String name = nameStrings[0];
                String lastname = nameStrings[1];
                person.setName(name);
                person.setLastname(lastname);
                person.setAge(personDTO.getAge());
                personRepository.save(person);
                return ResponseEntity.status(HttpStatus.OK).body(person);
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Person name must contain two strings separated by a whitespace");
            }
        }
    }


    public ResponseEntity getAllPeople() {
        List<PersonDTO> peopleList = getPeople();

//        for(Person p : personIterable){
//            PersonDTO person = new PersonDTO(p.getPersonId(), p.getName() + " " + p.getLastname(), p.getAge());
//            peopleList.add(person);

//      Method 2
////         PersonDTO person = new PersonDTO();
////         person.setId(p.getPersonId());
////         person.setName(p.getName() + " " + p.getLastname());
////         person.setAge(p.getAge());
////         peopleList.add(person);

        if(peopleList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person List not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(peopleList);
    }

    //TemplateController
    public List<PersonDTO> getPeople(){
        Iterable<Person> personIterable = personRepository.findAll();
        List<PersonDTO> peopleList = new ArrayList<>();
        for (Person p : personIterable) {
            PersonDTO person = new PersonDTO(p.getPersonId(), p.getName() + " " + p.getLastname(), p.getAge());
            peopleList.add(person);
        }
        return peopleList;
    }

    public ResponseEntity getPersonById(String personId) {

        //Optional<Person> personOptional = personRepository.findById(Long.valueOf(id));
        Optional<Person> personOptional = personRepository.findByPersonId(personId);

        if(personOptional.isPresent()){
            //if record was found
            Person personFound = personOptional.get();
            PersonDTO person = new PersonDTO(personFound.getPersonId(),
                    personFound.getName() + " " + personFound.getLastname(),
                    personFound.getAge());
            return ResponseEntity.status(HttpStatus.OK).body(person);

        }else{
            //if record wasn't found
            String errorMessage = "Person with id " + personId + " not found.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    public ResponseEntity updatePerson(PersonDTO personDTO) {
        //int updateIndex = findIndexById(personDTO.getId());
        String personId = personDTO.getId();
        Optional<Person> personOptional = personRepository.findByPersonId(personId);
        if (personOptional.isPresent()) {
            Person person = personOptional.get();

            if (personDTO.getName().contains(" ")) {
                person.setPersonId(personId);
                String[] nameStrings = personDTO.getName().split(" ");
                String name = nameStrings[0];
                String lastname = nameStrings[1];
                person.setName(name);
                person.setLastname(lastname);
                person.setAge(personDTO.getAge());
                personRepository.save(person);
                return ResponseEntity.status(HttpStatus.OK).body(person);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Person name must contain two strings separated by a whitespace");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person with id " + personId + " doesn't exits");
        }
    }

    public ResponseEntity detelePersonById(String id) {
        String message = "Person with id " + id;
        Optional<Person> personOptional = personRepository.findByPersonId(id);
        if(personOptional.isPresent()) {
            personRepository.delete(personOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body(message + " removed successufuly");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message + " not found");
        }
    }
    }
