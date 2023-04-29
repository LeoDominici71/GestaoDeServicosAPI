package com.leonardo.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.leonardo.management.service.impl.EmployeeServiceImpl;

@SpringBootApplication
public class ManagementApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ManagementApplication.class, args);
	}

	@Autowired
	EmployeeServiceImpl service;
	
	@Override
	public void run(String... args) throws Exception {

//		System.out.println("========================== Salvando =============================");
//
//		service.postFuncionario(new Funcionario(1L, "Isadora", "Porteira", 1200.00, "123456789", "Rua Itarare"));
//		service.postFuncionario(new Funcionario(2L, "Felipe", "Desenvolvedor", 5200.00, "123456789", "Rua Augusta"));
//		service.postFuncionario(new Funcionario(3L, "Isadora", "Porteira", 1200.00, "123456789", "Rua Itarare"));
//		service.postFuncionario(new Funcionario(4L, "Isadora", "Porteira", 1200.00, "123456789", "Rua Itarare"));
//		service.postFuncionario(new Funcionario(5L, "Isadora", "Porteira", 1200.00, "123456789", "Rua Itarare"));
//		System.out.println();
//		System.out.println("========================== Find All =============================");
//
//		List<Funcionario> todosFuncionarios = service.getFuncionario();
//
//		todosFuncionarios.forEach(System.out::println);
//
//		System.out.println("========================== Update =============================");
//		Funcionario funcionarioAtualizado = service.getFuncionarioById(1L);
//		funcionarioAtualizado = new Funcionario(1L, "Isadora", "Porteira", 1500.00, "123456789", "Rua Itarare");
//		service.updateFuncionario(funcionarioAtualizado);
//		System.out.println("Funcionário atualizado: " + service.getFuncionarioById(1L));
//		System.out.println();
//		
//		System.out.println("========================== Delete =============================");
//		service.deleteFuncionario(2L);
//		System.out.println("Funcionários restantes:");
//		List<Funcionario> funcionariosRestantes = service.getFuncionario();
//		funcionariosRestantes.forEach(System.out::println);

	}

}
