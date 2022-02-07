package model;

import javax.persistence.Embeddable;

@Embeddable
public class Paragraph {
	int initialOffset;
	int finalOffset;
}
