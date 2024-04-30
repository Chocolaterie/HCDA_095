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
		// Préparer la reponse par défaut
		ServiceResponse<Article> response = new ServiceResponse<Article>();
				
		// MongoRepo retourne null si n'existe pas
		Article foundArticle = articleDAO.findByUid(uid);
		
		// 702 - Si l'uid n'existe pas en base
		if (foundArticle == null) {
			response.code = "702";
			response.message = String.format("Impossible de récupérer un article avec l'UID %s", uid);
			
			return response;
		}
		
		// 200 - Succès
		response.code = "200";
		response.message = "Article récupéré avec succès";
		response.data = foundArticle;
		
		return response;
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
		// Préparer la reponse par défaut
		ServiceResponse<Article> response = new ServiceResponse<Article>();
		
		// ==============================================
		// CREATION
		// ==============================================
		if (article.uid == null || article.uid.isEmpty()) {
			// Verifier que le titre n'est pas en base
			Article articleByTitle = articleDAO.findByTitle(article.title);
			
			// 701 - Si le titre existe
			if (articleByTitle != null) {
				response.code = "701";
				response.message = "Impossible d'ajouter un article avec un titre déjà existant";
				
				return response;
			}
			
			// 200 - Succès
			response.code = "200";
			response.message = "Article ajouté avec succès";
			response.data = articleDAO.save(article);
			
			// Retourne la réponse de la création
			return response;
		}
		// ==============================================
		// Edition
		// ==============================================
		// Verifier que le titre n'est pas en base
		Article articleByTitle = articleDAO.findByTitle(article.title);
		
		// 701 - Si le titre existe
		if (articleByTitle != null) {
			response.code = "701";
			response.message = "Impossible de modifier un article avec un titre déjà existant";
			
			return response;
		}
		
		// 200 - Succès
		response.code = "200";
		response.message = "Article modifié avec succès";
		response.data = articleDAO.save(article);
					
		return response;
	}
}
