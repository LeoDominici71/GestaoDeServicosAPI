package com.leonardo.management.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.leonardo.management.entities.Funcionario;
import com.leonardo.management.exception.DuplicatedEmployeeException;
import com.leonardo.management.exception.EmployeeNotFoundException;
import com.leonardo.management.exception.NullPointerExceptions;
import com.leonardo.management.repositories.FuncionariosDatabase;
import com.leonardo.management.service.FuncionarioService;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

	private static final String EMPLOYEE_NOT_FOUND_MESSAGE = "Employee not found.";
	private static final String DUPLICATED_EMPLOYEE_MESSAGE = "Employee already exist.";
	private static final String NULL_FIELD_MESSAGE = "There are null fields.";

	private final FuncionariosDatabase funcionarioRepository;

	public FuncionarioServiceImpl(FuncionariosDatabase funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}

	@Override
	public List<Funcionario> getFuncionario() {
		return new ArrayList<>(funcionarioRepository.getFuncionarios().values());
	}

	@Override
	public Funcionario getFuncionarioById(Integer id) {
		return funcionarioRepository.getFuncionarios().entrySet().stream().filter(entry -> entry.getKey().equals(id))
				.map(Map.Entry::getValue).findFirst()
				.orElseThrow(() -> new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND_MESSAGE));
	}

	@Override
	public void postFuncionario(Funcionario funcionario) throws DuplicatedEmployeeException {
		try{if (funcionarioAlreadyExists(funcionario)) {
			throw new DuplicatedEmployeeException(DUPLICATED_EMPLOYEE_MESSAGE);
		} else {
			funcionarioRepository.getFuncionarios().put(funcionario.getId(), funcionario);
		}
		}catch(NullPointerException e){
			throw new NullPointerExceptions(NULL_FIELD_MESSAGE);
			}
	}

	@Override
	public void updateFuncionario(Funcionario funcionario) throws DuplicatedEmployeeException {
		if (funcionarioAlreadyExists(funcionario)) {
			funcionarioRepository.getFuncionarios().put(funcionario.getId(), funcionario);
		} else {
			new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND_MESSAGE);
		}
	}

	@Override
	public void deleteFuncionario(Integer id) {
		boolean removed = funcionarioRepository.getFuncionarios().entrySet()
				.removeIf(entry -> entry.getKey().equals(id));
		if (!removed) {
			throw new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND_MESSAGE);
		}
	}

	private boolean funcionarioAlreadyExists(Funcionario funcionario) {
		return funcionarioRepository.getFuncionarios().values().stream()
				.anyMatch(f -> f.getId().equals(funcionario.getId()));
	}
}
