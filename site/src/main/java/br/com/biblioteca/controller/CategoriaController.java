package br.com.biblioteca.controller;

import br.com.biblioteca.Categoria;
import br.com.biblioteca.dao.CategoriaDAO;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import java.io.Serializable;
 
@ManagedBean 
@SessionScoped
public class CategoriaController implements Serializable {
 
	private static final long serialVersionUID = -1751857982369494460L;

    private CategoriaDAO categoriaDAO = new CategoriaDAO();
    private Categoria categoriaNova;
    private DataModel<Categoria> listaCategorias;

    public DataModel<Categoria> getListarCategorias() {
    	listaCategorias = new ListDataModel<Categoria>(categoriaDAO.listar());
        return listaCategorias;
    }
    
    public String prepararAdicionarCategoria(ActionEvent actionEvent){
    	setCategoriaNova(new Categoria());
    	return "index";
    }
    
    public String excluirCategoria(ActionEvent actionEvent){
    	Categoria categoria = (listaCategorias.getRowData());
    	categoriaDAO.excluir(categoria.getIdCategoria());
        return "index";
    }
    
    public String adicionarCategoria(ActionEvent actionEvent){
    	categoriaDAO.inserir(categoriaNova);
        return "index";
    }

	public Categoria getCategoriaNova() {
		return categoriaNova;
	}

	public void setCategoriaNova(Categoria categoriaNova) {
		this.categoriaNova = categoriaNova;
	}

}