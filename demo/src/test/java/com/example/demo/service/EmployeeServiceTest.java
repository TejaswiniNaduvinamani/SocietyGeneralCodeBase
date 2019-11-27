package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entities.EmployeeDetailEntity;
import com.example.demo.repository.EmployeeRepository;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {
	
	@InjectMocks
	EmployeeService employeeService;
	
	@Mock
	EmployeeRepository employeeRepository;
	
	
	@Test
	public void testRegisterEmployee() {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setFirstName("Test FirstName");
		employeeDTO.setLastName("Test LastName");
		employeeService.registerEmployee(employeeDTO);
		verify(employeeRepository).save(any(EmployeeDetailEntity.class));
	}
	
	@Test
	public void testFindAllEmployee() {
		List<EmployeeDetailEntity > employeeEntity = new ArrayList<>();
		EmployeeDetailEntity entity = new  EmployeeDetailEntity();
		entity.setFirstName("Test FirstName");
		entity.setLastName("Test LastName");
		entity.setDepartment("Testing");
		entity.setDob(new Date());
		entity.setGender("Male");
		employeeEntity.add(entity);
		when(employeeRepository.findAll()).thenReturn(employeeEntity);
		
		List<EmployeeDTO> response =  employeeService.findEmployeeDetails();
		
		assertEquals(1, response.size());
		assertEquals(entity.getFirstName(), response.get(0).getFirstName());
		assertEquals(entity.getLastName(), response.get(0).getLastName());
		assertEquals(entity.getDepartment(), response.get(0).getDepartment());
		assertEquals(entity.getDob(), response.get(0).getDateOfBirth());
		assertEquals(entity.getGender(), response.get(0).getGender());
		
		
		verify(employeeRepository).findAll();
	}
	
	
	

}
