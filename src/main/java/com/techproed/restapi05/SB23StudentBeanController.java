package com.techproed.restapi05;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SB23StudentBeanController {
	
//	private final SB22StudentBeanService studentService;
//	
//	@Autowired
//	public SB23StudentBeanController(SB22StudentBeanService studentService) {
//		this.studentService = studentService;
//	}
//	//according to final method
//	@GetMapping(path = "/api/v1/students")
//	public List<SB20StudentBean> studentList(){
//		return studentService.getStudents();
//	}
	
	//according to static method
	@GetMapping(path = "/api/v1/students")
	public List<SB20StudentBean> studentList(){
		return SB22StudentBeanService.getStudents();
	}
	
	@GetMapping(path = "/api/v1/students/{studentId}")
	public Optional<SB20StudentBean> getStudentById(@PathVariable(value = "studentId") Long studentId) {
		return SB22StudentBeanService.getStudentById(studentId);
	}
	
	@PostMapping(path = "/api/v1/students")
	public void insertNewStudent(@RequestBody SB20StudentBean student) {
		SB22StudentBeanService.addNewStudent(student);
	}
	
	@DeleteMapping(path = "/api/v1/delete/{studentId}")
	public String deleteStudentById(@PathVariable(value = "studentId") Long studentId) {
		return SB22StudentBeanService.deleteStudentById(studentId);
	}
	
	@PutMapping(path = "/api/v1/update/{studentId}")
	public ResponseEntity<SB20StudentBean> updateStudentById(@PathVariable(value = "studentId") Long studentId, @Validated @RequestBody SB20StudentBean newStudent) {
		return ResponseEntity.ok(SB22StudentBeanService.updateStudent(studentId, newStudent));
	}
	
	@PatchMapping(path = "/api/v1/updatePartially/{studentId}")
    public ResponseEntity<SB20StudentBean> updateStudentPartiallyById(@PathVariable(value = "studentId") Long studentId, @Validated @RequestBody SB20StudentBean newStudent) {
        return ResponseEntity.ok(SB22StudentBeanService.updateStudentPartially2(studentId, newStudent));
    }
}
