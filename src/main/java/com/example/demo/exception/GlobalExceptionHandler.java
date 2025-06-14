package com.example.demo.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ProductNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public @ResponseBody ErrorMessage handleEmployeeException(ProductNotFoundException exception)
	{
		return new ErrorMessage(HttpStatus.NOT_FOUND.value(),exception.getMessage() );
	}
	
	@ExceptionHandler(CategoryNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public @ResponseBody ErrorMessage handleCategoryException(CategoryNotFoundException exception)
	{
		return new ErrorMessage(HttpStatus.NOT_FOUND.value(),exception.getMessage() );
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public @ResponseBody ErrorMessage handleUserException(CategoryNotFoundException exception)
	{
		return new ErrorMessage(HttpStatus.NOT_FOUND.value(),exception.getMessage() );
	}
	 
}
