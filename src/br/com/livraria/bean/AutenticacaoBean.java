package br.com.livraria.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.codec.digest.DigestUtils;

import br.com.livraria.dao.FuncionarioDAO;
import br.com.livraria.domain.Funcionario;
import br.com.livraria.util.FacesUtil;

@ManagedBean
@SessionScoped
public class AutenticacaoBean {
	private Funcionario funcionarioLogado;

	public Funcionario getFuncionarioLogado() {
		if (funcionarioLogado == null) {
			funcionarioLogado = new Funcionario();
		}
		return funcionarioLogado;
	}

	public void setFuncionarioLogado(Funcionario funcionarioLogado) {
		this.funcionarioLogado = funcionarioLogado;
	}

	public String entrar() {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioLogado = funcionarioDAO.autenticar(funcionarioLogado.getNome(),
					DigestUtils.md5Hex(funcionarioLogado.getSenha()));
			if (funcionarioLogado == null) {
				FacesUtil.addMsgErro("Dados incorretos! ");
				return null;
			} else {
				FacesUtil.addMsgInfo("Login efetuado!!");
				return "/pages/principal.xhtml?faces-redirect=true";
			}
		} catch (Exception e) {
			FacesUtil.addMsgErro("Erro de autenticação! " + e.getMessage());
			return null;
		}
	}

	public String sair() {
		funcionarioLogado = null;
		FacesUtil.addMsgInfo("Login encerrado!!");
		return "/pages/index.xhtml?faces-redirect=true";
	}

	public String verificar() {
		if (funcionarioLogado == null) {
			FacesUtil.addMsgErro("Login encerrado! ");
			return "/pages/index.xhtml?faces-redirect=true";
		}
		return null;
	}
}
