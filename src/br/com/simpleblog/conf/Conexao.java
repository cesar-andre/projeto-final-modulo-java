package br.com.simpleblog.conf;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class Conexao {
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("SimpleBlog");

	public EntityManager getConexao(){ 		
	 return factory.createEntityManager();
	}
}
