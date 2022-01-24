package model;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

	private String nome;
	private String cpf;
	private String celular;
	private List<Conta> contas;

	public Cliente(String nome, String cpf, String celular) {
		this.nome = nome;
		this.cpf = cpf;
		this.celular = celular;
		contas = new ArrayList<Conta>();
	}
	
	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public String getCelular() {
		return celular;
	}
	
	public void addConta(Conta conta) {
		contas.add(conta);
	}

	public List<Conta> getContas() {
		return contas;
	}

	
}