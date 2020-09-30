package com.gameTodoeyBackendClient.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// add a reference to our security data source
	@Autowired
	private DataSource securityDataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		
		auth.jdbcAuthentication().dataSource(securityDataSource);

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
			.antMatchers("/user/showForm*").hasAnyRole("USER_ADMIN", "SUPER_ADMIN")
			.antMatchers("/user/save*").hasAnyRole("USER_ADMIN", "SUPER_ADMIN")
			.antMatchers("/user/delete").hasRole("SUPER_ADMIN")
			.antMatchers("/user/**").hasRole("NORMAL_ADMIN")
			.antMatchers("/game/showForm*").hasAnyRole("USER_ADMIN", "SUPER_ADMIN")
			.antMatchers("/game/save*").hasAnyRole("USER_ADMIN", "SUPER_ADMIN")
			.antMatchers("/game/delete").hasRole("SUPER_ADMIN")
			.antMatchers("/game/**").hasRole("NORMAL_ADMIN")
			.antMatchers("/resources/**").permitAll()
			.and()
			.formLogin()
				.loginPage("/showMyLoginPage")
				.loginProcessingUrl("/authenticateTheUser")
				.permitAll()
			.and()
			.logout().permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/access-denied");
		
	}
	
	@Bean
	public UserDetailsManager userDetailsManager() {
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
		jdbcUserDetailsManager.setDataSource(securityDataSource);
		//jdbcUserDetailsManager.setUsersByUsernameQuery("select username, password, enabled from Admins where username = ?");
		//jdbcUserDetailsManager.setUserExistsSql("select username from Admins where username = ?");
		//jdbcUserDetailsManager.setCreateUserSql("insert into Admins (Username, Password, Enabled) values (?,?,?)");

		return jdbcUserDetailsManager; 
	}
}
