package br.com.simpleblog.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.simpleblog.dao.PostDao;
import br.com.simpleblog.model.Post;

@ManagedBean
@ViewScoped
public class HomeController {
	
	PostDao postDao = new PostDao();
	private List<Post> postsNaPagina = new ArrayList<>();
	
	
	@PostConstruct
	public void postContruct() {
		getPostsNaPagina().addAll(postDao.obterTodos());
	}
	
	public String fazerLogin(){
		return "login?facesRedirect=true";
	}


	public List<Post> getPostsNaPagina() {
		return postsNaPagina;
	}


	public void setPostsNaPagina(List<Post> postsNaPagina) {
		this.postsNaPagina = postsNaPagina;
	}
	
}
