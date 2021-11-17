package com.ecommerce.exceptions;

public class ResourceNotFound extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ResourceNotFound(String exception) {
		super(exception);
	}
	

}
