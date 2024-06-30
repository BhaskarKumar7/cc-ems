package com.codeclause.ems.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class EMSExceptionHandler {

	private static final String MESSAGE = "message";
	
	/*@ExceptionHandler(value = BadCredentialsException.class)
	public ResponseEntity<Map<String, String>> handleBadCredentialsException(BadCredentialsException be) {
		log.error("invaid credentials----> {}",be);
		Map<String,String> responseMap = new HashMap<>();
		responseMap.put(MESSAGE, "Invalid username or password!");
		return new ResponseEntity<>(responseMap,HttpStatus.BAD_REQUEST);
	}*/
	
	@ExceptionHandler(value = ExpiredJwtException.class)
	public ResponseEntity<Map<String, String>> handleExpiredJwtException(ExpiredJwtException ee) {
		log.error("jwt expired-----> {}",ee);
		Map<String,String> responseMap = new HashMap<>();
		responseMap.put(MESSAGE, "Token expired, please login again!");
		return new ResponseEntity<>(responseMap,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(
			MethodArgumentNotValidException me) {
		log.error("user creation exception details -> {}", me);
		Map<String, String> errorMap = new HashMap<>();
		StringBuilder errors = new StringBuilder();
		me.getAllErrors().forEach(error -> {
			errors.append(error.getDefaultMessage()).append("\n");
		});
		errorMap.put(MESSAGE, errors.substring(0, errors.length()-2));
		return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Map<String, String>> handleGeneralException(Exception e) {
		log.error("some system error has occurred-----> {}",e);
		Map<String,String> responseMap = new HashMap<>();
		responseMap.put(MESSAGE, "Not able to fulfill your request!");
		return new ResponseEntity<>(responseMap,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = RuntimeException.class)
	public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException re) {
		log.error("runtime exception has occurred-----> {}",re);
		Map<String,String> responseMap = new HashMap<>();
		responseMap.put(MESSAGE, re.getMessage());
		return new ResponseEntity<>(responseMap,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = InvalidEmailException.class)
	public ResponseEntity<Map<String, String>> handleInvalidEmailException(InvalidEmailException ie) {
		log.error("email is invalid-----> {}",ie);
		Map<String,String> responseMap = new HashMap<>();
		responseMap.put(MESSAGE, ie.getMessage());
		return new ResponseEntity<>(responseMap,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = InvalidInputException.class)
	public ResponseEntity<Map<String, String>> handleInvalidInputException(InvalidInputException ie) {
		log.error("input is invalid-----> {}",ie);
		Map<String,String> responseMap = new HashMap<>();
		responseMap.put(MESSAGE, ie.getMessage());
		return new ResponseEntity<>(responseMap,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = PayrollAlreadyAddedException.class)
	public ResponseEntity<Map<String, String>> handlePayrollAlreadyAddedException(PayrollAlreadyAddedException ie) {
		log.error("payroll already created-----> {}",ie);
		Map<String,String> responseMap = new HashMap<>();
		responseMap.put(MESSAGE, ie.getMessage());
		return new ResponseEntity<>(responseMap,HttpStatus.BAD_REQUEST);
	}
}
