package com.techproed.restapi01;

public class SB02HelloWorldBean {

	private String message;


	public SB02HelloWorldBean() {
	}

	public SB02HelloWorldBean(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "SB02HelloWorldBean [message=" + message + "]";
	}
		
}
