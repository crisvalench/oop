package com.ups.oop.controller;

import com.ups.oop.dto.PersonDTO;
import com.ups.oop.dto.StudentDTO;
import com.ups.oop.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {
    private final StudentService studentService; //Inyeccion de dependecia mediante un constructor

    public StudentController(StudentService personService) {
        this.studentService = personService;
    }

    @PostMapping("/student")
    public ResponseEntity createStudent(@RequestBody StudentDTO studentDTO){
        return this.studentService.createStudent(studentDTO);
    }

    @GetMapping("/get-all-students")
    public ResponseEntity getAllPeople(){
        return this.studentService.getAllStudents();
    }

    @GetMapping("/get-student")
    public ResponseEntity getPersonById(@RequestParam String id){
        return this.studentService.getStudentById(id);
    }

    @PutMapping("/update-student")
    public ResponseEntity updatePerson(@RequestBody StudentDTO studentDTO){
        return this.studentService.updateStudent(studentDTO);
    }

    @DeleteMapping("/remove-student")
    public ResponseEntity deleteStudent(@RequestParam String id){
        return this.studentService.deteleStudentById(id);
    }

}