package service;

import java.util.List;
import model.Cliente;

public class ClienteService {
	
	//pesquisa cliente
	public Cliente pesquisaCliente(List<Cliente> clientes, String cpf) {
		
		for(Cliente cliente: clientes) {
			if(cliente.getCpf().equalsIgnoreCase(cpf)) {
				return cliente;
			}
		}
		return null;
	}
	
	//funçãoo validadora de cpf
	public boolean validarCPF(String cpfRaw){
        StringBuilder cpf = new StringBuilder(cpfRaw);
        cpf = cpf.deleteCharAt(3);
        cpf = cpf.deleteCharAt(6);
        cpf = cpf.deleteCharAt(9);
        Integer test = Character.getNumericValue(cpf.charAt(10));
        Integer primDigito;
        Integer segDigito;
        
        
        //conferindo o tamanho da string enviada
        if(!test.equals(-1)) {
            
            //calculando primeiro digito
            int totalSoma = 0;
            for(int i = 0; i < 9; i++) {
                try {
                    int formatNumero = Character.getNumericValue(cpf.charAt(i));
                    totalSoma += formatNumero * (i+1);
                } catch(Error err) {
                    return false;
                }
            }
            Integer restoDivisao = totalSoma % 11;
            if (restoDivisao.equals(10)) {
                primDigito = 0;
            } else {
                primDigito = restoDivisao;
            }
            
            //calculando segundo digito
            totalSoma = 0;
            int index = 0;
            for(int i = 11; i > 2; i--) {
                try {
                    int formatNumero = Character.getNumericValue(cpf.charAt(index));
                    totalSoma += formatNumero * (i);
                    index++;
                } catch(Error err) {
                    return false;
                }
            }
            totalSoma += primDigito * 2;
            restoDivisao = (totalSoma * 10) % 11;
            if (restoDivisao.equals(10)) {
                segDigito = 0;
            } else {
                segDigito = restoDivisao;
            }
            
            //conferindo os digitos e retornando verdadeiro ou falso
            Integer posi10 = Character.getNumericValue(cpf.charAt(9));
            Integer posi11 = Character.getNumericValue(cpf.charAt(10));
            if(posi10.equals(primDigito) && posi11.equals(segDigito)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
}
