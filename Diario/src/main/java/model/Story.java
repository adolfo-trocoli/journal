package model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class Story {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Embedded
	private Period period;
	
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	public Story(String description, Category category, LocalDateTime initialDate, LocalDateTime finalDate) {
		this.period = new Period(initialDate, finalDate);
		this.description = description;
		this.category = category;
	}
}
