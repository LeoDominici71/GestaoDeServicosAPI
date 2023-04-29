package com.leonardo.management.repositories.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.leonardo.management.entities.Funcionario;
import com.leonardo.management.repositories.FuncionariosDatabase;

@Repository
public class FuncionarioDatabaseImpl implements FuncionariosDatabase {

	private Map<Integer, Funcionario> funcionarios;

	public FuncionarioDatabaseImpl() {
		this.funcionarios = new HashMap<>();
	}

	public Map<Integer, Funcionario> getFuncionarios() {
		return funcionarios;
	}

}
