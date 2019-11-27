package com.example.demo.dto;

import java.io.Serializable;

public class EmployeeResponseDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String statusCode;
	private String statusMsg;
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusMsg() {
		return statusMsg;
	}
	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}
}
