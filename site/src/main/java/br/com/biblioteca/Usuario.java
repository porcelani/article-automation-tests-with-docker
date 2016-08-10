package br.com.biblioteca;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tb_usuario")
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = -5159952304514750654L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idUsuario;
	
	private String nome;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY,
			   mappedBy="emprestimoID.usuario")
	private List<Emprestimo> emprestimoItems = new ArrayList<Emprestimo>();

	
	public Usuario() { }
	
	public int getIdUsuario() {
		return idUsuario;
	}
	
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Emprestimo> getEmprestimoItems() {
		return emprestimoItems;
	}

	public void setEmprestimoItems(List<Emprestimo> emprestimoItems) {
		this.emprestimoItems = emprestimoItems;
	}
}
