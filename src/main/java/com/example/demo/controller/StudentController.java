package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;

@RestController
public class StudentController {
	@Autowired
	StudentRepository repo;
//Get All the Students
	//localhost:8080/Students

	@GetMapping("/student")
	public List<Student>getAllStudents(){
		List<Student>students=repo.findAll();
		return students;
	}
	@PostMapping("/studentId")
	public Student getStudent(@RequestBody Student student) {
	    return repo.findByRollNo(student.getRollNo()).get();
	}

	@PostMapping("/student/add")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Student createStudent(@RequestBody Student student) {
	repo.save(student);
	return student;
	
	}
	
	@PutMapping("/student/update/{id}")
	public Student updateStudents(@PathVariable int id ) {
		Student student = repo.findById(id).get();
		student.setName("Veer");
		student.setPercentage(10);
		repo.save(student);
		return student;
	}
	 @DeleteMapping("student/delete")
	public String removeStudent(@RequestParam int id ) {
		 Student student = repo.findById(id).get();
		 repo.delete(student);
		 return "Student Deleted Successfully";
		 
		 
		 
	 }
}
