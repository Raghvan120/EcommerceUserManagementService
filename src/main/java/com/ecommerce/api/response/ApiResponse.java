package com.ecommerce.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ApiResponse {
	private String message;
	private Object error;
	private Object body;
	private int status;
	private String uri;
}
