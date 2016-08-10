package br.com.biblioteca.controller;

import br.com.biblioteca.Emprestimo;
import br.com.biblioteca.Livro;
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
public class EmprestimoController implements Serializable {
 
	private static final long serialVersionUID = -1751857982369494460L;

	private BibliotecaNegocio bibliotecaNegocio = new BibliotecaNegocio();
    private Emprestimo novoEmprestimo;
    private String isbnLivro;
    private Integer usuarioId;
    private DataModel<Emprestimo> listaEmprestimos;
	private DataModel<Usuario> listaUsuarios;
	private DataModel<Livro> listaLivros;

    public DataModel<Emprestimo> getListarEmprestimos() {
    	listaEmprestimos = new ListDataModel<Emprestimo>(bibliotecaNegocio.listarEmprestimos());
        return listaEmprestimos;
    }
    
    public DataModel<Usuario> getListarUsuarios() {
    	listaUsuarios = new ListDataModel<Usuario>(bibliotecaNegocio.listarUsuarios());
        return listaUsuarios;
    }
    
    public DataModel<Livro> getListarLivros() {
        listaLivros = new ListDataModel<Livro>(bibliotecaNegocio.selecionarLivrosDisponiveis());
        return listaLivros;
    }
    
    public String prepararNovoEmprestimo(ActionEvent actionEvent){
    	setNovoEmprestimo(new Emprestimo());
    	return "index";
    }
    
    public String devolverLivro(ActionEvent actionEvent){
    	Emprestimo emprestimo = (listaEmprestimos.getRowData());
    	String isbnLivro = emprestimo.getEmprestimoID().getLivro().getIsbn();
		Integer usuarioId = emprestimo.getEmprestimoID().getUsuario().getIdUsuario();
		bibliotecaNegocio.devolverLivro(isbnLivro, usuarioId);
        return "index";
    }
    
    public String cadastrarEmprestimo(ActionEvent actionEvent){
    	bibliotecaNegocio.retirarLivro(isbnLivro, usuarioId);
        return "index";
    }

	public Emprestimo getNovoEmprestimo() {
		return novoEmprestimo;
	}

	public void setNovoEmprestimo(Emprestimo novoEmprestimo) {
		this.novoEmprestimo = novoEmprestimo;
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

}