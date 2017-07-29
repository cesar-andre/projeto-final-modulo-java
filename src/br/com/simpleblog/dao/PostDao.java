package br.com.simpleblog.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.simpleblog.conf.Conexao;
import br.com.simpleblog.model.Post;

public class PostDao {
	
	Conexao conexao = new Conexao();

	@SuppressWarnings("unchecked")
	public List<Post> obterTodos() {
		EntityManager em = this.conexao.getConexao();
		ArrayList<Post> retorno = new ArrayList<Post>();
		
		em.getTransaction().begin();
		
		Query query = em.createQuery("SELECT p FROM Post p");
		retorno.addAll(query.getResultList());
		
		em.getTransaction().commit();
		em.close();
		
		return retorno;
	}

}
