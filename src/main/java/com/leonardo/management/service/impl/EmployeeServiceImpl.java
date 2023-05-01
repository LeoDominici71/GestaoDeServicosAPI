package com.leonardo.management.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.leonardo.management.entities.Employee;
import com.leonardo.management.entities.dto.EmployeeDTO;
import com.leonardo.management.repositories.EmployeeDatabase;
import com.leonardo.management.service.EmployeeService;
import com.leonardo.management.service.exceptions.DuplicatedEmployeeException;
import com.leonardo.management.service.exceptions.EmployeeNotFoundException;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static final String EMPLOYEE_NOT_FOUND_MESSAGE = "Employee not found.";
	private static final String DUPLICATED_EMPLOYEE_MESSAGE = "Employee already exist.";

	private List<Employee> emp = new ArrayList<>();

	private final EmployeeDatabase employeeRepository;

	public EmployeeServiceImpl(EmployeeDatabase employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public List<EmployeeDTO> getEmployee() {
		return employeeRepository.getEmployee().values().stream().map(EmployeeDTO::new).collect(Collectors.toList());
	}

	public EmployeeDTO getEmployeeById(Long id) {
		return employeeRepository.getEmployee().entrySet().stream().filter(entry -> entry.getKey().equals(id))
				.map(Map.Entry::getValue).map(EmployeeDTO::new).findFirst()
				.orElseThrow(() -> new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND_MESSAGE));
	}

	@Override
	public EmployeeDTO postEmployee(EmployeeDTO employeeDTO) {
		if (employeeAlreadyExists(employeeDTO)) {
			throw new DuplicatedEmployeeException(DUPLICATED_EMPLOYEE_MESSAGE);
		}

		Employee employee = new Employee();
		copyDtoToEntityForSave(employeeDTO, employee);
		employeeRepository.saveEmployee(employee);
		return new EmployeeDTO(employee);
	}

	@Override
	public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {

		Employee entity = employeeRepository.getEmployee().entrySet().stream()
				.filter(entry -> entry.getKey().equals(id)).map(Map.Entry::getValue).findFirst()
				.orElseThrow(() -> new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND_MESSAGE));

		copyDtoToEntity(employeeDTO, entity);
		employeeRepository.updateEmployee(entity);

		return new EmployeeDTO(entity);

	}

	@Override
	public void deleteEmployee(Long id) {
		var employeeExists = getEmployeeById(id);
		employeeRepository.deleteEmployee(employeeExists.getId());
	}

	private boolean employeeAlreadyExists(EmployeeDTO employee) {
		return employeeRepository.getEmployee().values().stream()
				.anyMatch(f -> f.getPhoneNumber().equals(employee.getPhoneNumber()));
	}

	private void copyDtoToEntity(EmployeeDTO dto, Employee entity) {

		entity.setName(dto.getName());
		entity.setDesignation(dto.getDesignation());
		entity.setSalary(dto.getSalary());
		entity.setPhoneNumber(dto.getPhoneNumber());
		entity.setAddress(dto.getAddress());
	}

	private void copyDtoToEntityForSave(EmployeeDTO dto, Employee entity) {

		if (emp.isEmpty()) {
			entity.setId(1L);
		} else {

			entity.setId(emp.size() + 1L);

		}

		entity.setName(dto.getName());
		entity.setDesignation(dto.getDesignation());
		entity.setSalary(dto.getSalary());
		entity.setPhoneNumber(dto.getPhoneNumber());
		entity.setAddress(dto.getAddress());

		emp.add(entity);
	}

}
