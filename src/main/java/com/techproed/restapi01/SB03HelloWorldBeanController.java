package com.techproed.restapi01;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SB03HelloWorldBeanController {
	
	@GetMapping(path = "/helloWorldBean")
	public SB02HelloWorldBean helloWorldBean() {
		
		return new SB02HelloWorldBean("Hello World with bean...");
	}

}
