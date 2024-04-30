package tp.enistore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tp.enistore.bo.Article;
import tp.enistore.bo.ServiceResponse;
import tp.enistore.dao.ArticleDAO;

@Service
public class ArticleService {

	@Autowired
	ArticleDAO articleDAO;
	
	/**
	 * Version dépréciée
	 * @return
	 */
	public List<Article> getArticles_Deprecated(){
		// appel la nouvelle version
		ServiceResponse<List<Article>> response = getArticles();
		
		return response.data;
	}
	
	public ServiceResponse<List<Article>> getArticles(){
		// Préparer la reponse par défaut
		ServiceResponse<List<Article>> response = new ServiceResponse<List<Article>>();
				
		// Cas 1 : 200
		response.code = "200";
		response.message = "Articles récupérés avec succès";
		response.data = articleDAO.findAll();
		
		return response;
	}
	
	/**
	 * Version dépréciée
	 * @return
	 */
	public Article getArticleById_Deprecated(String uid) {
		// appel la nouvelle version
		ServiceResponse<Article> response = getArticleById(uid);
		
		return response.data;
	}
	
	public ServiceResponse<Article> getArticleById(String uid) {
		// return articleDAO.findByUid(uid);
		
		return new ServiceResponse<Article>();
	}
	
	/**
	 * Version dépréciée
	 * @return
	 */
	public void deleteArticle_Deprecated(String uid) {
		// appel la nouvelle version
		ServiceResponse<Boolean> response = deleteArticle(uid);
	}
	
	public ServiceResponse<Boolean> deleteArticle(String uid) {
		// articleDAO.delete(uid);
		
		return new ServiceResponse<Boolean>();
	}
	
	/**
	 * Version dépréciée
	 * @return
	 */
	public void saveArticle_Deprecated(Article article) {
		// appel la nouvelle version
		ServiceResponse<Article> response = saveArticle(article);
	}
	
	public ServiceResponse<Article> saveArticle(Article article) {
		articleDAO.save(article);
		
		return new ServiceResponse<Article>();
	}
}
