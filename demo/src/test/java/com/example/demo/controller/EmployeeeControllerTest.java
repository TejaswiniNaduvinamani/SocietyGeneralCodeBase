package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.DemoApplicationTests;
import com.example.demo.constants.EmployeeConstants;
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.dto.EmployeeWrapperDTO;
import com.example.demo.service.EmployeeService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeeControllerTest extends DemoApplicationTests {
	
	public MockMvc mockMvc;
	
	@InjectMocks
	private EmployeeController employeeController;
	
	@Mock
	private EmployeeService employeeService;
	
	private static final String content = "{\r\n" + 
			"	\"firstName\" : \"Tejaswini\",\r\n" + 
			"	\"lastName\" : \"Naduvinamani\",\r\n" + 
			"	\"gender\"   : \"Female\",\r\n" + 
			"	\"dateOfBirth\" : \"1992-12-14\",\r\n" + 
			"	\"department\" : \"EC\"\r\n" + 
			"}";
	
	@Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.employeeController).build();
    }
	
	@Test
	public void testRegisterEmployee() throws Exception {
		ResultActions resultActions = this.mockMvc.perform(post("/employee/registerEmployee")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(content)) ;
		JSONObject result = new JSONObject(resultActions.andReturn().getResponse().getContentAsString());
        String statusCode = String.valueOf(result.get("statusCode"));
        
        assertEquals(EmployeeConstants.RESPONSE_SUCCESS, statusCode);
        
        verify(employeeService).registerEmployee(any(EmployeeDTO.class));
	}
	
	@Test
	public void testFindAllEmployee() throws Exception {
		List<EmployeeDTO> employeeDTOList = new ArrayList<>();
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setFirstName("Test FirstName");
		employeeDTO.setLastName("Test LastName");
		employeeDTO.setDateOfBirth(new Date());
		employeeDTO.setGender("Male");
		employeeDTO.setDepartment("Testing");
		employeeDTOList.add(employeeDTO);
		
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<EmployeeWrapperDTO> typeRef = new TypeReference<EmployeeWrapperDTO>() {
		};
		
		when(employeeService.findEmployeeDetails()).thenReturn(employeeDTOList) ;
		
		ResultActions resultActions = this.mockMvc.perform(get("/employee/findAllEmployee")) ;
		String result = resultActions.andReturn().getResponse().getContentAsString();
		
		EmployeeWrapperDTO response = mapper.readValue(result, typeRef);
        
        assertEquals(EmployeeConstants.RESPONSE_SUCCESS, response.getStatusCode());
        assertEquals(employeeDTO.getFirstName(), response.getEmpDTO().get(0).getFirstName());
        
        verify(employeeService).findEmployeeDetails();
	}
	
	@Test
	public void testFindAllEmployeeEmptyResponse() throws Exception {
		List<EmployeeDTO> employeeDTOList = new ArrayList<>();
		
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<EmployeeWrapperDTO> typeRef = new TypeReference<EmployeeWrapperDTO>() {
		};
		
		when(employeeService.findEmployeeDetails()).thenReturn(employeeDTOList) ;
		
		ResultActions resultActions = this.mockMvc.perform(get("/employee/findAllEmployee")) ;
		String result = resultActions.andReturn().getResponse().getContentAsString();
		
		EmployeeWrapperDTO response = mapper.readValue(result, typeRef);
        
        assertEquals(EmployeeConstants.NO_RESULT_FOUND, response.getStatusCode());
        assertEquals(0, response.getEmpDTO().size());
        
        verify(employeeService).findEmployeeDetails();
	}
	
	@Test
	public void testFindAllEmployeeException() throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<EmployeeWrapperDTO> typeRef = new TypeReference<EmployeeWrapperDTO>() {
		};
		
		when(employeeService.findEmployeeDetails()).thenThrow(new RuntimeException()) ;
		
		ResultActions resultActions = this.mockMvc.perform(get("/employee/findAllEmployee")) ;
		String result = resultActions.andReturn().getResponse().getContentAsString();
		
		EmployeeWrapperDTO response = mapper.readValue(result, typeRef);
        
        assertEquals(EmployeeConstants.RESPONSE_FAILED, response.getStatusCode());
        
        verify(employeeService).findEmployeeDetails();
	}

}
