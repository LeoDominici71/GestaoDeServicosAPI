package com.leonardo.management.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.leonardo.management.entities.Funcionario;
import com.leonardo.management.service.FuncionarioService;

@Service
public class FuncionarioServiceImpl implements FuncionarioService{

	private final Map<Long, Funcionario> funcionarios;

	public FuncionarioServiceImpl(Map<Long, Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public List<Funcionario> obterTodosFuncionarios() {
		return new ArrayList<>(funcionarios.values());
	}

	public Funcionario obterFuncionarioPorId(Long id) {
		return funcionarios.get(id);
	}

	public void adicionarFuncionario(Funcionario funcionario) {
		if (funcionario.getId() == null || funcionarios.containsKey(funcionario.getId())) {
			throw new IllegalArgumentException("ID inválido ou já existente.");
		}
		funcionarios.put(funcionario.getId(), funcionario);
	}

	public void atualizarFuncionario(Funcionario funcionario) {
		if (funcionario.getId() == null || !funcionarios.containsKey(funcionario.getId())) {
			throw new IllegalArgumentException("Funcionário não encontrado ou ID inválido.");
		}
		funcionarios.put(funcionario.getId(), funcionario);
	}

	public void removerFuncionario(Long id) {
		if (!funcionarios.containsKey(id)) {
			throw new IllegalArgumentException("Funcionário não encontrado.");
		}
		funcionarios.remove(id);
	}

}
