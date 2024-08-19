package com.ups.oop.service;

import com.ups.oop.dto.StudentDTO;
import com.ups.oop.entity.Student;
import com.ups.oop.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public ResponseEntity createStudent(StudentDTO studentDTO) {

        String studentId = studentDTO.getStudent_code();

        Optional<Student> studentOptional = studentRepository.findByStudentId(studentId);

        if (studentOptional.isPresent()) {
            String errorMessage = "Student with id " + studentId + " already exists.";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        } else {
            Student student = new Student();
            student.setStudentId(studentId);
            student.setName(studentDTO.getName());
            student.setLastname(studentDTO.getLastname());
            studentRepository.save(student);
            return ResponseEntity.status(HttpStatus.OK).body(student);
        }
    }

    public ResponseEntity getAllStudents() {

        Iterable<Student> studentIterable = studentRepository.findAll();
        List<StudentDTO> studentList = new ArrayList<>();

        for (Student s : studentIterable) {
            StudentDTO student = new StudentDTO(s.getStudentId(), s.getName(), s.getLastname());
            studentList.add(student);
        }

        if (studentList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student List not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(studentList);
    }

    public ResponseEntity getStudentById(String studentId) {

        Optional<Student> studentOptional = studentRepository.findByStudentId(studentId);

        if (studentOptional.isPresent()) {
            //if record was found
            Student studentFound = studentOptional.get();
            StudentDTO student = new StudentDTO(studentFound.getStudentId(),
                    studentFound.getName(), studentFound.getLastname());
            return ResponseEntity.status(HttpStatus.OK).body(student);

        } else {
            //if record wasn't found
            String errorMessage = "Person with id " + studentId + " not found.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    public ResponseEntity updateStudent(StudentDTO studentDTO) {
        //int updateIndex = findIndexById(personDTO.getId());
        String studentId = studentDTO.getStudent_code();

        Optional<Student> studentOptional = studentRepository.findByStudentId(studentId);

        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            student.setStudentId(studentId);
            student.setName(studentDTO.getName());
            student.setLastname(studentDTO.getLastname());
            studentRepository.save(student);
            return ResponseEntity.status(HttpStatus.OK).body(student);

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student with id " + studentId + " doesn't exits");
        }
    }

    public ResponseEntity deteleStudentById(String id) {

        String message = "Student with id " + id;

        Optional<Student> studentOptional = studentRepository.findByStudentId(id);

        if(studentOptional.isPresent()) {
            studentRepository.delete(studentOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body(message + " removed successufuly");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message + " not found");
        }
    }

}

