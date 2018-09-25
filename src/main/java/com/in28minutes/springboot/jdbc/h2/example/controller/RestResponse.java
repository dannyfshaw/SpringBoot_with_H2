package com.in28minutes.springboot.jdbc.h2.example.controller;

import com.fasterxml.jackson.annotation.*;

@JsonPropertyOrder({ "responseMessage", "wasSuccessful", "data"})
public class RestResponse {
	private Object data;
	private boolean wasSuccessful;
	@JsonProperty("ResponseMessage")
	private String responseMessage;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public boolean isWasSuccessful() {
		return wasSuccessful;
	}

	public void setWasSuccessful(boolean wasSuccessful) {
		this.wasSuccessful = wasSuccessful;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

}
