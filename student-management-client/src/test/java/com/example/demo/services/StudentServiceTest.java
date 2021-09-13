package com.example.demo.services;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;
import com.example.demo.exceptions.StudentGeneralException;
import com.example.demo.models.StudentModel;
import com.example.demo.models.StudentResponseModel;
import com.mongodb.MongoException;
import org.springframework.web.client.HttpClientErrorException;


@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceTest {

	private String postUrl="http://127.0.0.1:8080/api/add/student";
	private String getUrl="http://127.0.0.1:8080/api/get/student";
	private String updateUrl="http://127.0.0.1:8080/api/update/student";
	private String deleteUrl="http://127.0.0.1:8080/api/delete/student?id=132";

	@InjectMocks
	StudentService studentService;

	
@Mock
	RestTemplate restTemplate;
	
	@Test
	public void addStudent_Success() { 

		ResponseEntity<StudentResponseModel> responseEntity=new ResponseEntity<StudentResponseModel>(getStudentResponseModelForPost(),HttpStatus.OK);
		//to mock @Value in service class
		ReflectionTestUtils.setField(studentService, "postUrl",postUrl);
		
		Mockito.when( restTemplate.exchange(
				ArgumentMatchers.eq(postUrl),
				ArgumentMatchers.eq(HttpMethod.POST),
				 ArgumentMatchers.any(),
				 ArgumentMatchers.<Class<StudentResponseModel>>any(),
				 ArgumentMatchers.<Class<ParameterizedTypeReference>>any()))
				
		.thenReturn(responseEntity);
		
		
		StudentResponseModel studentResponse = studentService.addStudent(getStudentModel());
		assertEquals(getStudentResponseModelForPost().getStatusCode(), (studentResponse.getStatusCode()));
		assertEquals(getStudentResponseModelForPost().getMessage(), (studentResponse.getMessage()));
		assertEquals(((StudentModel)getStudentResponseModelForPost().getResult()).getId(), (((StudentModel)studentResponse.getResult()).getId()));
	
	}
	
	@Test(expected=StudentGeneralException.class)
	public void addStudent_500_UnexpectedException() { 

		//to mock @Value in service class
		ReflectionTestUtils.setField(studentService, "postUrl",postUrl);
		
		Mockito.when( restTemplate.exchange(
				ArgumentMatchers.eq(postUrl),
				ArgumentMatchers.eq(HttpMethod.POST),
				 ArgumentMatchers.any(),
				 ArgumentMatchers.<Class<StudentResponseModel>>any(),
				 ArgumentMatchers.<Class<ParameterizedTypeReference>>any()))
				
		.thenThrow(NullPointerException.class);
		
		//calling method
		StudentResponseModel studentResponse = studentService.addStudent(getStudentModel());
	}
	
	@Test(expected=HttpClientErrorException.class)
	public void addStudent_ClientErrorException() { 

		//to mock @Value in service class
		ReflectionTestUtils.setField(studentService, "postUrl",postUrl);
		
		Mockito.when( restTemplate.exchange(
				ArgumentMatchers.eq(postUrl),
				ArgumentMatchers.eq(HttpMethod.POST),
				 ArgumentMatchers.any(),
				 ArgumentMatchers.<Class<StudentResponseModel>>any(),
				 ArgumentMatchers.<Class<ParameterizedTypeReference>>any()))
				
		.thenThrow(new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR));
		
		//calling method
		StudentResponseModel studentResponse = studentService.addStudent(getStudentModel());
	}
	
	@Test
	public void getStudent_Success() { 

		ResponseEntity<StudentResponseModel> responseEntity=new ResponseEntity<StudentResponseModel>(getStudentResponseModelForGet(),HttpStatus.OK);
		//to mock @Value in service class
		ReflectionTestUtils.setField(studentService, "getUrl",getUrl);
	
		Mockito.when( restTemplate.exchange(
				ArgumentMatchers.eq(getUrl),
				ArgumentMatchers.eq(HttpMethod.GET),
				 ArgumentMatchers.any(),
				 ArgumentMatchers.<Class<StudentResponseModel>>any(),
				 ArgumentMatchers.<Class<ParameterizedTypeReference>>any()))
				
		.thenReturn(responseEntity);
		
		
		StudentResponseModel studentResponse = studentService.getStudent();
		assertEquals(getStudentResponseModelForGet().getStatusCode(), (studentResponse.getStatusCode()));
		assertEquals(getStudentResponseModelForGet().getMessage(), (studentResponse.getMessage()));
		assertEquals(((StudentModel)getStudentResponseModelForGet().getResult()).getId(), (((StudentModel)studentResponse.getResult()).getId()));
		
	}
	

	
	@Test(expected=StudentGeneralException.class)
	public void getStudent_500_UnexpectedException() { 

		//ResponseEntity<StudentResponseModel> responseEntity=new ResponseEntity<StudentResponseModel>(getStudentResponseModelForGet(),HttpStatus.OK);
		//to mock @Value in service class
		ReflectionTestUtils.setField(studentService, "getUrl",getUrl);
	
		Mockito.when( restTemplate.exchange(
				ArgumentMatchers.eq(getUrl),
				ArgumentMatchers.eq(HttpMethod.GET),
				 ArgumentMatchers.any(),
				 ArgumentMatchers.<Class<StudentResponseModel>>any(),
				 ArgumentMatchers.<Class<ParameterizedTypeReference>>any()))
				
		.thenThrow(NullPointerException.class);
		
		
		StudentResponseModel studentResponse = studentService.getStudent();
		
	}
	
	@Test(expected=HttpClientErrorException.class)
	public void getStudent_ClientErrorException() { 

		//to mock @Value in service class
		ReflectionTestUtils.setField(studentService, "getUrl",getUrl);
	
		Mockito.when( restTemplate.exchange(
				ArgumentMatchers.eq(getUrl),
				ArgumentMatchers.eq(HttpMethod.GET),
				 ArgumentMatchers.any(),
				 ArgumentMatchers.<Class<StudentResponseModel>>any(),
				 ArgumentMatchers.<Class<ParameterizedTypeReference>>any()))
				
		.thenThrow(new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR));
		
		
		StudentResponseModel studentResponse = studentService.getStudent();
		
	}
	

	@Test
	public void updateStudent_Success() { 

		ResponseEntity<StudentResponseModel> responseEntity=new ResponseEntity<StudentResponseModel>(getStudentResponseModelForUpdate(),HttpStatus.OK);
		//to mock @Value in service class
		ReflectionTestUtils.setField(studentService, "updateUrl",updateUrl);
		HttpEntity<StudentModel> entity=new HttpEntity<>(getStudentModel());
	
		Mockito.when( restTemplate.exchange(
				ArgumentMatchers.eq(updateUrl),
				ArgumentMatchers.eq(HttpMethod.POST),
				 ArgumentMatchers.any(),
				 ArgumentMatchers.<Class<StudentResponseModel>>any(),
				 ArgumentMatchers.<Class<ParameterizedTypeReference>>any()))
				
		.thenReturn(responseEntity);
		
		
		StudentResponseModel studentResponse = studentService.updateStudent(getStudentModel());
		assertEquals(getStudentResponseModelForUpdate().getStatusCode(), (studentResponse.getStatusCode()));
		assertEquals(getStudentResponseModelForUpdate().getMessage(), (studentResponse.getMessage()));
		assertEquals(((StudentModel)getStudentResponseModelForUpdate().getResult()).getId(), (((StudentModel)studentResponse.getResult()).getId()));
	}
	
	@Test(expected=StudentGeneralException.class)
	public void updateStudent_500_UnexpectedException() { 

		//to mock @Value in service class
		ReflectionTestUtils.setField(studentService, "updateUrl",updateUrl);
	
		Mockito.when( restTemplate.exchange(
				ArgumentMatchers.eq(updateUrl),
				ArgumentMatchers.eq(HttpMethod.POST),
				 ArgumentMatchers.any(),
				 ArgumentMatchers.<Class<StudentResponseModel>>any(),
				 ArgumentMatchers.<Class<ParameterizedTypeReference>>any()))
				
		.thenThrow(NullPointerException.class);
		
		
		StudentResponseModel studentResponse = studentService.updateStudent(getStudentModel());
	}
	
	@Test(expected=HttpClientErrorException.class)
	public void updateStudent_ClientErrorException() { 

		//to mock @Value in service class
		ReflectionTestUtils.setField(studentService, "updateUrl",updateUrl);
	
		Mockito.when( restTemplate.exchange(
				ArgumentMatchers.eq(updateUrl),
				ArgumentMatchers.eq(HttpMethod.POST),
				 ArgumentMatchers.any(),
				 ArgumentMatchers.<Class<StudentResponseModel>>any(),
				 ArgumentMatchers.<Class<ParameterizedTypeReference>>any()))
				
		.thenThrow(new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR));
		
		
		StudentResponseModel studentResponse = studentService.updateStudent(getStudentModel());
	}
	
	@Test
	public void deleteStudent_Success() { 

		ResponseEntity<StudentResponseModel> responseEntity=new ResponseEntity<StudentResponseModel>(getStudentResponseModelForDelete(),HttpStatus.OK);
		//to mock @Value in service class
		ReflectionTestUtils.setField(studentService, "deleteUrl",deleteUrl);
		
		Mockito.when( restTemplate.exchange(
				ArgumentMatchers.anyString(),
				ArgumentMatchers.eq(HttpMethod.DELETE),
				 ArgumentMatchers.any(),
				 ArgumentMatchers.<Class<StudentResponseModel>>any(),
				 ArgumentMatchers.<Class<ParameterizedTypeReference>>any()))
				
		.thenReturn(responseEntity);
		
		
		StudentResponseModel studentResponse = studentService.deleteStudent(132);
		assertEquals(getStudentResponseModelForDelete().getStatusCode(), (studentResponse.getStatusCode()));
		assertEquals(getStudentResponseModelForDelete().getMessage(), (studentResponse.getMessage()));
		assertEquals(((StudentModel)getStudentResponseModelForDelete().getResult()).getId(), (((StudentModel)studentResponse.getResult()).getId()));
	
	}
	
	
	@Test(expected=StudentGeneralException.class)
	public void deleteStudent_500_UnexpectedException() { 

		//to mock @Value in service class
		ReflectionTestUtils.setField(studentService, "deleteUrl",deleteUrl);
		
		Mockito.when( restTemplate.exchange(
				ArgumentMatchers.anyString(),
				ArgumentMatchers.eq(HttpMethod.DELETE),
				 ArgumentMatchers.any(),
				 ArgumentMatchers.<Class<StudentResponseModel>>any(),
				 ArgumentMatchers.<Class<ParameterizedTypeReference>>any()))
				
		.thenThrow(NullPointerException.class);
		
		
		StudentResponseModel studentResponse = studentService.deleteStudent(132);
	
	}
	
	@Test(expected=HttpClientErrorException.class)
	public void deleteStudent_ClientErrorException() { 

		//to mock @Value in service class
		ReflectionTestUtils.setField(studentService, "deleteUrl",deleteUrl);
		
		Mockito.when( restTemplate.exchange(
				ArgumentMatchers.anyString(),
				ArgumentMatchers.eq(HttpMethod.DELETE),
				 ArgumentMatchers.any(),
				 ArgumentMatchers.<Class<StudentResponseModel>>any(),
				 ArgumentMatchers.<Class<ParameterizedTypeReference>>any()))
				
		.thenThrow(new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR));
		
		
		StudentResponseModel studentResponse = studentService.deleteStudent(132);
	
	}

	private StudentModel getStudentModel() {
		StudentModel studentModel=new StudentModel();
		studentModel.setId(132);
		studentModel.setFirstName("ashik");
		return studentModel;
	}
	private StudentResponseModel getStudentResponseModelForPost() {
		StudentResponseModel studentResponse=new StudentResponseModel();
		studentResponse.setResult(getStudentModel());
		studentResponse.setStatusCode(HttpStatus.OK);
		studentResponse.setMessage("Student Added Successfully!");
		return studentResponse;
	}
	
	private StudentResponseModel getStudentResponseModelForGet() {
		StudentResponseModel studentResponse=new StudentResponseModel();
		studentResponse.setResult(getStudentModel());
		studentResponse.setStatusCode(HttpStatus.OK);
		studentResponse.setMessage("Student data fetched successfully!");
		return studentResponse;
	}
	private StudentResponseModel getStudentResponseModelForUpdate() {
		StudentResponseModel studentResponse=new StudentResponseModel();
		studentResponse.setResult(getStudentModel());
		studentResponse.setStatusCode(HttpStatus.OK);
		studentResponse.setMessage("Student updated Successfully!");
		return studentResponse;
	}
	
	private StudentResponseModel getStudentResponseModelForDelete() {
		StudentResponseModel studentResponse=new StudentResponseModel();
		studentResponse.setResult(getStudentModel());
		studentResponse.setStatusCode(HttpStatus.OK);
		studentResponse.setMessage("Student with id 132 deleted successfully!");
		return studentResponse;
	}

}
