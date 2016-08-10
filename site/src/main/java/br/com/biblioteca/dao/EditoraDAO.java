package br.com.biblioteca.dao;

import br.com.biblioteca.Editora;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public class EditoraDAO extends GenericDAO<Editora> {

	private static final long serialVersionUID = -715307108673173397L;

	/**
	 * Localiza uma Editora pelo nome
	 * @param nome Nome da Editora
	 * @return Objeto da classe Editora
	 */
	public Editora localizar(String nome) {

		EntityManager em = factory.createEntityManager();
		EntityTransaction t = em.getTransaction();
		Editora editora = null;
		
		try {

			t.begin();
			Query q = em.createQuery("from Editora where nome like :nome");
			q.setParameter("nome", nome);
			editora = (Editora) q.getSingleResult();
			t.commit();

		} catch (Exception e) {

			if (debugInfo) {
				e.printStackTrace();
			}
			if (t.isActive()) t.rollback();

		} finally {

			em.close();
		}

		return editora;
	}
}
