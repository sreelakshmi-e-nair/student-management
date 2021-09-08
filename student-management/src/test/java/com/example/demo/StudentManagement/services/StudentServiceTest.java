package com.example.demo.StudentManagement.services;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.StudentManagement.models.StudentModel;
import com.example.demo.StudentManagement.models.StudentResponseModel;
import com.example.demo.StudentManagement.repositories.StudentRepository;
import com.example.demo.exceptions.StudentDatabaseException;
import com.example.demo.exceptions.StudentGeneralException;
import com.mongodb.MongoException;


@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceTest {



	@InjectMocks
	private StudentServiceImpl studentService;


	@Mock
	private StudentRepository studentRepository;


	@Test
	public void addStudent_Success() { 

		Mockito.when(studentRepository.save(Mockito.any(StudentModel.class))).thenReturn(getStudentModel());
		StudentResponseModel studentResponse = studentService.addStudent(getStudentModel());
		assertEquals(HttpStatus.OK, (studentResponse.getStatusCode()));
		assertEquals("Student Added Successfully!", (studentResponse.getMessage()));
		assertEquals(129, ((StudentModel)studentResponse.getResult()).getId()); 

	}

	@Test(expected=StudentGeneralException.class)
	public void addStudent_500_UnexpectedException() {

		Mockito.when(studentRepository.save(Mockito.any(StudentModel.class))).thenThrow(NullPointerException.class);
		StudentResponseModel studentResponse = studentService.addStudent(getStudentModel());

	}

	@Test
	public void getStudent_Success() { 

		Mockito.when(studentRepository.findAll()).thenReturn((getStudentList()));
		StudentResponseModel studentResponse = studentService.getStudent();
		assertEquals(HttpStatus.OK, (studentResponse.getStatusCode()));
		assertEquals("Student data fetched successfully!", (studentResponse.getMessage())); 
		assertEquals(1, ((List<StudentModel>)studentResponse.getResult()).size()); 
		assertEquals(129, ((List<StudentModel>)studentResponse.getResult()).get(0).getId()); 

	}

	@Test(expected=NullPointerException.class)
	public void getStudent_404_DataNotFound() {

		//passing an empty array.then it will throw null pointer exception as the code we written in serviceimpl
		Mockito.when(studentRepository.findAll()).thenReturn(new ArrayList<>());
		StudentResponseModel studentResponse = studentService.getStudent();

	}

	@Test(expected=StudentGeneralException.class)
	public void getStudent_500_UnexpectedException() {

		Mockito.when(studentRepository.findAll()).thenThrow(StudentGeneralException.class);
		StudentResponseModel studentResponse = studentService.getStudent();

	}

	@Test(expected=StudentDatabaseException.class)
	public void getStudent_502_DataBaseNotAvailable() {

		Mockito.when(studentRepository.findAll()).thenThrow(MongoException.class);
		StudentResponseModel studentResponse = studentService.getStudent();

	}

	@Test
	public void updateStudent_Success() { 

		Mockito.when(studentRepository.findById(Mockito.anyInt())).thenReturn(getStudentModelForUpdate());
		Mockito.when(studentRepository.save(Mockito.any(StudentModel.class))).thenReturn(getStudentModel());
		StudentResponseModel studentResponse = studentService.updateStudent(getStudentModel());
		assertEquals(HttpStatus.OK, (studentResponse.getStatusCode()));
		assertEquals("Student updated Successfully!", (studentResponse.getMessage()));
		assertEquals(129, ((StudentModel)studentResponse.getResult()).getId());

	}

	@Test(expected=NullPointerException.class)
	public void updateStudent_404_DataNotFound() {

		Mockito.when(studentRepository.findById(Mockito.anyInt())).thenReturn(null);
		StudentResponseModel studentResponse = studentService.updateStudent(getStudentModel());

	}

	@Test(expected=StudentGeneralException.class)
	public void updateStudent_500_UnexpectedException() {

		Mockito.when(studentRepository.findById(Mockito.anyInt())).thenThrow(StudentGeneralException.class);
		StudentResponseModel studentResponse = studentService.updateStudent(getStudentModel());

	}

	@Test
	public void deleteStudent_Success() { 

		Mockito.when(studentRepository.findAll()).thenReturn(getStudentList());
		//Mockito.when(studentRepository.deleteById(Mockito.anyInt())).thenReturn();
		StudentResponseModel studentResponse = studentService.deleteStudent(129);
		assertEquals(HttpStatus.OK, (studentResponse.getStatusCode()));
		assertEquals("Student with id 129 deleted successfully!", (studentResponse.getMessage()));
		assertEquals(129, ((StudentModel)studentResponse.getResult()).getId());

	}

	@Test(expected=NullPointerException.class)
	public void deleteStudent_404_DataNotFound() {

		Mockito.when(studentRepository.findAll()).thenReturn(new ArrayList<>());
		StudentResponseModel studentResponse = studentService.deleteStudent(129);

	}

	@Test(expected=StudentGeneralException.class)
	public void deleteStudent_500_UnexpectedException() {

		Mockito.when(studentRepository.findAll()).thenThrow(StudentGeneralException.class);
		StudentResponseModel studentResponse = studentService.deleteStudent(129);

	}



	private Optional<StudentModel> getStudentModelForUpdate() {
		Optional<StudentModel> studentModel=Optional.of(getStudentModel());

		return studentModel;
	}


	private StudentModel getStudentModel() {
		StudentModel studentModel=new StudentModel();
		studentModel.setId(129);
		studentModel.setFirstName("kp");
		return studentModel;
	}



	private List<StudentModel> getStudentList() {
		List<StudentModel> studentModelList=new ArrayList<>();
		studentModelList.add(getStudentModel());
		return studentModelList;
	}

}
