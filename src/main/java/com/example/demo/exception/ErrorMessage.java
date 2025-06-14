
package com.example.demo.exception;
import lombok.Data;

@Data
public class ErrorMessage {

	private int statsCode;
	private String message;
	
	public ErrorMessage(int statusCode ,String message)
	{
		this.message=message;
		this.statsCode= statusCode;
		
	}
}   
