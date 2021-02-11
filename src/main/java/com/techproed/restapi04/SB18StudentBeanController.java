package com.techproed.restapi04;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SB18StudentBeanController {
	
	private final SB17StudentBeanService studentService;
	
	public SB18StudentBeanController(SB17StudentBeanService studentService) {
		this.studentService = studentService;
	}
	
	@GetMapping(path = "api/v1/students")
	public List<SB15StudentBean> studentList(){
		return studentService.getStudentList();
	}
	
}
