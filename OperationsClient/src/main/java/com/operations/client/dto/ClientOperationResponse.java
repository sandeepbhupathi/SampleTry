package com.operations.client.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ClientOperationResponse {
	
	
	private String operation;
	private int responseRes;
	
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public void setResponseRes(int responseRes) {
		this.responseRes = responseRes;
	}
	public String getOperation() {
		return operation;
	}
	public int getResponseRes() {
		return responseRes;
	}
	
	public ClientOperationResponse() {
		super();
	}
	public ClientOperationResponse(String operation, int responseRes) {
		this.operation = operation;
		this.responseRes = responseRes;
	}
	
	
	

}
