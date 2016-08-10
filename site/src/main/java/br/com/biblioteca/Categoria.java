package br.com.biblioteca;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="tb_categoria")
public class Categoria implements Serializable {
	
	private static final long serialVersionUID = -438649389306009952L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idCategoria;
	
	private String descricao;
	
	
	public Categoria() { }

	public int getIdCategoria() {
		return idCategoria;
	}
	
	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
