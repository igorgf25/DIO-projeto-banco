package model;

import interfaces.IConta;

public abstract class Conta implements IConta {
	
	private static int SEQUENCIAL = 1;

	private int agencia;
	private int numero;
	private double saldo;
	private Cliente cliente;
	private Banco banco;

	public Conta(Cliente cliente, Banco banco, int agencia) {
		this.numero = SEQUENCIAL++;
		this.cliente = cliente;
		this.agencia = agencia;
		this.banco = banco;
		linksConta(banco, cliente);
	}

	//Criando os laços da conta
	public void linksConta(Banco banco, Cliente cliente) {
		banco.addConta(this);
		cliente.addConta(this);
	}

	@Override
	public void sacar(double valor) {
		saldo -= valor;
	}

	@Override
	public void depositar(double valor) {
		saldo += valor;
	}

	@Override
	public void transferir(double valor, IConta contaDestino) {
		this.sacar(valor);
		contaDestino.depositar(valor);
	}

	public int getAgencia() {
		return agencia;
	}

	public int getNumero() {
		return numero;
	}

	public double getSaldo() {
		return saldo;
	}

	protected void imprimirInfosComuns() {
		System.out.println(String.format("Titular: %s", this.cliente.getNome()));
		System.out.println(String.format("Agencia: %d", this.agencia));
		System.out.println(String.format("Numero: %d", this.numero));
		System.out.println(String.format("Saldo: %.2f", this.saldo));
	}
}
