package br.com.simpleblog.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.simpleblog.dao.PostDao;
import br.com.simpleblog.model.Post;

@ManagedBean
@SessionScoped
public class ImagemController {
	
	PostDao postDao = new PostDao();
	
	public StreamedContent getImagem() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        }
        else {
            String idPost = context.getExternalContext().getRequestParameterMap().get("idPost");
            Post post = postDao.obterPorId(Integer.valueOf(idPost));
            return new DefaultStreamedContent(new ByteArrayInputStream(post.getImagem()));
        }
    }

}
