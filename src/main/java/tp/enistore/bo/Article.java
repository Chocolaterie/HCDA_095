package tp.enistore.bo;

import org.springframework.data.annotation.Id;

public class Article {

	@Id
	public Long id;
	
	public String uid;
	
	public String title;
}
