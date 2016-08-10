package br.com.biblioteca;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="tb_estoque")
public class Estoque implements Serializable {

	private static final long serialVersionUID = -6542315164414798597L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idEstoque;
	
	@ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.EAGER)
	@JoinColumn (name="isbnLivro")
	private Livro livro;
	
	public Estoque() { }

	public int getIdEstoque() {
		return idEstoque;
	}

	public void setIdEstoque(int idEstoque) {
		this.idEstoque = idEstoque;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
}
