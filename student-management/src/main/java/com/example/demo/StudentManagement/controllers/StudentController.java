package com.example.demo.StudentManagement.controllers;

import com.example.demo.StudentManagement.models.StudentModel;
import com.example.demo.StudentManagement.models.StudentResponseModel;
import com.example.demo.StudentManagement.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    StudentService studentService;

    //Api to post student data
    @PostMapping("/add/student")
    public StudentResponseModel addStudent(@RequestBody StudentModel studentModels){

        return studentService.addStudent(studentModels);
    }

    //Api to get student data
    @GetMapping("/get/student")
    public StudentResponseModel getAllStudent(){
        return studentService.getStudent();
    }
    
    //Api to update student data
    @PostMapping("/update/student")
    public StudentResponseModel updateStudent(@RequestBody StudentModel studentModels){

        return studentService.updateStudent(studentModels);
    }

    //Api to delete a student
    @DeleteMapping("/delete/student")
    public StudentResponseModel deleteStudent(@RequestParam int id){
            return studentService.deleteStudent(id);
    }
}
