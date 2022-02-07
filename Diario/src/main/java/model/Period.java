package model;

import java.time.LocalDateTime;

import javax.persistence.Embeddable;

import lombok.Data;


@Embeddable
@Data
public class Period {
	LocalDateTime initialDate;
	LocalDateTime finalDate;
	
	public Period(LocalDateTime initialDate, LocalDateTime finalDate) {
		this.initialDate = initialDate;
		this.finalDate = finalDate;
	}
	
	
}
