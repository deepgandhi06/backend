package com.example.StudentManagementSystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.StudentManagementSystem.entity.Course;
import com.example.StudentManagementSystem.entity.Student;
import com.example.StudentManagementSystem.repository.CourseRepo;
@CrossOrigin("*")
@RestController
public class CourseController {
	
		@Autowired
		CourseRepo repo;
		
		@GetMapping("/courses")
		public List<Course> allCourse(){
			List<Course> courses = repo.findAll();
			return courses;
		}
		
		@PostMapping("courses/add")
		@ResponseStatus(code = HttpStatus.CREATED)
		public void newCourse(@RequestBody Course course) {
			repo.save(course);
		}
		
		@DeleteMapping("courses/delete/{id}")
		public void removeCourse(@PathVariable int id) {
			Course course = repo.findById(id).get();
			repo.delete(course);
		}
		
		public Course UpdateCourse(@PathVariable int id, @RequestBody Course course) {
			Optional<Course> optionalCourse = repo.findById(id);
			if (optionalCourse.isPresent()) {
		        Course updateCourse = optionalCourse.get();
		        updateCourse.setName(course.getName());
		        updateCourse.setTeacher(course.getTeacher());
		        return repo.save(updateCourse);
		    } else {
		        throw new RuntimeException("Course not found with id: " + id);
		    }
		}
		
		
		  
	
}
