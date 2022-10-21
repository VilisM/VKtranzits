package lv.vktranzits.demo.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

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

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/home").permitAll()
		.antMatchers("/send").hasAuthority("ROLE_ADMIN")
		.antMatchers("/object").hasAuthority("ROLE_ADMIN")
		.antMatchers("/allProducts").permitAll()
		.antMatchers("/allProductsFilter").hasAnyAuthority("ROLE_EMPLOYEE", "ROLE_USER", "ROLE_ADMIN")
		.antMatchers("/allProducts/**").hasAnyAuthority("ROLE_EMPLOYEE", "ROLE_USER", "ROLE_ADMIN")
		.antMatchers("/addProduct").hasAnyAuthority("ROLE_EMPLOYEE", "ROLE_ADMIN")
		.antMatchers("/updateProduct/**").hasAnyAuthority("ROLE_EMPLOYEE", "ROLE_USER", "ROLE_ADMIN")
		.antMatchers("/error").permitAll()
		.antMatchers("/deleteProduct/**").hasAuthority("ROLE_ADMIN")
		.antMatchers("/filter").permitAll()
		.and()
		.formLogin().permitAll()
		.and()
		.logout().permitAll();
		
		http.csrf().disable();
		http.headers().frameOptions().disable();
		
	}

	
}
