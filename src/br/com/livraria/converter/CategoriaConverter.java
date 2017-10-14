package br.com.livraria.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.livraria.dao.CategoriaDAO;
import br.com.livraria.domain.Categoria;

@FacesConverter("categoriaConverter")
public class CategoriaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String valor) {
		try {
			Long codigo = Long.parseLong(valor);
			CategoriaDAO categoriaDAO = new CategoriaDAO();
			Categoria categoria = categoriaDAO.buscarCodigo(codigo);
			return categoria;
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object objeto) {
		try {
			Categoria categoria = (Categoria) objeto;
			Long codigo = categoria.getIdCategoria();
			return codigo.toString();
		} catch (Exception e) {
			return null;
		}
	}

}
