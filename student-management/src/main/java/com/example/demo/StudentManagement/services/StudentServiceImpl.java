package com.example.demo.StudentManagement.services;

import com.example.demo.exceptions.StudentDatabaseException;
import com.example.demo.exceptions.StudentGeneralException;
import com.example.demo.StudentManagement.models.StudentModel;
import com.example.demo.StudentManagement.models.StudentResponseModel;
import com.example.demo.StudentManagement.repositories.StudentRepository;
import com.mongodb.MongoException;
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
            throw new StudentGeneralException(HttpStatus.INTERNAL_SERVER_ERROR,"Something went wrong!");

        }

        return studentResponseModel;
    }

@Override
    public StudentResponseModel getStudent(){
    StudentResponseModel responseModel=new StudentResponseModel();

        try{
            List<StudentModel> studentDataList=  studentRepository.findAll();
            if(studentDataList.size()==0){

                throw new NullPointerException();
            }
            else{
                responseModel.setMessage("Student data fetched successfully!");
                responseModel.setStatusCode(HttpStatus.OK);
                responseModel.setResult(studentDataList);

            }

        }catch(NullPointerException e){
             throw new NullPointerException();

        }catch (MongoException e){
               throw new StudentDatabaseException("Database not found!");
        }
        catch (Exception e){
                throw new StudentGeneralException(HttpStatus.INTERNAL_SERVER_ERROR,"Something went wrong!");
        }
        return responseModel;
    }


public StudentResponseModel updateStudent(StudentModel studentModel) {
	 StudentResponseModel studentResponseModel=new StudentResponseModel();
     try{
    	 
    	 StudentModel findStudent=null;
    	 findStudent=studentRepository.findById(studentModel.getId()).get();
    	 if(findStudent!=null) {
    		 StudentModel studentData= studentRepository.save(studentModel);
             studentResponseModel.setMessage("Student updated Successfully!");
             studentResponseModel.setStatusCode(HttpStatus.OK);
             studentResponseModel.setResult(studentData);
    	 }
    	 else {
    		 throw new NullPointerException();
    	 }
         
     }catch(NullPointerException e){
         throw new NullPointerException();

    }catch(Exception e)
     {
         throw new StudentGeneralException(HttpStatus.INTERNAL_SERVER_ERROR,"Something went wrong!");

     }

     return studentResponseModel;
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
                
            }
            if(studentResponseModel.getResult()==null) {
            	throw new NullPointerException();
            }
         

        }catch(NullPointerException e){
            throw new NullPointerException();

       }catch (Exception e){
        
            throw new StudentGeneralException(HttpStatus.INTERNAL_SERVER_ERROR,"Something went wrong!");
        }
        return studentResponseModel;
    }
}
