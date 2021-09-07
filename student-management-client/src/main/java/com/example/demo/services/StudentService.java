package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import com.example.demo.models.StudentResponseModel;
import com.example.demo.exceptions.StudentGeneralException;
import com.example.demo.models.StudentModel;



@Service
public class StudentService{

	private RestTemplate restTemplate;
	
	@Autowired
	 public StudentService(RestTemplateBuilder restTemplateBuilder) {
		
		 this.restTemplate=restTemplateBuilder.build();
		 
	}
	
	 public StudentResponseModel addStudent(StudentModel studentModel) {
		 try {
			 HttpEntity<StudentModel> entity=new HttpEntity<>(studentModel);
			 return restTemplate.exchange("http://localhost:8080/api/add/student", HttpMethod.POST,entity,StudentResponseModel.class).getBody();
			 
		 }catch(HttpClientErrorException e) {
			 throw new HttpClientErrorException(e.getStatusCode());
		 }
		 catch(Exception e) {
			 throw new StudentGeneralException(HttpStatus.INTERNAL_SERVER_ERROR,"Something went wrong!");
		 }
		
	 }

	 public StudentResponseModel getStudent() {
		 try {
			 return restTemplate.exchange("http://localhost:8080/api/get/student", HttpMethod.GET, null, StudentResponseModel.class).getBody();
			 
		 }catch(HttpClientErrorException e) {
			 throw new HttpClientErrorException(e.getStatusCode());
		 }
		 catch(Exception e) {
			 throw new StudentGeneralException(HttpStatus.INTERNAL_SERVER_ERROR,"Something went wrong!");
		 }
		
		 
	}
	 
	 public StudentResponseModel updateStudent(StudentModel studentModel) {
		 try {
			 HttpEntity<StudentModel> entity=new HttpEntity<>(studentModel);
			 return restTemplate.exchange("http://localhost:8080/api/update/student", HttpMethod.POST,entity,StudentResponseModel.class).getBody();
			 
		 }catch(HttpClientErrorException e) {
			 throw new HttpClientErrorException(e.getStatusCode());
		 }
		 catch(Exception e) {
			 throw new StudentGeneralException(HttpStatus.INTERNAL_SERVER_ERROR,"Something went wrong!");
		 }
		
	 }
	 
	 public StudentResponseModel deleteStudent(int id) {
		 try {
			 HttpEntity<Integer> entity=new HttpEntity<>(id);
			 return restTemplate.exchange("http://localhost:8080/api/delete/student?id="+id, HttpMethod.DELETE,entity,StudentResponseModel.class).getBody();
		 }catch(HttpClientErrorException e) {
			 throw new HttpClientErrorException(e.getStatusCode());
		 }
		 catch(Exception e) {
			 throw new StudentGeneralException(HttpStatus.INTERNAL_SERVER_ERROR,"Something went wrong!");
		 }
		
	 }
	 
	 
}
