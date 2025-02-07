package org.reist.msvc.user.setup;

import lombok.RequiredArgsConstructor;
import org.reist.msvc.user.repositories.IUsernameRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class MvcConfig implements WebMvcConfigurer {
	/*
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		WebMvcConfigurer.super.addResourceHandlers(registry);
		String resourcePath = Paths.get("uploads").toAbsolutePath().toUri().toString();
		log.info(resourcePath);
		registry.addResourceHandler("/uploads/**").addResourceLocations(resourcePath);
	}
	*/
	private final IUsernameRepository dao;
	//public void addViewControllers(ViewControllerRegistry registry) {
		//registry.addViewController("/error-403").setViewName("error-403");
	//}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authenticationProvider(){
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

	@Bean
	public UserDetailsService userDetailsService(){
		return username -> dao.findByNickname(username)
				.orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s does not exist", username)));

	}
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**") // Permitir todas las rutas
				.allowedOrigins("http://localhost:5173") // Permitir solo este origen
				.allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos HTTP permitidos
				.allowedHeaders("*") // Permitir todos los encabezados
				.allowCredentials(true); // Permitir el uso de credenciales (cookies)

	}
}
