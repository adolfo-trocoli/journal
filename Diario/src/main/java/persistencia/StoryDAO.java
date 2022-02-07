package persistencia;

import javax.persistence.EntityManager;

import model.Story;

public class StoryDAO extends JpaDAO<Story, Long> {
	public StoryDAO(EntityManager em) {
		super(em, Story.class);
	}
}
