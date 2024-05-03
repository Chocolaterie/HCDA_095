package tp.enistore.bo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categories")
public class Category {

	@Id
	public String id;
	
	public String name;
	
	public Category(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
}
