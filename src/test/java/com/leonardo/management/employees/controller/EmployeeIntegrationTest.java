package com.leonardo.management.employees.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leonardo.management.entities.Employee;
import com.leonardo.management.entities.dto.EmployeeDTO;
import com.leonardo.management.repositories.EmployeeDatabase;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class EmployeeIntegrationTest {

	private static final String EMPLOYEES_PATH = "/employees";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	EmployeeDatabase employeeRepository;

	@Autowired
	ObjectMapper objectMapper;

	@BeforeEach
	void setUp() throws Exception {

	}
	/*
	 * //GET Employee // 1 - Success when try to get all employee
	 */

	@Test
	@DisplayName("Should return success when try to get all employees")
	void shouldReturnSuccessWhenTryToGetAllTeachers() throws Exception {
		// given
		Employee employee1 = new Employee(1L, "Igor", "Developer", 1200.00, "123456710", "Atkinson road");

		Employee employee2 = new Employee(2L, "Laura", "Developer", 1200.00, "123456789", "Atkinson av");

		employeeRepository.saveEmployee(employee1);
		employeeRepository.saveEmployee(employee2);

		// when
		var employeeRequest = get(EMPLOYEES_PATH);

		// then
		mockMvc.perform(employeeRequest).andExpect(status().isOk()).andExpect(jsonPath("$[0].name").value("Igor"))
				.andExpect(jsonPath("$[0].phoneNumber").value("123456710"))
				.andExpect(jsonPath("$[1].name").value("Laura"))
				.andExpect(jsonPath("$[1].phoneNumber").value("123456789"));
	}

	/*
	 * GET Employee BY ID 1 - Success when try to get existent employee 2 - Error
	 * when try to get non-existent employee
	 * 
	 */

	@Test
	public void findByIdShouldReturnEmployeeWhenIdExists() throws Exception {

		Employee employee1 = new Employee(1L, "Igor", "Developer", 1200.00, "123456710", "Atkinson road");

		employeeRepository.saveEmployee(employee1);

		ResultActions result = mockMvc
				.perform(get("/employees/{id}", employee1.getId()).accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.id").value(employee1.getId()));
		result.andExpect(jsonPath("$.name").value("Igor"));
		result.andExpect(jsonPath("$.phoneNumber").value("123456710"));

	}

	@Test
	public void findByIdShouldReturnEntityNotFoundExceptionsWhenIdExists() throws Exception {

		Integer nonExistingId = 99;

		ResultActions result = mockMvc
				.perform(get("/employees/{id}", nonExistingId).accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isNotFound());

	}

	/*
	 * CREATE Employee 1 - Error when try to create employee with empty name or
	 * phoneNumber 2 - Error when try to create employee with phoneNumber already
	 * registered 3 - Success when try to create a valid teacher
	 */

	@Test
	@DisplayName("Should return error when try to create a employee with empty name or number null")
	void shouldReturnErrorWhenTryToCreateTeacherWithEmptyNameOrCpf() throws Exception {
		// given
		var employeeEmptyName = new EmployeeDTO(1L, "", "Developer", 1200.00, "123456710", "Atkinson road");
		var employeeEmptyPhoneNumber = new EmployeeDTO(2L, "Osvaldo", "Developer", 1200.00, "", "Atkinson road");

		// when
		var employeeEmptyNameRequest = post(EMPLOYEES_PATH).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(employeeEmptyName));

		var employeeEmptyPhoneNumberRequest = post(EMPLOYEES_PATH).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(employeeEmptyPhoneNumber));

		// then
		mockMvc.perform(employeeEmptyNameRequest).andExpect(status().isUnprocessableEntity())
				.andExpect(jsonPath("$.status").value("422"))
				.andExpect(jsonPath("$.error").value("Validation exception."));
		mockMvc.perform(employeeEmptyPhoneNumberRequest).andExpect(status().isUnprocessableEntity())
				.andExpect(jsonPath("$.status").value("422"))
				.andExpect(jsonPath("$.error").value("Validation exception."));
	}

	@Test
	@DisplayName("Should return error when try to create a employee with phoneNumber already registered")
	void shouldReturnErrorWhenTryToCreateTeacherWithCpfAlreadyRegistered() throws Exception {
		// given
		var employee1 = new Employee(1L, "Lucas", "Developer", 1200.00, "123456710", "Atkinson road");
		var employee2 = new Employee(2L, "Osvaldo", "Developer", 1200.00, "123456710", "Atkinson road");

		employeeRepository.saveEmployee(employee1);

		// when
		var teacherCpfAlreadyRegisteredRequest = post(EMPLOYEES_PATH).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(employee2));

		// then
		mockMvc.perform(teacherCpfAlreadyRegisteredRequest).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.status").value("400"))
				.andExpect(jsonPath("$.error").value("Duplicated data exception."));
	}

	@Test
	@DisplayName("Should return success when try to create a valid employee")
	void shouldReturnSuccessWhenTryToCreateAValidEmployee() throws Exception {
		// given
		var employee1 = new Employee(1L, "Lucas", "Developer", 1200.00, "321899241", "Atkinson road");

		// when
		var teacherRequest = post(EMPLOYEES_PATH).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(employee1));

		// then
		mockMvc.perform(teacherRequest).andExpect(status().isCreated())
				.andExpect(jsonPath("$.name").value(employee1.getName()))
				.andExpect(jsonPath("$.phoneNumber").value(employee1.getPhoneNumber()));
	}

	/*
	 * UPDATE TEACHER 1 - Error when try to update non-existent employee 2 - Error
	 * when try to update employee with empty name or phone number 3 - Error when
	 * try to update employee with invalid number 4 - Error when try to update
	 * employee with phone number already registered 5 - Ok when try to update a
	 * valid employee
	 */

	@Test
	@DisplayName("Should return error when try to update non-existent teacher")
	void shouldReturnErrorWhenTryToUpdateNonExistentTeacher() throws Exception {
		// given
		Integer nonExistingId = 99;
		var employee1 = new Employee(1L, "Lucas", "Developer", 1200.00, "321899241", "Atkinson road");

		// when
		var employeeRequest = put(EMPLOYEES_PATH + "/{id}", nonExistingId).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(employee1));

		// then
		mockMvc.perform(employeeRequest).andExpect(status().isNotFound()).andExpect(jsonPath("$.status").value("404"))
				.andExpect(jsonPath("$.error").value("Entity not found exception."));
	}

	@Test
	@DisplayName("Should return error when try to update teacher with empty name or phone number")
	void shouldReturnErrorWhenTryToUpdateTeacherWithEmptyNameOrCpf() throws Exception {
		// given
		var employee1 = new Employee(1L, "", "Developer", 1200.00, "400000000", "Atkinson road");
		var employee2 = new Employee(2L, "Osvaldo", "Developer", 1200.00, "", "Atkinson road");

		// when
		var employeeEmptyNameRequest = put(EMPLOYEES_PATH + "/{id}", employee1.getId())
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(employee1));

		var teacherEmptyNumberRequest = put(EMPLOYEES_PATH + "/{id}", employee2.getId())
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(employee2));

		// then
		mockMvc.perform(employeeEmptyNameRequest).andExpect(status().isUnprocessableEntity())
				.andExpect(jsonPath("$.status").value("422"))
				.andExpect(jsonPath("$.error").value("Validation exception."));

		mockMvc.perform(teacherEmptyNumberRequest).andExpect(status().isUnprocessableEntity())
				.andExpect(jsonPath("$.status").value("422"))
				.andExpect(jsonPath("$.error").value("Validation exception."));

	}

	@Test
	@DisplayName("Should return error when try to update employee with invalid phone number")
	void shouldReturnErrorWhenTryToUpdateTeacherWithInvalidCpf() throws Exception {
		// given
		var employee1 = new Employee(1L, "Lucas", "Developer", 1200.00, "3899241", "Atkinson road");
		employeeRepository.saveEmployee(employee1);

		// when
		var employeeInvalidNumberRequest = put(EMPLOYEES_PATH + "/{id}", employee1.getId())
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(employee1));

		// then
		mockMvc.perform(employeeInvalidNumberRequest).andExpect(status().isUnprocessableEntity())
				.andExpect(jsonPath("$.status").value("422"))
				.andExpect(jsonPath("$.error").value("Validation exception."));
	}

	@Test
	@DisplayName("Should return error when try to update teacher with cpf already registered")
	void shouldReturnErrorWhenTryToUpdateTeacherWithCpfAlreadyRegistered() throws Exception {
		// given
		var employee1 = new Employee(1L, "Lucas", "Developer", 1200.00, "3899241", "Atkinson road");

		employeeRepository.saveEmployee(employee1);

		var employee2 = new Employee(2L, "Carlos", "Developer", 1200.00, "3899241", "Atkinson road");

		// when
		var employeePhoneNumberAlreadyRegisteredRequest = put(EMPLOYEES_PATH + "/{id}", employee2.getId())
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(employee1));

		// then
		mockMvc.perform(employeePhoneNumberAlreadyRegisteredRequest).andExpect(status().isUnprocessableEntity())
				.andExpect(jsonPath("$.status").value("422"))
				.andExpect(jsonPath("$.error").value("Validation exception."));
	}

	@Test
	@DisplayName("Should return success when try to update a valid employee")
	void shouldReturnSuccessWhenTryToUpdateAValidTeacher() throws Exception {
		// given
		var employee1 = new Employee(1L, "Lucas", "Developer", 1200.00, "389924199", "Atkinson road");
		employeeRepository.saveEmployee(employee1);

		// when
		var employeeRequest = put(EMPLOYEES_PATH + "/{id}", employee1.getId()).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(employee1));

		// then
		mockMvc.perform(employeeRequest).andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value(employee1.getName()))
				.andExpect(jsonPath("$.phoneNumber").value(employee1.getPhoneNumber()));
	}

	/*
	 * DELETE EMPLOYEE 1 - Error when try to delete non-existent employee 2 -
	 * No-Content when try to delete existent employee
	 */

	@Test
	@DisplayName("Should return error when try to delete non-existent employee")
	void shouldReturnErrorWhenTryToDeleteNonExistentTeacher() throws Exception {
		Integer nonExistingId = 99;
		// when
		var employeeRequest = delete(EMPLOYEES_PATH + "/{id}", nonExistingId);

		// then
		mockMvc.perform(employeeRequest).andExpect(status().isNotFound()).andExpect(jsonPath("$.status").value("404"))
				.andExpect(jsonPath("$.error").value("Entity not found exception."));
	}

	    @Test
	    @DisplayName("Should return no-content when try to delete a existent employee")
	    void shouldReturnNoContentWhenTryToDeleteAExistentTeacher() throws Exception {
	        // given
	    	var employee1 = new Employee(1L, "Lucas", "Developer", 1200.00, "389924165", "Atkinson road");
	    	employeeRepository.saveEmployee(employee1);

	        // when
	        var employeeRequest = delete(EMPLOYEES_PATH + "/{id}", employee1.getId());

	        // then
	        mockMvc.perform(employeeRequest)
	                .andExpect(status().isNoContent());
	    }

}
