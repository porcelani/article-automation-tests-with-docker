package br.com.biblioteca.controller;

import br.com.biblioteca.Emprestimo;
import br.com.biblioteca.Usuario;
import br.com.biblioteca.negocio.BibliotecaNegocio;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import java.io.Serializable;
 
@ManagedBean 
@SessionScoped
public class ConsultaEmprestimoController implements Serializable {
 
	private static final long serialVersionUID = -1751857982369494460L;

	private BibliotecaNegocio bibliotecaNegocio = new BibliotecaNegocio();
    private String isbnLivro;
    private Integer usuarioId = -1;
    private String nomeUsuario;
    private DataModel<Emprestimo> listaEmprestimos;
	private DataModel<Usuario> listaUsuarios;


    public DataModel<Usuario> getListarUsuarios() {
    	listaUsuarios = new ListDataModel<Usuario>(bibliotecaNegocio.listarUsuarios());
        return listaUsuarios;
    }
    
    public DataModel<Emprestimo> getListarEmprestimosUsuario() {
    	listaEmprestimos = new ListDataModel<Emprestimo>(bibliotecaNegocio.listarEmprestimosUsuario(usuarioId));
        return listaEmprestimos;
    }
       
    public String prepararEmprestimos(ActionEvent actionEvent){
    	Usuario usuario = (listaUsuarios.getRowData());
    	usuarioId = usuario.getIdUsuario();
    	setNomeUsuario(usuario.getNome());
    	return "index";
    }
    
    public String devolverLivro(ActionEvent actionEvent){
    	Emprestimo emprestimo = (listaEmprestimos.getRowData());
    	String isbnLivro = emprestimo.getEmprestimoID().getLivro().getIsbn();
		Integer usuarioId = emprestimo.getEmprestimoID().getUsuario().getIdUsuario();
		bibliotecaNegocio.devolverLivro(isbnLivro, usuarioId);
        return "index";
    }
    
    public Integer getUsuarioId() {
		return usuarioId;
    }
    
	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getIsbnLivro() {
		return isbnLivro;
	}

	public void setIsbnLivro(String isbnLivro) {
		this.isbnLivro = isbnLivro;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

}