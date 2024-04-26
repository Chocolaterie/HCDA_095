package tp.enistore.bo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Document(collection = "personnes")
public class Personne implements UserDetails {

	@Id
	public String id;
	
	/**
	 * Id metier
	 */
	public String uid;
	
	/**
	 * Username (identifiant) de UserDetails (donc de Spring Security)
	 */
	public String email;
	
	public String password;
	
	public String authority;
	
	public Personne() {
		
	}

	public Personne(String id, String email) {
		super();
		this.id = id;
		this.email = email;
	}

	/**
	 * Permet de récupérer les rôles en String dans la bdd
	 * en liste de rôle dans Spring Security
	 * EX: "ROLE_ADMIN,ROLE_USER" => List(ADMIN, USER)
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> grants = new ArrayList<GrantedAuthority>();
		String[] roles = authority.split(",");
		
		for (String role : roles) {
			grants.add(new SimpleGrantedAuthority(role));
		}
		
		return grants;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
