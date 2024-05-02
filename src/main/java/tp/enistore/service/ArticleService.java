package tp.enistore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tp.enistore.bo.Article;
import tp.enistore.bo.Category;
import tp.enistore.bo.FormRequest;
import tp.enistore.bo.ServiceResponse;
import tp.enistore.dao.ArticleDAO;
import tp.enistore.dao.CategoryDAO;
import tp.enistore.dao.mongo.CategoryMongoRepository;

@Service
public class ArticleService {

	@Autowired
	ArticleDAO articleDAO;
	
	@Autowired
	CategoryDAO categoryDAO;
	
	@Autowired
	CategoryMongoRepository categoryMongoRepository;
	
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
		// Préparer la reponse par défaut
		ServiceResponse<Boolean> response = new ServiceResponse<Boolean>();
		
		// -- récupérer un article avec l'uid 
		Article foundArticle = articleDAO.findByUid(uid);
		
		// 701 : Uid non existant car on ne trouve pas l'article
		if (foundArticle == null) {
			response.code = "701";
			response.message = "Impossible de supprimer un article dont l'UID n'existe pas ";
			response.data = false;
			
			return response;
		}
		
		// 200 : Ok
		// -- supprime l'article
		articleDAO.delete(uid);
		
		// -- response
		response.code = "200";
		response.message = String.format("L'article %s a été supprimé avec succès", uid);
		response.data = true;
		
		return response;
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
	
	public ServiceResponse<Category> saveCategory(Category category) {
		// Préparer la reponse par défaut
		ServiceResponse<Category> response = new ServiceResponse<Category>();
		
		// ==============================================
		// CREATION
		// ==============================================
		if (category.id == null) {
			// Verifier que le titre n'est pas en base
			Category categoryByName = categoryDAO.findByName(category.name);
			
			// 701 - Si le titre existe
			if (categoryByName != null) {
				response.code = "701";
				response.message = "Impossible d'ajouter une catégorie avec un label déjà existant";
				
				return response;
			}
			
			// 200 - Succès
			response.code = "200";
			response.message = "Categorie ajoutée avec succès";
			response.data = categoryDAO.save(category);
			
			// Retourne la réponse de la création
			return response;
		}
		// ==============================================
		// Edition
		// ==============================================
		// Verifier que le titre n'est pas en base
		Category categoryByName = categoryDAO.findByName(category.name);
		
		// 701 - Si le titre existe
		if (categoryByName != null) {
			response.code = "701";
			response.message = "Impossible de modifier une categorie avec un titre déjà existant";
			
			return response;
		}
		
		// 200 - Succès
		response.code = "200";
		response.message = "Categorie modifié avec succès";
		response.data = categoryDAO.save(category);
					
		return response;
	}

	public ServiceResponse<Article> associateCategory(FormRequest<Article, String> requestData) {
		// réponse par defaut
		ServiceResponse<Article> response = new ServiceResponse<Article>();
		
		// récuéperer la category
		Category category = categoryMongoRepository.findById(requestData.associationId).get();
		
		// récupérer l'article
		Article article = articleDAO.findByUid(requestData.data.uid);
		
		// association
		article.category = category;
		
		articleDAO.save(article);
		
		response.code = "200";
		response.data = article;
		
		return response;
	}
}
