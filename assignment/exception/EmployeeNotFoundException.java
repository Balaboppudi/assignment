package com.assignment.exception;

@SuppressWarnings("serial")
public class EmployeeNotFoundException extends RuntimeException {
private String errorCode;
public EmployeeNotFoundException(String errorCode){
	this.errorCode = errorCode;
}

public String getErrorCode(){
	return errorCode;
}
	
}
