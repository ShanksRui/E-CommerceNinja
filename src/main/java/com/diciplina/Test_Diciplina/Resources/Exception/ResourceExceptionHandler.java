package com.diciplina.Test_Diciplina.Resources.Exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.diciplina.Test_Diciplina.Services.Exception.DataBaseException;
import com.diciplina.Test_Diciplina.Services.Exception.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> NotFoundResource(ResourceNotFoundException e,HttpServletRequest request){
		String erro = "Resource not Found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError stdErro = new StandardError(Instant.now(),status.value(), erro, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(stdErro);
		
	}
	@ExceptionHandler(DataBaseException.class)
	public ResponseEntity<StandardError> DataBase(DataBaseException e, HttpServletRequest request){
	 String erro = "DataBase error";
	 HttpStatus status = HttpStatus.BAD_REQUEST;
	 StandardError stdErro = new StandardError(Instant.now(),status.value(), erro, e.getMessage(), request.getRequestURI());
	 return ResponseEntity.status(status).body(stdErro);		
	}
	
}
