package com.example.demo.controller;

import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import com.example.demo.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    private final StudentRepository studentRepository;

    @GetMapping("")
    public List<Student> getStudent() {
        return studentService.getStudent();
    }

    @GetMapping(path ="{studentId}")
    public Student findById(@PathVariable("studentId") Long id){
        return studentService.findById(id);
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {

        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException("student with id " + studentId + " does not exist");
        } else {
            studentRepository.deleteById(studentId);
        }
    }

    @PutMapping
    public void updateStudent(@RequestBody Student student) {
        studentRepository.save(student);
    }


    @GetMapping("/name")
    public List<Student> getStudentsByName(){
        return studentService.getOneByName();
    }
}
