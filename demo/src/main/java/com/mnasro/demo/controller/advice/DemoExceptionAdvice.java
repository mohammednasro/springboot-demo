package com.mnasro.demo.controller.advice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mnasro.demo.exception.WebserviceValidationException;

@RestControllerAdvice
public class DemoExceptionAdvice {

	private static final Logger LOG = LoggerFactory.getLogger(DemoExceptionAdvice.class);

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Map<String, List<String>>> bindExceptionHandler(Exception ex) {

		LOG.error("Exception [{}] ", ex.getMessage());
		Map<String, List<String>> map = new HashMap<>();
		map.put("errors", Arrays.asList(ex.getMessage()));
		return new ResponseEntity<Map<String, List<String>>>(map, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(BindException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Map<String, List<String>>> bindExceptionHandler(BindException ex) {
		List<String> errors = ex.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
				.collect(Collectors.toList());

		Map<String, List<String>> map = new HashMap<>();
		map.put("errors", errors);
		return new ResponseEntity<Map<String, List<String>>>(map, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(WebserviceValidationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Map<String, List<String>>> webserviceValidationExceptionHandler(
			WebserviceValidationException ex) {

		List<String> errors = ex.getValidationObject().getFieldErrors().stream().map(ef -> ef.getDefaultMessage())
				.collect(Collectors.toList());
		Map<String, List<String>> map = new HashMap<>();
		map.put("errors", errors);
		return new ResponseEntity<Map<String, List<String>>>(map, HttpStatus.BAD_REQUEST);
	}
}