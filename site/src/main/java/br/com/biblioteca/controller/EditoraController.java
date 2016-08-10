package br.com.biblioteca.controller;

import br.com.biblioteca.Editora;
import br.com.biblioteca.dao.EditoraDAO;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import java.io.Serializable;
 
@ManagedBean 
@SessionScoped
public class EditoraController implements Serializable {
 
	private static final long serialVersionUID = -1751857982369494460L;

    private EditoraDAO editoraDAO = new EditoraDAO();
    private Editora novaEditora;
    private DataModel<Editora> listaEditoras;

    public DataModel<Editora> getListarEditoras() {
    	listaEditoras = new ListDataModel<Editora>(editoraDAO.listar());
        return listaEditoras;
    }
    
    public String prepararAdicionarEditora(ActionEvent actionEvent){
    	setNovaEditora(new Editora());
    	return "index";
    }
    
    public String excluirEditora(ActionEvent actionEvent){
    	Editora editora = (listaEditoras.getRowData());
    	editoraDAO.excluir(editora.getIdEditora());
        return "index";
    }
    
    public String adicionarEditora(ActionEvent actionEvent){
    	editoraDAO.inserir(novaEditora);
        return "index";
    }

	public Editora getNovaEditora() {
		return novaEditora;
	}

	public void setNovaEditora(Editora novaEditora) {
		this.novaEditora = novaEditora;
	}

}