package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public List<Student> getStudent() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        // TODO: What exactly optional is?
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
    }

    public Student findById(Long id) {
        Optional<Student> optional = studentRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new IllegalStateException("No such Field");
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException("student with id " + studentId + " does not exist");
        } else {
            studentRepository.deleteById(studentId);
        }
    }


    //  @Transactional(readOnly = false)  TODO:  What does it do?
 /*  public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("No student with id " + studentId));
        if (name != null && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }
        if (email != null && !Objects.equals(student.getName(), name)) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
            studentRepository.save(student);
        }
    }

   */

    public List<Student> getByName() {
        return studentRepository.findAllByName();
    }

    public List<Student> getOneByName() {
        return studentRepository.findAllByName("Quvonchbek");
    }
}
