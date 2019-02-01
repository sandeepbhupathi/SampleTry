package com.operations.client.controlleradvice;

import java.sql.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.operations.client.dto.ErrorDetails;
import com.operations.client.exception.IlligalArgsException;
import com.operations.client.exception.NegativeNumberException;

@ControllerAdvice
public class AppResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value={IlligalArgsException.class,NegativeNumberException.class})
	public ResponseEntity<ErrorDetails> handleIlligalArgsException(Exception exception){
		if(exception instanceof IlligalArgsException){
			return new ResponseEntity<ErrorDetails>(new ErrorDetails(new Date(System.currentTimeMillis()),"Illigal Args Exception" , "Only Add Operation was Implemented"),HttpStatus.BAD_REQUEST);
		}else if(exception instanceof NegativeNumberException){
			return new ResponseEntity<ErrorDetails>(new ErrorDetails(new Date(System.currentTimeMillis()),"Negative Number Exception" , "Only Postive Number can be passed as input args"),HttpStatus.BAD_REQUEST);
		}else{
			return new ResponseEntity<ErrorDetails>(new ErrorDetails(new Date(System.currentTimeMillis()),"Illigal Args Exception" , "Only Add Operation was Implemented"),HttpStatus.BAD_REQUEST);
		}
	}
}
