package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import com.example.demo.models.StudentResponseModel;
import com.example.demo.exceptions.StudentGeneralException;
import com.example.demo.models.StudentModel;



@Service
public class StudentService{
	
	@Value("${studentapplication.postStudent}")
	private String postUrl;
	
	@Value("${studentapplication.getStudent}")
	private String getUrl;
	
	@Value("${studentapplication.updateStudent}")
	private String updateUrl;
	
	@Value("${studentapplication.deleteStudent}")
	private String deleteUrl;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	 public StudentResponseModel addStudent(StudentModel studentModel) {
		 
		
		 try {
			 
			 
			 HttpEntity<StudentModel> entity=new HttpEntity<>(studentModel);
			
			 ResponseEntity<StudentResponseModel> restData= restTemplate.exchange(postUrl, HttpMethod.POST,entity,StudentResponseModel.class);
			
			 return restData.getBody();
			 
		 }catch(HttpClientErrorException e) {
			 throw new HttpClientErrorException(e.getStatusCode());
		 }
		 catch(Exception e) {
			
			 throw new StudentGeneralException(HttpStatus.INTERNAL_SERVER_ERROR,"Something went wrong!");
			 
		 }
		
	 }

	 public StudentResponseModel getStudent() {
		 try {
			 return restTemplate.exchange(getUrl, HttpMethod.GET, null, StudentResponseModel.class).getBody();
			 
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
			 return restTemplate.exchange(updateUrl, HttpMethod.POST,entity,StudentResponseModel.class).getBody();
			 
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
			return restTemplate.exchange(deleteUrl+id, HttpMethod.DELETE,entity,StudentResponseModel.class).getBody();
		 }catch(HttpClientErrorException e) {
			 throw new HttpClientErrorException(e.getStatusCode());
		 }
		 catch(Exception e) {
			 throw new StudentGeneralException(HttpStatus.INTERNAL_SERVER_ERROR,"Something went wrong!");
		 }
		
	 }
	 
	 
}
