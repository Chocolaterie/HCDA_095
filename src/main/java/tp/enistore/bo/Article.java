package tp.enistore.bo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "articles")
public class Article {

	@Id
	public String id;
	
	public String uid;
	
	public String title;
	
	@DBRef
	public Category category;
	
	public Article(String uid, String title, Category category) {
		this.uid = uid;
		this.title = title;
		this.category = category;
	}
	
}
