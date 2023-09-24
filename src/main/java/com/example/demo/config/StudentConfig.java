package com.example.demo.config;

import com.example.demo.repository.StudentRepository;
import com.example.demo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student quvonchbek = new Student(
                    "Quvonchbek",
                    "quvonchbekibragimov1@gmail.com",
                    LocalDate.of(2004, JULY, 2)
            );
            Student abdu = new Student(
                    "Abdulbosit",
                    "jumabayevs@gmail.com",
                    LocalDate.of(2006, APRIL, 24)
            );

            repository.saveAll(
                    List.of(quvonchbek, abdu)
            );

        };
    }
}
