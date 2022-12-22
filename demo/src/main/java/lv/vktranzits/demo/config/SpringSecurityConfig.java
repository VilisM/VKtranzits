package lv.vktranzits.demo.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

import lv.vktranzits.demo.services.impl.EmployeeDetailsService;

@Configurable
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SpringSecurityConfig{

	
	@Bean
	protected UserDetailsService userDetailsService() {
		
		return new EmployeeDetailsService();
	}
	

	@Bean
	public AuthenticationManager authenticationManager(
			AuthenticationConfiguration authConfig) throws Exception {
    	return authConfig.getAuthenticationManager();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
	
		return authProvider;
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public CookieSerializer cookieSerializer() {
		DefaultCookieSerializer serializer = new DefaultCookieSerializer();
		serializer.setCookieName("VKTsession"); 
		serializer.setCookiePath("/"); 
		serializer.setDomainNamePattern("^.+?\\.(\\w+\\.[a-z]+)$"); 
		return serializer;
	}

	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
		return new HttpSessionEventPublisher();
	}

	// @Bean
	// public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	// 	http
	// 		.sessionManagement(session -> session
	// 			.maximumSessions(1)
	// 			.maxSessionsPreventsLogin(true)
	// 			.expiredUrl("/sessionExpired.html").and()
	// 			.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
	// 			.invalidSessionUrl("/login")
	// 		);
	// 	return http.build();
	// }

	@Bean
	protected SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
		
		http.authenticationProvider(authenticationProvider());
		http.authorizeRequests()
		.antMatchers("/course/**").hasAuthority("ROLE_ADMIN")
		.antMatchers("/department/**").hasAuthority("ROLE_ADMIN")
		.antMatchers("/employee/**").hasAuthority("ROLE_ADMIN")
		.antMatchers("/results/**").hasAuthority("ROLE_ADMIN")
		.antMatchers("/calendar/**").hasAuthority("ROLE_ADMIN")
		.antMatchers("/api/**").hasAuthority("ROLE_ADMIN")
		.and()
		.formLogin().permitAll()
		.and()
		.logout().permitAll()
		.invalidateHttpSession(true)
		.and()
		.sessionManagement(session -> session
			.maximumSessions(1)
			.maxSessionsPreventsLogin(true)
			.expiredUrl("/sessionExpired.html").and()
			.sessionCreationPolicy(SessionCreationPolicy.ALWAYS));
			// .invalidSessionUrl("/login")); <-- makes page go to homepage
		
		
		
		http.csrf().disable();
		http.headers().frameOptions().disable();
		
		

		return http.build();
	}

	
}
