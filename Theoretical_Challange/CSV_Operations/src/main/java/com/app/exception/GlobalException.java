package com.app.exception;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalException {
	@ExceptionHandler(FileNotFoundException.class)
	public ResponseEntity<ErrorDetails> fileNotFoundExceptionHandler(FileNotFoundException e, WebRequest req)  {
		
		ErrorDetails err= new ErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(e.getMessage());
		err.setDetails(req.getDescription(false));
		
		
		
	 return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	
	}
	@ExceptionHandler(NumberFormatException.class)
	public ResponseEntity<ErrorDetails> numberFormatExceptionHandler(NumberFormatException e, WebRequest req)  {
		
		ErrorDetails err= new ErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(e.getMessage());
		err.setDetails(req.getDescription(false));
		
		
		
	 return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	
	}
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorDetails> illegalArgumentExceptionHandler(IllegalArgumentException e, WebRequest req)  {
		
		ErrorDetails err= new ErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(e.getMessage());
		err.setDetails(req.getDescription(false));
		
		
		
	 return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorDetails> noHandlerFoundHandler(NoHandlerFoundException nfe,WebRequest req)  {
			
	
	ErrorDetails err=new ErrorDetails(LocalDateTime.now(), nfe.getMessage(), req.getDescription(false));
	
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
					
	}
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorDetails> runtimeExceptionHandler(RuntimeException rte,WebRequest req)  {
			
	
	ErrorDetails err=new ErrorDetails(LocalDateTime.now(), rte.getMessage(), req.getDescription(false));
	
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
					
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> myExceptionHandler(Exception e,WebRequest req)  {
	

		ErrorDetails error = new ErrorDetails(LocalDateTime.now(), e.getMessage(), req.getDescription(false));

		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);

	}
}
