package br.com.biblioteca.controller;

import br.com.biblioteca.Categoria;
import br.com.biblioteca.Editora;
import br.com.biblioteca.Livro;
import br.com.biblioteca.dao.CategoriaDAO;
import br.com.biblioteca.dao.EditoraDAO;
import br.com.biblioteca.negocio.BibliotecaNegocio;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import java.io.Serializable;
 
@ManagedBean 
@SessionScoped
public class LivroController implements Serializable {
 
	private static final long serialVersionUID = -1751857982369494460L;

	private BibliotecaNegocio bibliotecaNegocio = new BibliotecaNegocio();
	private CategoriaDAO categoriaDAO = new CategoriaDAO();
	private EditoraDAO editoraDAO = new EditoraDAO();
    private Livro livroNovo;
    private Livro livroSelecionado;
    private int categoriaId;
    private int editoraId;
    private DataModel<Livro> listaLivros;
    private DataModel<Categoria> listaCategorias;
    private DataModel<Editora> listaEditoras;
    
    public DataModel<Livro> getListarLivros() {
        listaLivros = new ListDataModel<Livro>(bibliotecaNegocio.selecionarTodosLivros());
        return listaLivros;
    }
    
    public DataModel<Categoria> getListaCategorias() {
    	listaCategorias = new ListDataModel<Categoria>(categoriaDAO.listar());
        return listaCategorias;
    }
    
    public DataModel<Editora> getListaEditoras() {
    	listaEditoras = new ListDataModel<Editora>(editoraDAO.listar());
        return listaEditoras;
    }
 
    public Livro getLivroNovo() {
        return livroNovo;
    }
 
    public void setLivroNovo(Livro livro) {
        this.livroNovo = livro;
    }
 
    public void prepararAlterarLivro(){
        livroNovo = (listaLivros.getRowData());
    }
    
    public String excluirLivro(ActionEvent actionEvent){
        Livro livro = (listaLivros.getRowData());
        bibliotecaNegocio.excluirLivro(livro.getIsbn());
        return "index";
    }
 
    public String prepararAdicionarLivro(ActionEvent actionEvent){
    	livroNovo = new Livro();
    	return "index";
    }
    
    public String adicionarLivro(ActionEvent actionEvent){
    	Categoria categoria = categoriaDAO.localizar(categoriaId);
    	Editora editora = editoraDAO.localizar(editoraId);
		livroNovo.setCategoria(categoria);
		livroNovo.setEditora(editora);
    	bibliotecaNegocio.inserirLivro(livroNovo, editora);
    	return "index";
    }
 
    public void alterarLivro(){
    }

	public Livro getLivroSelecionado() {
		return livroSelecionado;
	}
	
	public Livro setLivroSelecionado() {
		return livroSelecionado;
	}

	public int getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(int categoriaId) {
		this.categoriaId = categoriaId;
	}

	public int getEditoraId() {
		return editoraId;
	}

	public void setEditoraId(int editoraId) {
		this.editoraId = editoraId;
	}

}