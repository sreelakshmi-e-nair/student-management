package com.example.demo.StudentManagement.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.StudentManagement.models.StudentModel;
import com.example.demo.StudentManagement.models.StudentResponseModel;
import com.example.demo.StudentManagement.services.StudentService;
import com.example.demo.exceptions.StudentDatabaseException;
import com.example.demo.exceptions.StudentExceptionHandler;
import com.example.demo.exceptions.StudentGeneralException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentControllerTest {

	private MockMvc mockMvc;

	ObjectMapper objectMapper=new ObjectMapper();

	@InjectMocks
	StudentController studentController;

	@Mock
	StudentService studentService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc=MockMvcBuilders.standaloneSetup(studentController).setControllerAdvice(new StudentExceptionHandler()).build();
	}


	@Test
	public void addStudent_Success() throws JsonProcessingException, Exception {

		Mockito.when(studentService.addStudent(Mockito.any(StudentModel.class))).thenReturn(getStudentResponseModelForAddStudent());

		mockMvc.perform(
				post("/api/add/student")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(getStudentModel()))
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(status().is(HttpStatus.OK.value()));

	}


	@Test
	public void addStudent_500_UnexpectedException() throws JsonProcessingException, Exception {

		Mockito.when(studentService.addStudent(Mockito.any(StudentModel.class))).thenThrow(StudentGeneralException.class);

		mockMvc.perform(
				post("/api/add/student")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(getStudentModel()))
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(status().is(HttpStatus.INTERNAL_SERVER_ERROR.value()));
	}

	@Test
	public void getStudent_Success() throws Exception {

		Mockito.when(studentService.getStudent()).thenReturn(getStudentResponseModelForGetStudent());

		mockMvc.perform(
				get("/api/get/student")
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(status().is(HttpStatus.OK.value()));

	}

	@Test
	public void getStudent_500_UnexpectedException() throws Exception {

		Mockito.when(studentService.getStudent()).thenThrow(StudentGeneralException.class);

		mockMvc.perform(
				get("/api/get/student")
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(status().is(HttpStatus.INTERNAL_SERVER_ERROR.value()));

	}



	@Test
	public void getStudent_404_DataNotFound() throws Exception {

		Mockito.when(studentService.getStudent()).thenThrow(NullPointerException.class);

		mockMvc.perform(
				get("/api/get/student")
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(status().is(HttpStatus.NOT_FOUND.value()));

	}

	@Test
	public void getStudent_502_DataBaseNotAvailable() throws Exception {

		Mockito.when(studentService.getStudent()).thenThrow(StudentDatabaseException.class);

		mockMvc.perform(
				get("/api/get/student")
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(status().is(HttpStatus.BAD_GATEWAY.value()));

	}

	@Test
	public void updateStudent_Success() throws JsonProcessingException, Exception {

		Mockito.when(studentService.updateStudent(Mockito.any(StudentModel.class))).thenReturn(getStudentResponseModelForUpdateStudent());

		mockMvc.perform(
				post("/api/update/student")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(getStudentModel()))
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(status().is(HttpStatus.OK.value()));
	}

	@Test
	public void updateStudent_500_UnexpectedException() throws JsonProcessingException, Exception {

		Mockito.when(studentService.updateStudent(Mockito.any(StudentModel.class))).thenThrow(StudentGeneralException.class);

		mockMvc.perform(
				post("/api/update/student")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(getStudentModel()))
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(status().is(HttpStatus.INTERNAL_SERVER_ERROR.value()));

	}

	@Test
	public void updateStudent_404_DataNotFound() throws JsonProcessingException, Exception {

		Mockito.when(studentService.updateStudent(Mockito.any(StudentModel.class))).thenThrow(NullPointerException.class);

		mockMvc.perform(
				post("/api/update/student")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(getStudentModel()))
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(status().is(HttpStatus.NOT_FOUND.value()));

	}


	@Test
	public void deleteStudent_Success() throws Exception {

		Mockito.when(studentService.deleteStudent(Mockito.anyInt())).thenReturn(getStudentResponseModelForDeleteStudent());


		mockMvc.perform(
				delete("/api/delete/student")
				.param("id","129")
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(status().is(HttpStatus.OK.value()));

	}



	@Test
	public void deleteStudent_500_UnexpectedException() throws Exception {

		Mockito.when(studentService.deleteStudent(Mockito.anyInt())).thenThrow(StudentGeneralException.class);


		mockMvc.perform(
				delete("/api/delete/student")
				.param("id","129")
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(status().is(HttpStatus.INTERNAL_SERVER_ERROR.value()));

	}

	@Test
	public void deleteStudent_404_DataNotFound() throws Exception {

		Mockito.when(studentService.deleteStudent(Mockito.anyInt())).thenThrow(NullPointerException.class);


		mockMvc.perform(
				delete("/api/delete/student")
				.param("id","129")
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(status().is(HttpStatus.NOT_FOUND.value()));

	}



	private StudentModel getStudentModel() { 
		StudentModel studentModel=new StudentModel();
		studentModel.setId(129);
		studentModel.setFirstName("kp");
		return studentModel;
	}

	private StudentResponseModel getStudentResponseModelForAddStudent() {
		StudentResponseModel studentResponseModel=new StudentResponseModel();
		studentResponseModel.setResult(getStudentModel());
		studentResponseModel.setMessage("Student Added Successfully!");
		studentResponseModel.setStatusCode(HttpStatus.OK);

		return studentResponseModel;
	}

	private StudentResponseModel getStudentResponseModelForGetStudent() {
		StudentResponseModel studentResponseModel=new StudentResponseModel();
		studentResponseModel.setResult(getStudentModel());
		studentResponseModel.setMessage("Student data fetched successfully!");
		studentResponseModel.setStatusCode(HttpStatus.OK);

		return studentResponseModel;
	}

	private StudentResponseModel getStudentResponseModelForDeleteStudent() {
		StudentResponseModel studentResponseModel=new StudentResponseModel();
		studentResponseModel.setResult(getStudentModel());
		studentResponseModel.setMessage("Student with id 129 deleted successfully!");
		studentResponseModel.setStatusCode(HttpStatus.OK);

		return studentResponseModel;
	}

	private StudentResponseModel getStudentResponseModelForUpdateStudent() {
		StudentResponseModel studentResponseModel=new StudentResponseModel();
		studentResponseModel.setResult(getStudentModel());
		studentResponseModel.setMessage("Student updated Successfully!");
		studentResponseModel.setStatusCode(HttpStatus.OK);

		return studentResponseModel;
	}


}
