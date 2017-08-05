package br.com.simpleblog.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.simpleblog.dao.UsuarioDao;
import br.com.simpleblog.model.Usuario;
import br.com.simpleblog.viewmodel.UsuarioViewModel;

@ManagedBean
@SessionScoped
public class LoginController {

	private UsuarioViewModel usuarioViewModel = new UsuarioViewModel();
	private Usuario usuarioLogado;
	private boolean logado;
	private UsuarioDao usuarioDao = new UsuarioDao();
	
	
	public String entrar() {
		usuarioLogado = usuarioDao.validarUsuario(usuarioViewModel.getEmail(), usuarioViewModel.getSenha());
		
		if (null != usuarioLogado) {
			logado = true;
			return "admin/posts.xhtml?faces-redirect=true";
		}
		
		FacesMessage msg = new FacesMessage("Usuario ou senha incorretos.!", "INFO MSG");
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return "";
	}
	
	public String sair() {
		logado = false;

		FacesMessage msg = new FacesMessage("Sessão encerrada!", "INFO MSG");
		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, msg);

		return "/index?faces-redirect=true";
	}
	
	public UsuarioViewModel getUsuarioViewModel() {
		return usuarioViewModel;
	}

	public void setUsuarioViewModel(UsuarioViewModel usuarioViewModel) {
		this.usuarioViewModel = usuarioViewModel;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public boolean isLogado() {
		return logado;
	}

	public void setLogado(boolean logado) {
		this.logado = logado;
	}

	public UsuarioDao getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}
}
