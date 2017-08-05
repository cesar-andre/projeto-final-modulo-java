package br.com.simpleblog.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;

import br.com.simpleblog.dao.CategoriaDao;
import br.com.simpleblog.dao.PostDao;
import br.com.simpleblog.model.Categoria;
import br.com.simpleblog.model.Post;

@ManagedBean
@SessionScoped
public class PostController {
	
	@ManagedProperty("#{loginController}")
	private LoginController loginController;
	
	private PostDao postDao = new PostDao();
	private CategoriaDao categoriaDao = new CategoriaDao();
	private List<Post> postsDoUsuario = new ArrayList<>();
	
	private Post postForm = new Post();
	private List<Categoria> categorias = new ArrayList<>();
	
	@PostConstruct
	public void postConstruct(){
		getCategorias().addAll(categoriaDao.obterTodos());
	}
	
	public List<Post> obterListaAtualizada(){
		getPostsDoUsuario().clear();
		getPostsDoUsuario().addAll(postDao.obterPorUsuario(getLoginController().getUsuarioLogado().getId()));
		return getPostsDoUsuario();
	}
	
	public void obterPostDaLista(Integer idPost){
		postForm = postDao.obterPorId(idPost);
	}
	
	public String novoPost(){
		this.postForm = new Post();
		getCategorias().clear();
		getCategorias().addAll(categoriaDao.obterTodos());
		return "alter-post?faces-redirect=true";
	}
	
	public String salvarPost(){
		postForm.setAutor(loginController.getUsuarioLogado());
		postDao.salvar(postForm);
				
		FacesMessage msg = new FacesMessage("Post salvo com sucesso!", "INFO MSG");
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, msg);
 
		return "posts?faces-redirect=true";
	}
	
	
	public String atualizarPost(Post post){
		this.postForm = post;
		getCategorias().clear();
		getCategorias().addAll(categoriaDao.obterTodos());
		return "alter-post?faces-redirect=true"; 
	}
	
	public void removerPost(Post post){
		postDao.remover(post);
	}
	
	public void handleFileUpload(FileUploadEvent event) {
		try {
			byte[] imagem = IOUtils.toByteArray(event.getFile().getInputstream());
			
			this.postForm.setImagem(imagem);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Post> getPostsDoUsuario() {
		return postsDoUsuario;
	}

	public void setPostsDoUsuario(List<Post> postsDoUsuario) {
		this.postsDoUsuario = postsDoUsuario;
	}

	public LoginController getLoginController() {
		return loginController;
	}

	public void setLoginController(LoginController loginController) {
		this.loginController = loginController;
	}

	public Post getPostForm() {
		return postForm;
	}

	public void setPostForm(Post postForm) {
		this.postForm = postForm;
	}


	public List<Categoria> getCategorias() {
		return categorias;
	}


	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	
	

}
