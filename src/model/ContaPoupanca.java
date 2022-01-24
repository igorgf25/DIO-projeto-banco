package model;

public class ContaPoupanca extends Conta {

	public ContaPoupanca(Cliente cliente, Banco banco, int agencia) {
		super(cliente, banco, agencia);
	}

	@Override
	public void imprimirExtrato() {
		System.out.println("=== Extrato Conta Poupança ===");
		super.imprimirInfosComuns();
	}
}

