package br.com.simpleblog.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.simpleblog.dao.CategoriaDao;
import br.com.simpleblog.model.Categoria;

@ManagedBean
@SessionScoped
public class CategoriaController {
	
	private CategoriaDao categoriaDao = new CategoriaDao();
	private Categoria categoriaForm = new Categoria();
	
	
	public List<Categoria> obterListaAtualizada() {
		return categoriaDao.obterTodos();
	}
	
	public String novaCategoria(){
		this.categoriaForm = new Categoria();
		return "alter-categoria?faces-redirect=true";
	}
	
	public String salvarCategoria(){
		
		categoriaDao.salvar(categoriaForm);
				
		FacesMessage msg = new FacesMessage("Categoria salva com sucesso!", "INFO MSG");
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, msg);
 
		return "categorias?faces-redirect=true";
	}
	
	public void removerCategoria(Categoria categoria) {
		categoriaDao.remover(categoria);
	}
	
	public String atualizarCategoria(Categoria categoria) {
		this.categoriaForm = categoria;
		return "alter-categoria?faces-redirect=true";
	}


	public Categoria getCategoriaForm() {
		return categoriaForm;
	}


	public void setCategoriaForm(Categoria categoriaForm) {
		this.categoriaForm = categoriaForm;
	}

}
