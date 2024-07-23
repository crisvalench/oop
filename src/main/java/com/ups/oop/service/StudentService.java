package com.ups.oop.service;

import com.ups.oop.dto.PersonDTO;
import com.ups.oop.dto.StudentDTO;
import com.ups.oop.entity.Person;
import com.ups.oop.entity.Student;
import com.ups.oop.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public ResponseEntity createStudent(StudentDTO studentDTO) {

        String studentId = studentDTO.getId();

        Optional<Student> studentOptional = studentRepository.findByStudentId(studentId);

        if (studentOptional.isPresent()) {
            String errorMessage = "Person with id " + studentId + " already exists.";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        } else {
            if (studentDTO.getName().contains(" ")) {
                Person student = new Person();
                student.setPersonId(studentId);
                String[] nameStrings = studentDTO.getName().split(" ");
                String name = nameStrings[0];
                String lastname = nameStrings[1];
                student.setName(name);
                student.setLastname(lastname);
               // studentRepository.save(student);
                return ResponseEntity.status(HttpStatus.OK).body(student);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Person name must contain two strings separated by a whitespace");
            }
        }
    }

        public ResponseEntity getAllStudents() {

            Iterable<Student> studentIterable = studentRepository.findAll();
            List<StudentDTO> studentList = new ArrayList<>();

            for(Student s : studentIterable){
                StudentDTO student = new StudentDTO(s.getStudentId(), s.getName(),s.getLastname());
                studentList.add(student);
            }

            if(studentList.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student List not found");
            }
            return ResponseEntity.status(HttpStatus.OK).body(studentList);
        }

    }

}
