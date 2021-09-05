package com.blogpessoal.Turma29.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{

	private static final long serialVersionUID = 1L;

	private List<FieldMenssager> errors = new ArrayList<>();
	
	public ValidationError() {
		super();
	}
	
	public ValidationError(Long timestamp, Integer status, String error) {
		super(timestamp, status, error);
	}
	
	public List<FieldMenssager> getErrors() {
		return errors;
	}
	
	public void addError(String fieldName, String menssage) {
		this.errors.add(new FieldMenssager(fieldName, menssage));
	}
	
}
