package com.example.demo.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="EMPLOYEE_DETAIL")
public class EmployeeDetailEntity {
	
	@Id
    @GeneratedValue
    private Long id;
	
	@Column(name="first_name")
    private String firstName;
	
	@Column(name="last_name")
    private String lastName;
	
	@Column(name="gender")
    private String gender;
	
	@Column(name="dob")
    private Date dob;
	
	@Column(name="department")
    private String department;
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Long getId() {
		return id;
	}

	@Override
    public String toString() {
        return "EmployeeEntity [id=" + id + ", firstName=" + firstName + 
                ", lastName=" + lastName + ", gender=" + gender   + "]";
    }
}
