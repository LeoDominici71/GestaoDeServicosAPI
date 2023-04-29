package com.leonardo.management.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Funcionario {

	@NotNull
	private Integer id;
	@NotEmpty
	private String nome;
	@NotEmpty
	private String designacao;
	@NotNull
	private Double salario;
	@NotEmpty
	private String telefone;
	@NotEmpty
	private String endereco;

	public Funcionario() {
		// TODO Auto-generated constructor stub
	}

	public Funcionario(Integer id, String nome, String designacao, Double salario, String telefone, String endereco) {

		this.id = id;
		this.nome = nome;
		this.designacao = designacao;
		this.salario = salario;
		this.telefone = telefone;
		this.endereco = endereco;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDesignacao() {
		return designacao;
	}

	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	@Override
	public String toString() {
		return "Funcionario [id = " + id + ", nome = " + nome + ", designacao = " + designacao + ", salario = " + salario
				+ ", telefone = " + telefone + ", endereco = " + endereco + "]";
	}

}
