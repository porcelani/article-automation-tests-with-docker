package br.com.biblioteca;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="tb_emprestimo")
public class Emprestimo implements Serializable {

	private static final long serialVersionUID = -6181277912567504376L;

	@EmbeddedId
	private EmprestimoID emprestimoID;
	
	@Temporal(value=TemporalType.DATE)
	private Date dtEmprestimo;

	@Temporal(value=TemporalType.DATE)
	private Date dtDevolucao;
	
	public Emprestimo() { }
	
	public EmprestimoID getEmprestimoID() {
		return emprestimoID;
	}
	
	public void setEmprestimoID(EmprestimoID emprestimoID) {
		this.emprestimoID = emprestimoID;
	}
	
	public Date getDtEmprestimo() {
		return dtEmprestimo;
	}
	
	public void setDtEmprestimo(Date dtEmprestimo) {
		this.dtEmprestimo = dtEmprestimo;
	}

	public Date getDtDevolucao() {
		return dtDevolucao;
	}
	
	public void setDtDevolucao(Date dtDevolucao) {
		this.dtDevolucao = dtDevolucao;
	}
	
}
