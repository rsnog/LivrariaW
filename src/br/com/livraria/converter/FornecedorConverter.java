package br.com.livraria.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.livraria.dao.FornecedorDAO;
import br.com.livraria.domain.Fornecedor;

@FacesConverter("fornecedorConverter")
public class FornecedorConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String valor) {
		try {
			Long codigo = Long.parseLong(valor);
			FornecedorDAO fornecedorDAO = new FornecedorDAO();
			Fornecedor fornecedor = fornecedorDAO.buscarCodigo(codigo);
			return fornecedor;
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object objeto) {
		try {
			Fornecedor fornecedor = (Fornecedor) objeto;
			Long codigo = fornecedor.getIdFornecedor();
			return codigo.toString();
		} catch (Exception e) {
			return null;
		}
	}

}
