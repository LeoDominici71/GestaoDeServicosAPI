package com.leonardo.management.employees.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

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

	@Test
	@DisplayName("Delete should throw EntityNotFound")
	public void deleteShouldThrowEntityNotFoundWhenIdDoesNotExists() {

		Integer nonExistingId = 99;
		

		assertThrows(EmployeeNotFoundException.class, () -> {

			service.deleteEmployee(nonExistingId);

		});
		
		

	}

}
