package com.example.demo.StudentManagement.services;

import com.example.demo.StudentManagement.models.StudentModel;
import com.example.demo.StudentManagement.models.StudentResponseModel;
import com.example.demo.StudentManagement.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    StudentRepository studentRepository;

    @Override
    public StudentResponseModel addStudent(StudentModel studentModel){
        StudentResponseModel studentResponseModel=new StudentResponseModel();
        try{
            StudentModel studentData= studentRepository.save(studentModel);
            studentResponseModel.setMessage("Student Added Successfully!");
            studentResponseModel.setStatusCode(HttpStatus.OK);
            studentResponseModel.setResult(studentData);
        }catch(Exception e)
        {
            studentResponseModel.setMessage("Something went wrong!");
            studentResponseModel.setStatusCode(HttpStatus.BAD_GATEWAY);
            studentResponseModel.setResult(e.getMessage());
        }

        return studentResponseModel;
    }

@Override
    public StudentResponseModel getStudent(){
    StudentResponseModel responseModel=new StudentResponseModel();

        try{
            List<StudentModel> studentDataList=  studentRepository.findAll();
            if(studentDataList.size()==0){
                responseModel.setMessage("No data found!");
                responseModel.setStatusCode(HttpStatus.OK);
                responseModel.setResult("No data found!");
            }
            else{
                responseModel.setMessage("Student data fetched successfully!");
                responseModel.setStatusCode(HttpStatus.OK);
                responseModel.setResult(studentDataList);
            }

        }catch(Exception e){

          responseModel.setMessage("Something went wrong!");
          responseModel.setStatusCode(HttpStatus.BAD_GATEWAY);
          responseModel.setResult(e.getMessage());
        }
        return responseModel;
    }

    @Override
    public StudentResponseModel deleteStudent(int id){
        StudentResponseModel studentResponseModel=new StudentResponseModel();
        try{
            List<StudentModel> studentModel=studentRepository.findAll();
            for (StudentModel student:
                 studentModel) {
                if(student.getId()==id){
                    studentResponseModel.setResult(student);
                    studentRepository.deleteById(id);
                    studentResponseModel.setMessage("Student with id "+student.getId() +" deleted successfully!");
                    studentResponseModel.setStatusCode(HttpStatus.OK);
                }
                else{
                    studentResponseModel.setMessage("Student with this id doesn't exist!");
                    studentResponseModel.setStatusCode(HttpStatus.OK);
                    studentResponseModel.setResult("No result found");
                }
            }

        }catch (Exception e){
            studentResponseModel.setMessage("Something went wrong!");
            studentResponseModel.setStatusCode(HttpStatus.BAD_GATEWAY);
            studentResponseModel.setResult(e.getMessage());
        }
        return studentResponseModel;
    }
}
