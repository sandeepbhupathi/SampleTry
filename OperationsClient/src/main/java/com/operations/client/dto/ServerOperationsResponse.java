package com.operations.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ServerOperationsResponse {
	private String operationName;
	private int noOfOperations;
	private long finalOperationValue;
	public ServerOperationsResponse(String operationName, int noOfOperations, long finalOperationValue) {
		this.operationName = operationName;
		this.noOfOperations = noOfOperations;
		this.finalOperationValue = finalOperationValue;
	}
	
	
	public ServerOperationsResponse() {
	}
	
	public String getOperationName() {
		return operationName;
	}
	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}
	public int getNoOfOperations() {
		return noOfOperations;
	}
	public void setNoOfOperations(int noOfOperations) {
		this.noOfOperations = noOfOperations;
	}
	public long getFinalOperationValue() {
		return finalOperationValue;
	}
	public void setFinalOperationValue(long finalOperationValue) {
		this.finalOperationValue = finalOperationValue;
	}
	
	
	
	

}
