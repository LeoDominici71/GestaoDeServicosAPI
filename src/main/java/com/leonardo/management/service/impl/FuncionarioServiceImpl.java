package com.leonardo.management.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.leonardo.management.entities.Funcionario;
import com.leonardo.management.exception.DuplicatedEmployeeException;
import com.leonardo.management.exception.EmployeeNotFoundException;
import com.leonardo.management.service.FuncionarioService;



@Service
public class FuncionarioServiceImpl implements FuncionarioService {
	
	private static final String EMPLOYEE_NOT_FOUND_MESSAGE = "Funcionário não encontrado.";
	private static final String DUPLICATED_EMPLOYEE_MESSAGE = "Funcionário já existente.";

	private final Map<Long, Funcionario> funcionarioRepository;

	public FuncionarioServiceImpl(Map<Long, Funcionario> funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}

	public List<Funcionario> getFuncionario() {
        return funcionarioRepository.values()
                .stream()
                .collect(Collectors.toList());
    }

	public Funcionario getFuncionarioById(Long id) {
		return funcionarioRepository.entrySet().stream()
				.filter(entry -> entry.getKey().equals(id))
				.map(Map.Entry::getValue)
				.findFirst()
				.orElseThrow(() -> new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND_MESSAGE));
	}


	public void postFuncionario(Funcionario funcionario) throws DuplicatedEmployeeException {
		if (funcionarioAlreadyExists(funcionario)) {
	        throw new DuplicatedEmployeeException(DUPLICATED_EMPLOYEE_MESSAGE);
	    } else {
	        funcionarioRepository.put(funcionario.getId(), funcionario);
	    }
	}

	public void updateFuncionario(Funcionario funcionario) throws DuplicatedEmployeeException {
		if (funcionarioAlreadyExists(funcionario)) {
	        funcionarioRepository.put(funcionario.getId(), funcionario);
	    } else {
	        throw new DuplicatedEmployeeException(DUPLICATED_EMPLOYEE_MESSAGE);
	    }
	}

	public void deleteFuncionario(Long id) {
        boolean removed = funcionarioRepository.entrySet()
                .removeIf(entry -> entry.getKey().equals(id));
        if (!removed) {
            throw new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND_MESSAGE);
        }
    }
	
	private boolean funcionarioAlreadyExists(Funcionario funcionario) {
	    return funcionarioRepository.values().stream()
	            .anyMatch(f -> f.getId().equals(funcionario.getId()));
	}

}
