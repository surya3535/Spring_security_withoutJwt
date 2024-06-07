package com.demo.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandle {
	
	@ExceptionHandler(value = UserNotFoundException.class)
	public ResponseEntity<Object> GlobalUserNotFoundEx(UserNotFoundException message) {
		return new ResponseEntity<Object>(message.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR) ;
		
	}

}
