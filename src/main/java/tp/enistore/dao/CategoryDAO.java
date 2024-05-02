package tp.enistore.dao;

import java.util.List;

import tp.enistore.bo.Category;

public interface CategoryDAO {

	public List<Category> findAll();
	
	public Category findByName(String name);

	public Category save(Category category);
}
