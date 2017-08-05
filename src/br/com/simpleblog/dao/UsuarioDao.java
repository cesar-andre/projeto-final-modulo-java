package br.com.simpleblog.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.simpleblog.conf.Conexao;
import br.com.simpleblog.model.Usuario;

public class UsuarioDao {
	
	Conexao conexao = new Conexao();

	public Usuario validarUsuario(String email, String senha) {
		EntityManager em = this.conexao.getConexao();
		Usuario retorno = null;
		
		em.getTransaction().begin();
		
		Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.email = :email AND u.senha = :senha");
		query.setParameter("email", email);
		query.setParameter("senha", senha);
		retorno = (Usuario) query.getSingleResult();
		
		em.getTransaction().commit();
		em.close();
		
		return retorno;
		
	}

	public Usuario salvar(Usuario usuario) {
		EntityManager em = this.conexao.getConexao();

		em.getTransaction().begin();

		em.merge(usuario);

		em.getTransaction().commit();
		em.close();

		return usuario;
		
	}

}
