package com.example.demo.StudentManagement.services;


import com.example.demo.StudentManagement.models.StudentModel;
import com.example.demo.StudentManagement.models.StudentResponseModel;
import org.springframework.stereotype.Service;

public interface StudentService {
    public StudentResponseModel addStudent(StudentModel studentModel);
    public StudentResponseModel getStudent();
    public StudentResponseModel updateStudent(StudentModel studentModel);
    public StudentResponseModel deleteStudent(int id);
}
