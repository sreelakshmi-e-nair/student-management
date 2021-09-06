package com.example.demo.StudentManagement.repositories;

import com.example.demo.StudentManagement.models.StudentModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends MongoRepository<StudentModel,Integer> {

}
