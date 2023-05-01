package com.leonardo.management.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.leonardo.management.entities.Employee;

import io.swagger.annotations.ApiModelProperty;

public class EmployeeDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(hidden = true)
	private Long id;

	@NotBlank
	private String name;

	@NotBlank
	private String designation;

	@Positive
	@NotNull
	private Double salary;

	@Size(min = 9, max = 13)
	@NotBlank
	private String phoneNumber;

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
		this.phoneNumber = entity.getPhoneNumber();
		this.address = entity.getAddress();

	}

	public EmployeeDTO(@NotNull Long id, @NotEmpty String name, @NotEmpty String designation, @NotNull Double salary,
			@NotEmpty String phoneNumber, @NotEmpty String adress) {
		super();
		this.id = id;
		this.name = name;
		this.designation = designation;
		this.salary = salary;
		this.phoneNumber = phoneNumber;
		this.address = adress;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String adress) {
		this.address = adress;
	}
}