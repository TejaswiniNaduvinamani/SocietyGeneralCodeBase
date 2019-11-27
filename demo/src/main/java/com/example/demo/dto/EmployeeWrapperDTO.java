package com.example.demo.dto;

import java.io.Serializable;
import java.util.List;

public class EmployeeWrapperDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String statusCode;
	
	private String statusMsg;
	
	private List<EmployeeDTO> empDTO;

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

	public List<EmployeeDTO> getEmpDTO() {
		return empDTO;
	}

	public void setEmpDTO(List<EmployeeDTO> empDTO) {
		this.empDTO = empDTO;
	}
}
