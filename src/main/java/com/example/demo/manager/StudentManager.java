package com.example.demo.manager;

import com.example.demo.repository.StudentRepository;
import com.example.demo.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class StudentManager {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentManager(StudentRepository studentRepo) {
        this.studentRepository = studentRepo;
    }

//    public List<Student> findAll() {
//        return studentRepository.findAll();
//    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());

        if (studentOptional.isPresent()){
            throw new IllegalStateException("Email is already taken");
        }

        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean studentExist = studentRepository.existsById(studentId);

        if(!studentExist){
            throw new IllegalStateException("Student with id " + studentId + " doesn't exist");
        }

        studentRepository.deleteById(studentId);
    }

    public void updateStudent(Long studentId, String name, String email
    ) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("student with id " + studentId + " doesn't exist"));

        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }
    }
}
