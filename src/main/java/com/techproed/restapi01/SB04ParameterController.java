package com.techproed.restapi01;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SB04ParameterController {

	@GetMapping(path = "/helloWorldBean/{name}")
	public SB02HelloWorldBean helloWorldBeanWithName(@PathVariable String name) {
	
		return new SB02HelloWorldBean(String.format("Hello %s my dear ", name));
	}	

}