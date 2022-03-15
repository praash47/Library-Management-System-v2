package transactions;

import java.io.Serializable;

public class Author implements Serializable {
	public String name;
	public long birthYear;
	public long deathYear;
	
	public Author() {}
	
	public void setAttribute(String attributeName, Object attributeValue) {
		switch(attributeName) {
		case "name":
			this.name = (String) attributeValue;
			break;
		case "birth_year":
			if (attributeValue != null) {
				this.birthYear = (Long) attributeValue;
			}
			break;
		case "death_year":
			if (attributeValue != null) {
				this.deathYear = (Long) attributeValue;
			}
			break;
		}
	}
}