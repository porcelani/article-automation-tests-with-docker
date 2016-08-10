package br.com.biblioteca;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="tb_editora")
public class Editora implements Serializable{

	private static final long serialVersionUID = -7220079513786041403L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idEditora;
	
	private String nome;
	
	
	public Editora() { }
	
	public int getIdEditora() {
		return idEditora;
	}
	
	public void setIdEditora(int idEditora) {
		this.idEditora = idEditora;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
}
