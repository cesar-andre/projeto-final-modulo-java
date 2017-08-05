package br.com.simpleblog.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.simpleblog.dao.UsuarioDao;
import br.com.simpleblog.model.Usuario;

@ManagedBean
@SessionScoped
public class UsuarioController {

	private Usuario usuarioForm = new Usuario();
	private UsuarioDao usuarioDao = new UsuarioDao();
	
	@ManagedProperty("#{loginController}")
	private LoginController loginController;
	
	
	public String editarUsuario(){
		this.usuarioForm = getLoginController().getUsuarioLogado();
		return "usuario?faces-redirect=true";
	}
	
	public String salvarUsuario() {
		usuarioDao.salvar(usuarioForm);
		
		FacesMessage msg = new FacesMessage("Usuario salvo com sucesso!", "INFO MSG");
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, msg);
 
		return "usuario?faces-redirect=true";
	}

	public Usuario getUsuarioForm() {
		return usuarioForm;
	}

	public void setUsuarioForm(Usuario usuarioForm) {
		this.usuarioForm = usuarioForm;
	}

	public LoginController getLoginController() {
		return loginController;
	}

	public void setLoginController(LoginController loginController) {
		this.loginController = loginController;
	}

}
