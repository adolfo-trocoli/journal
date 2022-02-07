package model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Category {

	@Id
	private String name;

	@OneToMany(mappedBy = "category")
	private List<Story> stories;

	public Category() {
	}

	public Category(String name) {
		this.name = name;
	}

	public void addStory(Story story) {
		stories.add(story);
	}

	public void removeStory(Story story) {
		stories.remove(story);
	}

	public List<Story> getStories() {
		return stories;
	}
}
