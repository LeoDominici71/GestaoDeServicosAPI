package com.leonardo.management.employees.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.leonardo.management.entities.Employee;
import com.leonardo.management.entities.dto.EmployeeDTO;
import com.leonardo.management.repositories.EmployeeDatabase;
import com.leonardo.management.service.EmployeeService;
import com.leonardo.management.service.exceptions.EmployeeNotFoundException;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class EmployeeIntegrationTest {

	@Autowired
	private EmployeeService service;

	@Autowired
	EmployeeDatabase employeeRepository;

	/*
	 * //GET Employee // 1 - Success when try to get all employee
	 */

	@Test
	@DisplayName("getEmployee should return a list of employee DTOs")
	public void getEmployeeShouldReturnListOfEmployeeDTOs() {
		// given
		Employee employee1 = new Employee(1L, "John Doe", "Developer", 1200.00, "123456710", "Atkinson road");
		Employee employee2 = new Employee(2L, "Jane Doe", "Designer", 1500.00, "123456711", "Broadway");
		employeeRepository.saveEmployee(employee1);
		employeeRepository.saveEmployee(employee2);

		// when
		List<EmployeeDTO> result = service.getEmployee();

		// then
		assertNotNull(result);
		assertEquals(2, result.size());
		assertEquals(employee1.getPhoneNumber(), result.get(0).getPhoneNumber());
		assertEquals(employee2.getPhoneNumber(), result.get(1).getPhoneNumber());
	}

	/*
	 * GET Employee BY ID 1 - Success when try to get existent employee 2 - Error
	 * when try to get non-existent employee
	 * 
	 */

	@Test
	@DisplayName("getEmployeeById should return employee by Id")
	public void getEmployeeShouldReturnEmployeeWhenIdExist() {
		// given
		Employee employee1 = new Employee(1L, "Igor", "Developer", 1200.00, "123456710", "Atkinson road");
		employeeRepository.saveEmployee(employee1);

		Long existingId = 1L;
		// when
		EmployeeDTO result = service.getEmployeeById(existingId);
		// then
		assertNotNull(result);
		assertEquals(employeeRepository.getEmployeeById(existingId).getPhoneNumber(), result.getPhoneNumber());

	}

	@Test
	@DisplayName("getEmployeeById should throw EmployeeNotFoundException")
	public void getEmployeeShouldThrowEntityNotFoundWhenIdDoesNotExists() {

		Long nonExistingId = 99L;

		assertThrows(EmployeeNotFoundException.class, () -> {

			service.getEmployeeById(nonExistingId);

		});

	}

	/*
	 * DELETE EMPLOYEE 1 - Error when try to delete non-existent employee 2 -
	 * No-Content when try to delete existent employee
	 */

	@Test
	@DisplayName("Delete should throw EntityNotFound")
	public void deleteShouldThrowEntityNotFoundWhenIdDoesNotExists() {

		Long nonExistingId = 99L;

		assertThrows(EmployeeNotFoundException.class, () -> {

			service.deleteEmployee(nonExistingId);

		});

	}

	@Test
	@DisplayName("Delete should throw ")
	public void deleteShouldThrowNothingWhenIdExists() {
		Employee employee1 = new Employee(1L, "Igor", "Developer", 1200.00, "123456710", "Atkinson road");
		employeeRepository.saveEmployee(employee1);

		Long existingId = 1L;

		// Delete the employee
		service.deleteEmployee(existingId);

		// Verify that the employee has been deleted
		assertNull(employeeRepository.getEmployeeById(existingId));

	}

	/*
	 * CREATE Employee 1 - Success when try to create a valid employee 2 - Should
	 * not create when there are null fields
	 */

	@Test
	@DisplayName("saveEmployee should create an employee")
	public void saveEmployeeShouldCreateAnEmployee() {

		// given
		Employee employee1 = new Employee(1L, "Osvaldo", "Developer", 1200.00, "123456710", "Atkinson road");

		// when
		employeeRepository.saveEmployee(employee1);

		// then
		EmployeeDTO result = service.getEmployeeById(employee1.getId());
		assertNotNull(result);
		assertEquals(employee1.getName(), result.getName());
		assertEquals(employee1.getDesignation(), result.getDesignation());
		assertEquals(employee1.getSalary(), result.getSalary());
		assertEquals(employee1.getPhoneNumber(), result.getPhoneNumber());
		assertEquals(employee1.getAddress(), result.getAddress());

	}

	@Test
	@DisplayName("saveEmployee should not create an employee")
	public void saveEmployeeShouldNotCreateAnEmployee() {

		Employee employee1 = new Employee(1L, "Igor", "", 1200.00, "123456710", "Atkinson road");
		employeeRepository.saveEmployee(employee1);

		Long existingId = 1L;

		// Delete the employee
		service.deleteEmployee(existingId);

		// Verify that the employee has been deleted
		assertNull(employeeRepository.getEmployeeById(existingId));

	}

	/*
	 * UPDATE TEACHER 1 - Error when trying to update and teacher does not exist 2 -
	 * Success when teacher is updated
	 */

	@Test
	@DisplayName("updateEmployee should update an employee")
	public void updateEmployeeShouldUpdateAnEmployee() {
		// given
		Employee employee1 = new Employee(1L, "Osvaldo", "Developer", 1200.00, "123456710", "Atkinson road");
		EmployeeDTO newEmployee1 = new EmployeeDTO(1L, "Osvaldo da Silva", "Developer", 1200.00, "123456710",
				"Atkinson road");
		employeeRepository.saveEmployee(employee1);

		// when
		service.updateEmployee(1L, newEmployee1);

		// then
		EmployeeDTO result = service.getEmployeeById(employee1.getId());
		assertNotNull(result);
		assertEquals(newEmployee1.getName(), result.getName());
		assertEquals(newEmployee1.getDesignation(), result.getDesignation());
		assertEquals(newEmployee1.getSalary(), result.getSalary());
		assertEquals(newEmployee1.getPhoneNumber(), result.getPhoneNumber());
		assertEquals(newEmployee1.getAddress(), result.getAddress());
	}

	@Test
	@DisplayName("updateEmployee should not update an employee")
	public void updateEmployeeShouldNotUpdateAnEmployee() {
		EmployeeDTO employee1 = new EmployeeDTO(1L, "Osvaldo", "Developer", 1200.00, "123456710", "Atkinson road");
		Long nonExistingId = 99L;

		assertThrows(EmployeeNotFoundException.class, () -> {

			service.updateEmployee(nonExistingId, employee1);

		});

	}

}
