package service;

import java.util.List;

import model.Banco;

public class BancoService {

	public BancoService() {}
	
	public Banco pesquisaBanco(List<Banco> bancos, String nome) {
		
		for(Banco banco:bancos) {
			if(banco.getNome().equalsIgnoreCase(nome)) {
				return banco;
			}
		}
		return null;
	}
}
