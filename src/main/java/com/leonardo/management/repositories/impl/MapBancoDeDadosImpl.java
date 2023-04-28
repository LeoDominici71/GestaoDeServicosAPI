package com.leonardo.management.repositories.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;

import com.leonardo.management.entities.Funcionario;
import com.leonardo.management.repositories.MapBancodeDados;

public class MapBancoDeDadosImpl implements MapBancodeDados{
	
	@Bean
    public Map<Long, Funcionario> funcionarios() {
        return new HashMap<>();
    }

}
