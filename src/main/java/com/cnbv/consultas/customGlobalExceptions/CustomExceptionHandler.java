package com.cnbv.consultas.customGlobalExceptions;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> handleConstraintViolation(ConstraintViolationException ex) {
		List<String> detalles = ex.getConstraintViolations().stream().map(violation -> violation.getMessage())
				.collect(Collectors.toList());

		ErrorResponse error = new ErrorResponse("Validación fallida", detalles);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DataAccessException.class)
	public ResponseEntity<String> handleDataAccessException(DataAccessException ex) {

		return new ResponseEntity<>("Ocurrió un error al intentar almacenar la información de la consulta",
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
//
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception ex) {
		
		String message = ex.getMessage();
	    String exceptionType = ex.getClass().getName();
		
		return new ResponseEntity<>("Ocurrio un error general: \n"+message+"\n "+exceptionType, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(IlegalActionException.class)
	public ResponseEntity<String> handleIlegalUpdateException(Exception ex) {

		return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
	}

}
