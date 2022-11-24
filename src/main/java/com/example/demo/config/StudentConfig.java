package com.example.demo.config;

import com.example.demo.repository.StudentRepository;
import com.example.demo.student.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            com.example.demo.student.Student omraj = new com.example.demo.student.Student(
                    "Omraj",
                    "omraj@gmail.com",
                    LocalDate.of(2000, Month.JANUARY, 5)
            );

            com.example.demo.student.Student riya = new Student(
                    "Shyam",
                    "shyam@gmail.com",
                    LocalDate.of(1999, Month.JANUARY, 5)
            );

            repository.saveAll(
                    List.of(omraj, riya)
            );

        };
    }
}
