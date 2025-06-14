package com.example.demo.exception;

public class CategoryNotFoundException extends RuntimeException {

	private String message;
	public CategoryNotFoundException(String message)
	{
		super(message);
		this.message =message;
		
		
	}
}
