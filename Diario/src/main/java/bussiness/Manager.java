package bussiness;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import model.Category;
import model.Period;
import model.Story;

/**
 * Bussiness class for controlling the EntityManager. It offers convenience methods to manage JPA persistence in the Diary app.
 * 
 * @author adolfo
 * @version 1.0
 */
public class Manager {

	private static EntityManager em;

	/**
	 * Constructor of the class.
	 */
	public Manager() {
		em = Persistence.createEntityManagerFactory("Diario-PU").createEntityManager();
	}
	
	/**
	 * Finds a story in the database given it's id.
	 * @param id Story's id in the database.
	 * @return The story if it exists in the database, null if it doesn't.
	 */
	public Story findStory(int id) {
		return em.find(Story.class, id);
	}

	/**
	 * Creates a story given a description, a category, and initial and final date and time.
	 * @param description Description of the story.
	 * @param categoryName Category name of the story.
	 * @param initialDate Date and time the event began.
	 * @param finalDate Date and time the event finished.
	 * @return The story stored in the database.
	 */
	public Story createStory(String description, String categoryName, LocalDateTime initialDate, LocalDateTime finalDate) {
		Category category = findCreateCategory(categoryName);
		Story story = new Story(description, category, initialDate, finalDate);
		try {
			em.getTransaction().begin();
			em.persist(story);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
		return story;
	}
	
	/**
	 * Finds a category given it's name, creates it if it doesn't exist in the database already.
	 * @param name Name of the category.
	 * @return Category created.
	 */
	public Category findCreateCategory(String name) {
		Category category = em.find(Category.class, name);
		if(category != null) return category;
		category = new Category(name);
		try {
			em.getTransaction().begin();
			em.persist(category);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
		return category;
	}

	/**
	 * Deletes a story from the database if it exists.
	 * @param id Id of the story.
	 */
	public void deleteStory(int id) {
		Story story = findStory(id);
		Category category = findCreateCategory(story.getCategory().getName());
		if (story == null) return;
		try {
			em.getTransaction().begin();
			category.getStories().remove(story);
			em.remove(em.merge(story));
			em.merge(category);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
	}
	
	/**
	 * Updates a story in the database. It doesn't do anything if the story doesn't exist.
	 * @param story
	 */
	public void updateStory(Story story) {
		if(!em.contains(story)) return;
		try {
			em.getTransaction().begin();
			em.merge(story);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
	}
	
	/**
	 * Flushes and clears the EntityManager.
	 */
	public void flushAndClear() {
		em.flush();
		em.clear();
	}

	/**
	 * Closes the EntityManager.
	 */
	public void close() {
		em.close();
	}

}
