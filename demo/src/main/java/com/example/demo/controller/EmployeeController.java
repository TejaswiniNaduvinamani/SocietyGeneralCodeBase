package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.constants.EmployeeConstants;
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.dto.EmployeeResponseDTO;
import com.example.demo.dto.EmployeeWrapperDTO;
import com.example.demo.service.EmployeeService;

@RestController
@CrossOrigin
@RequestMapping(value="/employee")
public class EmployeeController {
	
	
	@Autowired
	private EmployeeService employeeService;
	
	
	@RequestMapping(value="/registerEmployee", method=RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public EmployeeResponseDTO registerEmployee(@RequestBody EmployeeDTO employeeDTO) {
		EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
		try {
		employeeService.registerEmployee(employeeDTO);
		responseDTO.setStatusCode(EmployeeConstants.RESPONSE_SUCCESS);
		responseDTO.setStatusMsg(EmployeeConstants.SUCCESS_MSG);
		} catch(Exception e) {
			responseDTO.setStatusCode(EmployeeConstants.RESPONSE_FAILED);
			responseDTO.setStatusMsg(EmployeeConstants.FAILURE_MSG);
		}
		return responseDTO;
	}
	
	@RequestMapping(value="/findAllEmployee", method=RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public EmployeeWrapperDTO findAllEmployees() {
		EmployeeWrapperDTO responseDTO = new EmployeeWrapperDTO();
		try {
		responseDTO.setEmpDTO(employeeService.findEmployeeDetails());
		if(CollectionUtils.isEmpty(responseDTO.getEmpDTO())) {
			responseDTO.setStatusCode(EmployeeConstants.NO_RESULT_FOUND);
		} else {
			responseDTO.setStatusCode(EmployeeConstants.RESPONSE_SUCCESS);
		}
		} catch(Exception e){
			responseDTO.setStatusCode(EmployeeConstants.RESPONSE_FAILED);
		}
		return responseDTO;
	}

}
