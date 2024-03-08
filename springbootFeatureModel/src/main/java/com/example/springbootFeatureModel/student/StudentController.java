package com.example.springbootFeatureModel.student;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


import java.util.List;

@RestController()
@RequestMapping(path = "api/v1/student")
public class StudentController {



    private final StudentService studentService;

    private final  StudentRepository studentRepository;


    @Autowired
    public StudentController(StudentService studentService, StudentRepository studentRepository) {
        this.studentService = studentService;
        this.studentRepository = studentRepository;
    }

    @GetMapping("/index")
    public List<Student> getStudents(){
        return studentService.getStudents();
    }

    @PostMapping("/create")
    public ResponseEntity<Student> addNewStudent(@RequestBody @Valid StudentDto studentRequest){
        return new ResponseEntity<>(studentService.addNewStudent(studentRequest), HttpStatus.OK);
    }


    @PutMapping(path = "/update/{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email
    ){
        studentService.updateStudent(studentId, name, email);
    }

    @DeleteMapping(path = "/delete/{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long id){
        studentService.deleteStudent(id);
    }



}
