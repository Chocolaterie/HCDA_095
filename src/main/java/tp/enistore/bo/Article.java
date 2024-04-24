package tp.enistore.bo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "articles")
public class Article {

	@Id
	public String id;
	
	public String uid;
	
	public String title;
}
