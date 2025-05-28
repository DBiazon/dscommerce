package com.biazon.dscommerce.exceptions;


public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -1933813289764931298L;

	public ResourceNotFoundException(String msg) {
		super(msg);
	}
}
