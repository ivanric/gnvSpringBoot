package app.Seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.savedrequest.NullRequestCache;

@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
				.withUser("user").password("password").roles("USER");
	}

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		/*
		.authorizeRequests()
			.anyRequest().authenticated()
			.and()
		.requestCache()
			.requestCache(new NullRequestCache())
			.and()
		.httpBasic();
		*/
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/,/index").permitAll();
//		.antMatchers("/**").hasRole("USER")
//		.and()
//		.httpBasic()
//		.and().formLogin().loginPage("/principal/index");
		http.headers().frameOptions().sameOrigin();//DESACTIVA EL ENCABEZADO  sameOrigin y  permitir X-Frame-Options
	}
	

}