package com.example.demo.service;

import com.example.demo.manager.StudentManager;
import com.example.demo.student.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

//@Component
@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentManager studentManager;

    @Autowired
    public StudentService(StudentRepository studentRepository, StudentManager studentManager) {
        this.studentRepository = studentRepository;
        this.studentManager = studentManager;
    }

    public List<com.example.demo.student.Student> getStudents() {
        return studentManager.getAll();
    }

    public void addNewStudent(com.example.demo.student.Student student) {
        studentManager.addNewStudent(student);
    }

    public void deleteStudent(Long studentId) {
        studentManager.deleteStudent(studentId);
    }

    @Transactional
    public void updateStudent(
            Long studentId,
            String name,
            String email
    ) {
        studentManager.updateStudent(studentId, name, email);
    }
}
