package com.example.springbootFeatureModel.student;

import com.example.springbootFeatureModel.config.exception.EmailAlreadyExistException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {


    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email){
        Student student = studentRepository.findById(studentId).orElseThrow(() ->
                new IllegalStateException("student with id " + studentId + " dose not exists")
        );

        if(name != null && name.length() > 0 && !Objects.equals(student.getName(), name)){
            student.setName(name);
        }

        if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)){
            Optional<Student> studentByEmail = studentRepository.findStudentByEmail(email);
            if(studentByEmail.isPresent()){
                throw new IllegalStateException("Email taken");
            }
            student.setEmail(email);
        }


    }

    public void deleteStudent(Long id){
        boolean exists = studentRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("student with id " + id + " dont exists");
        }

        studentRepository.deleteById(id);
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public Student addNewStudent(StudentDto studentRequest){
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(studentRequest.getEmail());

        if(studentByEmail.isPresent()){
            throw new EmailAlreadyExistException("Email already exist");
        }

        Student student = Student.builder()
                .name(studentRequest.getName())
                .email(studentRequest.getEmail())
                .phone(studentRequest.getPhone())
                .build();

        return studentRepository.save(student);

    }
}
