package br.com.simpleblog.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.NoResultException;

import br.com.simpleblog.dao.CategoriaDao;
import br.com.simpleblog.model.Categoria;

@FacesConverter("categoriaConverter")
public class CategoriaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		 if(value.equals(null) || value.equals("null") || value.equals(""))
	            return null;
	        try{
	         CategoriaDao categoriaDao = new CategoriaDao();
	         Categoria resultado = categoriaDao.buscarCategoriaById(Integer.valueOf(value));
	         return resultado;
	        }catch (NoResultException e) {
	            e.printStackTrace();
	        }
	        return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value==null || value.equals(""))
            return null;
             return String.valueOf(((Categoria)value).getId());		
	}

}
