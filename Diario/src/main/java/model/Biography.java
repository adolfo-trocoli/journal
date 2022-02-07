package model;

import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Biography {
	@Id
	@GeneratedValue
	private int id;

	private String content;
	
	@ElementCollection
	private Map<Paragraph, Period> periods;

	private void appendContent(String text) {
		this.content += text;
	}
}
