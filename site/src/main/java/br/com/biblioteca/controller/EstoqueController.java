package br.com.biblioteca.controller;

import br.com.biblioteca.Livro;
import br.com.biblioteca.negocio.BibliotecaNegocio;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
 
@ManagedBean 
@SessionScoped
public class EstoqueController implements Serializable {
 
	private static final long serialVersionUID = -9004335677068435669L;
	
	private BibliotecaNegocio bibliotecaNegocio = new BibliotecaNegocio();
    private DataModel<RelacaoLivroEstoque> livroEstoque;
    
    public class RelacaoLivroEstoque {
    	private Livro livro;
    	private Integer quantidade;
    	
		public Livro getLivro() {
			return livro;
		}
		public void setLivro(Livro livro) {
			this.livro = livro;
		}
		public Integer getQuantidade() {
			return quantidade;
		}
		public void setQuantidade(Integer quantidade) {
			this.quantidade = quantidade;
		}
    }
    
    public DataModel<RelacaoLivroEstoque> getLivroEstoque(){
    	List<Livro> livros = bibliotecaNegocio.selecionarTodosLivros();
    	List<RelacaoLivroEstoque> relacoesLivroEstoque = new ArrayList<RelacaoLivroEstoque>();
    	for (Livro livro : livros) {
    		RelacaoLivroEstoque relacaoLivroEstoque = new RelacaoLivroEstoque();
    		relacaoLivroEstoque.setLivro(livro);
    		relacaoLivroEstoque.setQuantidade(bibliotecaNegocio.buscarQuantidade(livro.getIsbn()));
    		relacoesLivroEstoque.add(relacaoLivroEstoque);
		}
    	livroEstoque = new ListDataModel<RelacaoLivroEstoque>(relacoesLivroEstoque);
    	return livroEstoque;
    }
    
    public String excluirEstoque(ActionEvent actionEvent){
    	RelacaoLivroEstoque relLivroEst = (livroEstoque.getRowData());
    	bibliotecaNegocio.removerLivroEstoque(relLivroEst.getLivro().getIsbn(), 1);
        return "index";
    }
    
    public String adicionarEstoque(ActionEvent actionEvent){
    	RelacaoLivroEstoque relLivroEst = (livroEstoque.getRowData());
    	bibliotecaNegocio.adicionarLivroEstoque(relLivroEst.getLivro().getIsbn(), 1);
    	return "index";
    }

}