package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;
import java.time.Period;


@Data
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor // TODO: If I change it with @RequiredArgsConstructor DemoApp don't run Why?
public class Student {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "student_sequence"
    )
    private Long id;


    @Column(nullable = false)
    private String name;

    @Column
    private String email;

    @Column
    private LocalDate dob;

    @Transient
    private Integer age;

    public Student(String name, String email, LocalDate dob) {
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    public Integer getAge() {
        return Period.between(dob, LocalDate.now()).getYears();
    }
}
