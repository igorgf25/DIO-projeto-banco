import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Banco;
import model.Cliente;
import model.Conta;
import model.ContaCorrente;
import model.ContaPoupanca;
import service.BancoService;
import service.ClienteService;
import service.ContaService;

public class Application {
	
	 public static void main(String[] args) {
	     int opcao;
	     Scanner entrada = new Scanner(System.in);
	     BancoService bancoService = new BancoService();
		 ContaService contaService = new ContaService();
	     ClienteService clienteService = new ClienteService();
	     
	     //Listas
	     List<Banco> bancos = new ArrayList<Banco>();
		 List<Conta> contas = new ArrayList<Conta>();
	     List<Cliente> clientes = new ArrayList<Cliente>();
	    
	     do{
	         System.out.println("\n1 - Registrar Banco");
	         System.out.println("2 - Registrar Cliente");
	         System.out.println("3 - Registrar Conta Corrente");
	         System.out.println("4 - Registrar Conta Poupança");
	         System.out.println("5 - Depositar");
	         System.out.println("6 - Sacar");
	         System.out.println("7 - Transferir");
	         System.out.println("8 - Consultar Saldo");
	         System.out.println("9 - Sair");
	         System.out.print("\n\tDigite a opcao: ");
	         opcao = entrada.nextInt();
	             
	         switch(opcao){
	             case 1 : 
	                 String nome;
	                 System.out.println("Nome do banco: ");
	                 nome = entrada.next();
	                 Banco banco = bancoService.pesquisaBanco(bancos, nome);
	                 
	                 if(banco == null) {
	                	 banco = new Banco(nome);
	                	 bancos.add(banco);
	                 } else {
	                	 System.out.println("Nome do banco ja existe.");
	                 }
	                 
	                 break;
	             case 2 :
	                 String nome1;
	                 String cpf;
	                 String celular;
	                 
	                 System.out.println("Nome: ");
	                 nome1 = entrada.next();
	                 System.out.println("Celular: ");
	                 celular = entrada.next();
	                 System.out.println("CPF: ");
	                 cpf = entrada.next();
	                 if(clienteService.validarCPF(cpf)) {
	                	 if(clienteService.pesquisaCliente(clientes, cpf) == null) {
	                		 Cliente cliente = new Cliente(nome1, cpf, celular);
	                		 clientes.add(cliente);
	                	 } else {
	                		 System.out.println("CPF já cadastrado.");
	                	 }
	                 } else {
	                	 System.out.println("CPF invalido.");
	                 }
	                 
	                 break;
	             case 3 :

					 List dadosCorrente = contaService.validaConta(bancos, clientes);
					 if (dadosCorrente != null) {
						 Conta contaCorrente = new ContaCorrente((Cliente) dadosCorrente.get(0),(Banco) dadosCorrente.get(1),(int) dadosCorrente.get(2));
						 contas.add(contaCorrente);
					 }
	                 break;
	             case 4 :
	            	 
	            	 List dadosPoupanca = contaService.validaConta(bancos, clientes);
					 if (dadosPoupanca != null) {
						 Conta contaPoupanca = new ContaPoupanca((Cliente) dadosPoupanca.get(0),(Banco) dadosPoupanca.get(1),(int) dadosPoupanca.get(2));
						 contas.add(contaPoupanca);
					 }
	                 break;
	             case 5 :
					 int numeroContaD;
					 Double valorDeposito;
					 System.out.println("Numero da conta");
					 numeroContaD = entrada.nextInt();

					 System.out.println("Valor do deposito");
					 valorDeposito = entrada.nextDouble();

					 Conta contaDeposito = contaService.encontrarConta(numeroContaD, contas);

					 if (contaDeposito != null) {
						 contaDeposito.depositar(valorDeposito);
					 } else {
						 System.out.println("Numero de conta não existe");
					 }
					 break;
	             case 6 :
					 int numeroContaS;
					 double valorSaque;
					 System.out.println("Numero da conta: ");
					 numeroContaS = entrada.nextInt();

					 System.out.println("Valor do : ");
					 valorSaque = entrada.nextDouble();

					 Conta contaSaque = contaService.encontrarConta(numeroContaS, contas);

					 if (contaSaque != null) {
						 contaSaque.sacar(valorSaque);
					 } else {
						 System.out.println("Numero de conta não existe");
					 }
					 break;
	             case 7 :
					 int numeroContaDestino;
					 int numeroContaOrigem;
					 Double valorTransferencia;

					 System.out.println("Digite o numero da conta de: ");
					 numeroContaOrigem = entrada.nextInt();
					 System.out.println("Digite o numero da conta de destino: ");
					 numeroContaDestino = entrada.nextInt();
					 System.out.println("Digite o valor da transferencia: ");
					 valorTransferencia = entrada.nextDouble();

					 Conta contaOrigem = contaService.encontrarConta(numeroContaOrigem, contas);
					 Conta contaDestino = contaService.encontrarConta(numeroContaDestino, contas);

					 if(contaOrigem != null && contaDestino != null) {
						 contaOrigem.transferir(valorTransferencia, contaDestino);
					 } else {
						 System.out.println("Numero da conta de origem ou destino não existe");
					 }
	             case 8 :
	            	 String cpfCliente;
	            	 System.out.println("CPF do cliente: ");
	            	 cpfCliente = entrada.next();
	            	 
	            	 if(clienteService.validarCPF(cpfCliente)) {
	                	 Cliente cliente = clienteService.pesquisaCliente(clientes, cpfCliente);
	                	 if(cliente != null) {
	                		 for(Conta conta : cliente.getContas()) {
	                			 conta.imprimirExtrato();
	                		 }
		                	 break;
	                	 } else {
	                		 System.out.println("Cliente não existe");
	                	 }
	                 } else {
	                	 System.out.println("CPF invalido");
	                	 break;
	                 }


         }
	         
	     }while (opcao < 9);
	 }
}
