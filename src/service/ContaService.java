package service;

import model.Banco;
import model.Cliente;
import model.Conta;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContaService {
    Scanner entrada = new Scanner(System.in);
    BancoService bancoService = new BancoService();
    ClienteService clienteService = new ClienteService();

    public Conta encontrarConta(int numeroConta, List<Conta> contas) {
        for (Conta conta : contas) {
            if(conta.getNumero() == numeroConta) {
                return conta;
            }
        }
        return null;
    }

	public List validaConta(List<Banco> bancos, List<Cliente> clientes){
        String nomeBanco;
        String cpfCliente;
        Cliente cliente;
        int agencia;

        System.out.println("Nome do banco: ");
        nomeBanco = entrada.next();
        Banco banco = bancoService.pesquisaBanco(bancos, nomeBanco);
        if(banco == null) {
            System.out.println("Banco não existe");
            return null;
        }
        System.out.println("CPF do cliente: ");
        cpfCliente = entrada.next();
        if(clienteService.validarCPF(cpfCliente)) {
            cliente = clienteService.pesquisaCliente(clientes, cpfCliente);
            if(cliente == null) {
                System.out.println("Cliente não existe");
                return null;
            }
        } else {
            System.out.println("CPF invalido");
            return null;
        }

        System.out.println("Numero da agencia: ");
        agencia = entrada.nextInt();

        List dados = new ArrayList();
        dados.add(cliente);
        dados.add(banco);
        dados.add(agencia);
        return dados;
    }
}
