package com.leonardo.management.repositories;

import java.util.Map;

import com.leonardo.management.entities.Funcionario;

public interface FuncionariosDatabase {
	
	Map<Long, Funcionario> getFuncionarios();

}
