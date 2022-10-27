package lv.vktranzits.demo.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

import lv.vktranzits.demo.services.impl.EmployeeDetailsService;

@Configurable
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
	}

	@Bean
	protected UserDetailsService userDetailsService() {
		
		return new EmployeeDetailsService();
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

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/course/**").hasAuthority("ROLE_ADMIN")
		.antMatchers("/department/**").hasAuthority("ROLE_ADMIN")
		.antMatchers("/employee/**").hasAuthority("ROLE_ADMIN")
		.and()
		.formLogin().permitAll().and().sessionManagement()
        .maximumSessions(1)
        .expiredUrl("/sessionExpired.html").and()
		.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
		.invalidSessionUrl("/login")
		.and()
		.logout().permitAll()
		.invalidateHttpSession(true);
		
		http.csrf().disable();
		http.headers().frameOptions().disable();
		
		// http.sessionManagement()
        // .maximumSessions(1)
        // .expiredUrl("/sessionExpired.html").and()
		// .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
		// .invalidSessionUrl("/login");
	}

	
}
