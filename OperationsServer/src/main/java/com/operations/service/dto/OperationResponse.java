package com.operations.service.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class OperationResponse {
	
	
	private String operation;
	private int responseRes;
	public OperationResponse(String operation, int responseRes) {
		this.operation = operation;
		this.responseRes = responseRes;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public int getResponseRes() {
		return responseRes;
	}
	public void setResponseRes(int responseRes) {
		this.responseRes = responseRes;
	}
	
	
	

}
