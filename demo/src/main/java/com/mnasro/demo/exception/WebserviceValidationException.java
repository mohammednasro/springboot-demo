package com.mnasro.demo.exception;

import org.springframework.validation.Errors;

public class WebserviceValidationException extends RuntimeException {
    private static final long serialVersionUID = 1L;
	private final transient Errors validationObject;
    
    public WebserviceValidationException(Errors validationObject) {
      super("Validation error");
      this.validationObject = validationObject;
    }
 
 
    public Errors getValidationObject() {
      return this.validationObject;
    }
  }
