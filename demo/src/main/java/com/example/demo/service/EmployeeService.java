package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entities.EmployeeDetailEntity;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	public void registerEmployee(EmployeeDTO employeeDTO) {
		EmployeeDetailEntity entity = populateEmployeeEntity(employeeDTO);
		employeeRepository.save(entity);
	}
	
	public List<EmployeeDTO> findEmployeeDetails() {
		List<EmployeeDetailEntity> employeeDetailEntity = employeeRepository.findAll();
		return employeeDetailEntity.stream().map(emp -> populateEmployeeDTO(emp)).collect(Collectors.toList());
	}

	private EmployeeDetailEntity populateEmployeeEntity(EmployeeDTO employeeDTO) {
		EmployeeDetailEntity entity = new EmployeeDetailEntity();
		entity.setFirstName(employeeDTO.getFirstName());
		entity.setLastName(employeeDTO.getLastName());
		entity.setDepartment(employeeDTO.getDepartment());
		entity.setDob(employeeDTO.getDateOfBirth());
		entity.setGender(employeeDTO.getGender());
		return entity;
	}
	
	private EmployeeDTO populateEmployeeDTO(EmployeeDetailEntity empployeeDetail) {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setFirstName(empployeeDetail.getFirstName());
		employeeDTO.setLastName(empployeeDetail.getLastName());
		employeeDTO.setDepartment(empployeeDetail.getDepartment());
		employeeDTO.setDateOfBirth(empployeeDetail.getDob());
		employeeDTO.setGender(empployeeDetail.getGender());
		return employeeDTO;
	}

}
