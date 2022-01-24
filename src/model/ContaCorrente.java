package model;

public class ContaCorrente extends Conta {

	public ContaCorrente(Cliente cliente, Banco banco, int agencia) {
		super(cliente, banco, agencia);
	}

	@Override
	public void imprimirExtrato() {
		System.out.println("=== Extrato Conta Corrente ===");
		super.imprimirInfosComuns();
	}
	
}
