package com.leonardo.management.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.leonardo.management.entities.Employee;
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
	public List<Employee> getEmployee() {
		return new ArrayList<>(employeeRepository.getEmployee().values());
	}

	@Override
	public Employee getEmployeeById(Integer id) {
		return employeeRepository.getEmployee().entrySet().stream().filter(entry -> entry.getKey().equals(id))
				.map(Map.Entry::getValue).findFirst()
				.orElseThrow(() -> new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND_MESSAGE));
	}

	@Override
	public void postEmployee(Employee funcionario) throws DuplicatedEmployeeException {
		try {
			if (funcionarioAlreadyExists(funcionario)) {
				throw new DuplicatedEmployeeException(DUPLICATED_EMPLOYEE_MESSAGE);
			} else {
				employeeRepository.getEmployee().put(funcionario.getId(), funcionario);
			}
		} catch (NullPointerException e) {
			throw new NullPointerExceptions(NULL_FIELD_MESSAGE);
		}
	}

	@Override
	public void updateEmployee(Employee funcionario) throws DuplicatedEmployeeException {
		if (funcionarioAlreadyExists(funcionario)) {
			employeeRepository.getEmployee().put(funcionario.getId(), funcionario);
		} else {
			new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND_MESSAGE);
		}
	}

	@Override
	public void deleteEmployee(Integer id) {
		boolean removed = employeeRepository.getEmployee().entrySet().removeIf(entry -> entry.getKey().equals(id));
		if (!removed) {
			throw new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND_MESSAGE);
		}
	}

	private boolean funcionarioAlreadyExists(Employee funcionario) {
		return employeeRepository.getEmployee().values().stream().anyMatch(f -> f.getId().equals(funcionario.getId()));
	}
}
