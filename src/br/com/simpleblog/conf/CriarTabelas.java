package br.com.simpleblog.conf;

import javax.persistence.EntityManager;

public class CriarTabelas {

	public static void main(String[] args) {
		Conexao conexao = new Conexao();
		EntityManager manager = conexao.getConexao();
	}

}
