package com.leonardo.management.entities.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.leonardo.management.entities.Employee;

public class EmployeeDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotNull
	private Integer id;
	@NotBlank
	private String name;
	@NotBlank
	private String designation;
	@NotNull
	private Double salary;
	@NotBlank
	private String number;
	@NotBlank
	private String address;

	public EmployeeDTO() {
		// TODO Auto-generated constructor stub
	}
	
public EmployeeDTO(Employee entity) {
		
		this.id = entity.getId();
		this.name = entity.getName();
		this.designation = entity.getDesignation();
		this.salary = entity.getSalary();
		this.number = entity.getNumber();
		this.address = entity.getAddress();
		
	}

	public EmployeeDTO(@NotNull Integer id, @NotEmpty String name, @NotEmpty String designation, @NotNull Double salary,
			@NotEmpty String number, @NotEmpty String adress) {
		super();
		this.id = id;
		this.name = name;
		this.designation = designation;
		this.salary = salary;
		this.number = number;
		this.address = adress;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String adress) {
		this.address = adress;
	}
}