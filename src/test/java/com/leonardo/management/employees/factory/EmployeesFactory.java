package com.leonardo.management.employees.factory;

import com.leonardo.management.dto.EmployeeDTO;
import com.leonardo.management.entities.Employee;
 public class EmployeesFactory {

	public static EmployeeDTO createEmployeeDTO() {
		EmployeeDTO employee = new EmployeeDTO(1L, "Igor", "Developer", 1200.00, "13123456789", "Atkinson road");
		return employee;

	}

	public static EmployeeDTO createNewEmployeeDTO() {
		EmployeeDTO employee = new EmployeeDTO(2L, "Laura", "Developer", 1200.00, "11123456789", "Atkinson av");
		return employee;

	}

	public static Employee createEmployee() {
		Employee employee = new Employee(1L, "Igor", "Developer", 1200.00, "13123456789", "Atkinson road");
		return employee;

	}
	
	public static EmployeeDTO createEmployeeSamePhoneNumber() {
		EmployeeDTO employee = new EmployeeDTO(1L, "Igor", "Developer", 1200.00, "13123456789", "Atkinson road");
		return employee;

	}

	public static Employee createNewEmployee() {
		Employee employee = new Employee(2L, "Laura", "Developer", 1200.00, "11123456789", "Atkinson av");
		return employee;

	}


	public static EmployeeDTO createEmployeeDtoWithBlankName() {
		EmployeeDTO employee = new EmployeeDTO(1L, "", "Developer", 1200.00, "13123456789", "Atkinson road");
		return employee;

	}

	public static EmployeeDTO createEmployeeDtoWithBlankPhoneNumber() {
		EmployeeDTO employee = new EmployeeDTO(2L, "Laura", "Developer", 1200.00, "", "Atkinson road");
		return employee;

	}

}
