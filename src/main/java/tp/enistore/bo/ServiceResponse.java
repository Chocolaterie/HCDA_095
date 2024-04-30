package tp.enistore.bo;

public class ServiceResponse <T> {

	/**
	 * Lié à la règle de gestion
	 * Success : 200
	 * Erreur technique = 701
	 */
	public String code;
	
	/**
	 * Lié à la règle de gestion
	 * Exemple :Quand tu fais un virement de 5000€ sur un compte jeune :
	 * Impossible la plafond à 1500 euros
	 */
	public String message;
	
	public T data;
}
