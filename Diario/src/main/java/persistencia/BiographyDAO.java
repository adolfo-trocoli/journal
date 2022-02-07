package persistencia;

import javax.persistence.EntityManager;

import model.Biography;

public class BiographyDAO extends JpaDAO<Biography, Long>{
	public BiographyDAO(EntityManager em) {
		super(em, Biography.class);
	}
}
