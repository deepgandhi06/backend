package com.example.StudentManagementSystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.StudentManagementSystem.entity.Student;
import com.example.StudentManagementSystem.repository.StudentRepository;
@CrossOrigin("*")
@RestController
public class StudentController {
	
	@Autowired
	StudentRepository repo;
	@GetMapping("/students")
	public List<Student> getAllStudents(){
		List<Student>students = repo.findAll();
		
		return students;
	}
	
	@GetMapping("/students/{id}")
	public Student getStudent(@PathVariable int id) {
		Student student = repo.findById(id).get();
		return student;
	}
	
	@PostMapping("students/add")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void CreateStudent(@RequestBody Student student) {
		repo.save(student);
	}
	
	@PutMapping("students/update/{id}")
		public Student UpdateStudent(@PathVariable int id, @RequestBody Student student) {
		 	Optional<Student> optionalStudent = repo.findById(id);
		 	if(optionalStudent.isPresent()) {
		 		Student updatestudent = optionalStudent.get();
		 		updatestudent.setName(student.getName());
		 		updatestudent.setBranch(student.getBranch());
		 		updatestudent.setPercentage(student.getPercentage());
		 		return repo.save(updatestudent);
		 	}
		 	else {
		 		throw new RuntimeException("Student not found with id: " + id);
		 	}
		  
	}	
	
	@DeleteMapping("students/delete/{id}")
	
	public void DeleteStudent(@PathVariable int id) {
		Student student = repo.findById(id).get();
		repo.delete(student);
	}
}
