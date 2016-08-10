package br.com.biblioteca.controller;

import br.com.biblioteca.Usuario;
import br.com.biblioteca.dao.UsuarioDAO;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import java.io.Serializable;
 
@ManagedBean 
@SessionScoped
public class UsuarioController implements Serializable {
 
	private static final long serialVersionUID = -1751857982369494460L;

    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private Usuario usuarioNovo;
    private DataModel<Usuario> listaUsuarios;

    public DataModel<Usuario> getListarUsuarios() {
    	listaUsuarios = new ListDataModel<Usuario>(usuarioDAO.listar());
        return listaUsuarios;
    }
    
    public String prepararAdicionarUsuario(ActionEvent actionEvent){
    	setUsuarioNovo(new Usuario());
    	return "index";
    }
    
    public String excluirUsuario(ActionEvent actionEvent){
    	Usuario usuario = (listaUsuarios.getRowData());
    	usuarioDAO.excluir(usuario.getIdUsuario());
        return "index";
    }
    
    public String adicionarUsuario(ActionEvent actionEvent){
    	usuarioDAO.inserir(usuarioNovo);
        return "index";
    }

	public Usuario getUsuarioNovo() {
		return usuarioNovo;
	}

	public void setUsuarioNovo(Usuario usuarioNovo) {
		this.usuarioNovo = usuarioNovo;
	}

}