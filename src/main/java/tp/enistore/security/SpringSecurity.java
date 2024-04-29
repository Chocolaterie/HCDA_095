package tp.enistore.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

	@Autowired
	JwtFilter jwtFilter;

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		// Rédéfinir les habilitations des urls/routes
		http.authorizeHttpRequests(
				(authorize) -> authorize
				.requestMatchers(HttpMethod.DELETE, "/api/v1/delete-article/**").hasRole("ADMIN")
				.requestMatchers("/api/v1/auth/login").permitAll()
				.anyRequest().authenticated()
		);
				
		// Configurer l'authentification de Spring Security
		http.httpBasic(Customizer.withDefaults());
		
		http.csrf(csrf -> {
			csrf.disable();
		});
		
		// Activer notre middleware 
		// Dans l'execution -> remplace le basic auth
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
				
		
		return http.build();
	}
}
