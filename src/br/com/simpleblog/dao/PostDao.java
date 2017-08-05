package br.com.simpleblog.dao;

import java.util.ArrayList;
import java.util.Collection;
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

	@SuppressWarnings("unchecked")
	public List<Post> obterPorUsuario(Integer idUsuario) {
		EntityManager em = this.conexao.getConexao();

		ArrayList<Post> retorno = new ArrayList<>();

		em.getTransaction().begin();

		Query query = em.createQuery("Select p FROM Post p WHERE p.autor.id =:idUsuario");
		query.setParameter("idUsuario", idUsuario);

		retorno.addAll(query.getResultList());

		em.getTransaction().commit();
		em.close();

		return retorno;

	}

	public Post salvar(Post post) {
		EntityManager em = this.conexao.getConexao();

		em.getTransaction().begin();

		em.merge(post);

		em.getTransaction().commit();
		em.close();

		return post;

	}

	public Post obterPorId(Integer idPost) {
		EntityManager em = this.conexao.getConexao();
		Post retorno = new Post();

		em.getTransaction().begin();

		retorno = em.find(Post.class, idPost);
		em.getTransaction().commit();
		em.close();
		
		return retorno;

	}

	public void remover(Post postForm) {
		EntityManager em = this.conexao.getConexao();
		
		em.getTransaction().begin();
		
		Query query = em.createQuery("DELETE Post p where p.id = :idPost");
		query.setParameter("idPost", postForm.getId());
		query.executeUpdate();
		
		em.getTransaction().commit();
		em.close();
		
	}

}
