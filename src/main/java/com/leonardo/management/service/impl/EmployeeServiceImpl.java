package com.leonardo.management.service.impl;

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
import com.leonardo.management.service.exceptions.NullPointerExceptions;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static final String EMPLOYEE_NOT_FOUND_MESSAGE = "Employee not found.";
	private static final String DUPLICATED_EMPLOYEE_MESSAGE = "Employee already exist.";
	private static final String NULL_FIELD_MESSAGE = "There are null fields.";

	private final EmployeeDatabase employeeRepository;

	public EmployeeServiceImpl(EmployeeDatabase employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public List<EmployeeDTO> getEmployee() {
		return employeeRepository.getEmployee().values().stream().map(EmployeeDTO::new).collect(Collectors.toList());
	}

	@Override
	public EmployeeDTO getEmployeeById(Integer id) {
		Employee entity = employeeRepository.getEmployee().entrySet().stream()
				.filter(entry -> entry.getKey().equals(id)).map(Map.Entry::getValue).findFirst()
				.orElseThrow(() -> new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND_MESSAGE));

		return new EmployeeDTO(entity);
	}

	@Override
	public EmployeeDTO postEmployee(EmployeeDTO employeeDTO) {
		try {
			if (funcionarioAlreadyExists(employeeDTO)) {
				throw new DuplicatedEmployeeException(DUPLICATED_EMPLOYEE_MESSAGE);
			}

			Employee employee = new Employee(employeeDTO.getId(), employeeDTO.getName(), employeeDTO.getDesignation(),
					employeeDTO.getSalary(), employeeDTO.getNumber(), employeeDTO.getAddress());
			employeeRepository.saveEmployee(employee);
			EmployeeDTO savedEmployeeDTO = copyEntityToDTO(employee);
			return savedEmployeeDTO;
		} catch (NullPointerException e) {
			throw new NullPointerExceptions(NULL_FIELD_MESSAGE);
		}
	}

	@Override
	public EmployeeDTO updateEmployee(Integer id, EmployeeDTO employee) throws DuplicatedEmployeeException {
		Employee entity = employeeRepository.getEmployee().entrySet().stream()
				.filter(entry -> entry.getKey().equals(id)).map(Map.Entry::getValue).findFirst()
				.orElseThrow(() -> new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND_MESSAGE));

		try {

			copyDtoToEntity(employee, entity);
			employeeRepository.updateEmployee(entity);

			return new EmployeeDTO(entity);

		} catch (NullPointerException e) {
			throw new NullPointerExceptions(NULL_FIELD_MESSAGE);
		}
	}

	@Override
	public void deleteEmployee(Integer id) {
		employeeRepository.getEmployee().keySet().stream().filter(key -> key.equals(id)).findFirst()
				.orElseThrow(() -> new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND_MESSAGE));
		employeeRepository.getEmployee().remove(id);
	}

	private boolean funcionarioAlreadyExists(EmployeeDTO employee) {
		return employeeRepository.getEmployee().values().stream().anyMatch(f -> f.getId().equals(employee.getId()));
	}

	private EmployeeDTO copyEntityToDTO(Employee entity) {
		EmployeeDTO dto = new EmployeeDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setDesignation(entity.getDesignation());
		dto.setSalary(entity.getSalary());
		dto.setNumber(entity.getNumber());
		dto.setAddress(entity.getAddress());
		return dto;
	}
	
	private void copyDtoToEntity(EmployeeDTO dto, Employee entity) {
		// TODO Auto-generated method stub

		entity.setName(dto.getName());
		entity.setDesignation(dto.getDesignation());
		entity.setSalary(dto.getSalary());
		entity.setNumber(dto.getNumber());
		entity.setAddress(dto.getAddress());
	}

}
