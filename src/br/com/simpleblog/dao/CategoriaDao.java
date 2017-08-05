package br.com.simpleblog.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.simpleblog.conf.Conexao;
import br.com.simpleblog.model.Categoria;

public class CategoriaDao {
	Conexao conexao = new Conexao();

	@SuppressWarnings("unchecked")
	public List<Categoria> obterTodos() {
		EntityManager em = this.conexao.getConexao();
		ArrayList<Categoria> retorno = new ArrayList<>();
		em.getTransaction().begin();

		Query query = em.createQuery("SELECT c FROM Categoria c");
		retorno.addAll(query.getResultList());

		em.getTransaction().commit();
		em.close();

		return retorno;

	}

	public Categoria buscarCategoriaById(Integer idCategoria) {
		EntityManager em = this.conexao.getConexao();
		Categoria retorno = new Categoria();
		em.getTransaction().begin();

		Query query = em.createQuery("SELECT c FROM Categoria c WHERE c.id = :idCategoria");
		query.setParameter("idCategoria", idCategoria);

		retorno = (Categoria) query.getSingleResult();

		em.getTransaction().commit();
		em.close();

		return retorno;
	}

	public Categoria salvar(Categoria categoria) {
		EntityManager em = this.conexao.getConexao();

		em.getTransaction().begin();

		em.merge(categoria);

		em.getTransaction().commit();
		em.close();

		return categoria;

	}

	public void remover(Categoria categoria) {
		EntityManager em = this.conexao.getConexao();

		em.getTransaction().begin();

		Query query = em.createQuery("DELETE Categoria c where c.id = :idCategoria");
		query.setParameter("idCategoria", categoria.getId());
		query.executeUpdate();

		em.getTransaction().commit();
		em.close();

	}

}
