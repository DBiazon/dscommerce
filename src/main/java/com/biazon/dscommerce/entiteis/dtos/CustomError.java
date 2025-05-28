package com.biazon.dscommerce.entiteis.dtos;

import java.time.Instant;

import lombok.Getter;

@Getter
public class CustomError {

	private Instant timestamp;
	private Integer status;
	private String error;
	private String path;

	public CustomError(Instant timestamp, Integer status, String error, String path) {
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
		this.path = path;
	}
	
}
