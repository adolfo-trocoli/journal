package persistencia;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public class JpaDAO<T, K> implements DAO<T, K> {

	protected EntityManager em;
	protected Class<T> clazz;

	public JpaDAO(EntityManager em, Class<T> entityClass) {
		if (em == null || entityClass == null) throw new IllegalArgumentException();
		this.clazz = entityClass;
		this.em = em;

	}

	public Optional<T> get(K id) {
		return Optional.ofNullable(em.find(clazz, id));
	}

	
	public void save(T t) {
		em.persist(t);
	}

	public void update(T t) {
		em.merge(t);
	}

	public void delete(T t) {
		t = em.merge(t);
		em.remove(t);
	}

	public List<T> findAll() {
		return em.createQuery("SELECT t FROM " + clazz.getName() + " t", clazz).getResultList();
	}
}
