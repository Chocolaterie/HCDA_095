package tp.enistore.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

	@Autowired
	MongoUserDetailsService mongoUserDetailsService;
	
	/**
	 * Ajouter notre manière de connecter un user Spring Security
	 * Donc comment se connecter via ma BDD mongo dans notre cas
	 * @param builder
	 * @throws Exception
	 */
	@Autowired
	public void configAuthBuilder(AuthenticationManagerBuilder builder) throws Exception {
		builder.userDetailsService(mongoUserDetailsService);
	}
	
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		System.out.println(passwordEncoder().encode("password"));
		
		// Rédéfinir les habilitations des urls/routes
		http.authorizeHttpRequests(
				(authorize) -> authorize
				.requestMatchers("/api/v1/delete-article/**").hasRole("ADMIN")
				.anyRequest().authenticated()
		);
		
		// Configurer l'authentification de Spring Security
		http.httpBasic(Customizer.withDefaults());
		
		http.csrf(csrf -> {
			csrf.disable();
		});
		
		return http.build();
	}
}
