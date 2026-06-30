package com.ec.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>>handleValidation( MethodArgumentNotValidException ex){
		
		Map<String,String> errors = new HashMap<>();
		
		ex.getBindingResult().getFieldErrors().forEach(error -> {
		
		errors.put(error.getField(),error.getDefaultMessage());
			
			});
		
		return ResponseEntity
				.badRequest().body(errors);
		
	}
	
	  @ExceptionHandler(RuntimeException.class)
	    public ResponseEntity<Map<String,String>> handleRuntimeException(
	            RuntimeException ex){

	        Map<String,String> error = new HashMap<>();

	        error.put("message", ex.getMessage());

	        return ResponseEntity.badRequest().body(error);
	    }
	  
	  @ExceptionHandler(ProductNotFoundException.class)
	  public ResponseEntity<Map<String,String>> handleProductException(
	          ProductNotFoundException ex){

	      Map<String,String> error = new HashMap<>();

	      error.put("message", ex.getMessage());

	      return ResponseEntity.badRequest().body(error);
	  }
	  
	  @ExceptionHandler(UserNotFoundException.class)
	  public ResponseEntity<Map<String,String>> handleUserException(
	          UserNotFoundException ex){

	      Map<String,String> error = new HashMap<>();

	      error.put("message", ex.getMessage());

	      return ResponseEntity.badRequest().body(error);
	  }
	
	  @ExceptionHandler(OrderNotFoundException.class)
	  public ResponseEntity<Map<String,String>> handleOrderException(
	          OrderNotFoundException ex){

	      Map<String,String> error = new HashMap<>();

	      error.put("message", ex.getMessage());

	      return ResponseEntity.badRequest().body(error);
	  }
	  
	  @ExceptionHandler(ReviewNotFoundException.class)
	  public ResponseEntity<Map<String,String>> handleReviewException(
	          ReviewNotFoundException ex){

	      Map<String,String> error = new HashMap<>();

	      error.put("message", ex.getMessage());

	      return ResponseEntity.badRequest().body(error);
	  }
}
