package com.leonardo.management.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.leonardo.management.entities.Funcionario;
import com.leonardo.management.exception.DuplicatedEmployeeException;
import com.leonardo.management.exception.EmployeeNotFoundException;
import com.leonardo.management.service.FuncionarioService;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

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
				.orElseThrow(() -> new EmployeeNotFoundException("Funcionário não encontrado."));
	}


	public void postFuncionario(Funcionario funcionario) throws DuplicatedEmployeeException {
	    Optional<Funcionario> existingFuncionario = funcionarioRepository.values().stream()
	            .filter(f -> f.getId().equals(funcionario.getId()))
	            .findFirst();

	    if (existingFuncionario.isPresent()) {
	        throw new DuplicatedEmployeeException("Funcionario já existente.");
	    } else {
	        funcionarioRepository.put(funcionario.getId(), funcionario);
	    }
	}

	public void updateFuncionario(Funcionario funcionario) throws DuplicatedEmployeeException {
	    Optional<Funcionario> existingFuncionario = funcionarioRepository.values().stream()
	            .filter(f -> f.getId().equals(funcionario.getId()))
	            .findFirst();

	    if (existingFuncionario.isPresent()) {
	        funcionarioRepository.put(funcionario.getId(), funcionario);
	    } else {
	        throw new DuplicatedEmployeeException("Funcionário não encontrado ou ID inválido.");
	    }
	}

	public void deleteFuncionario(Long id) {
        boolean removed = funcionarioRepository.entrySet()
                .removeIf(entry -> entry.getKey().equals(id));
        if (!removed) {
            throw new EmployeeNotFoundException("Funcionário não encontrado.");
        }
    }

}
