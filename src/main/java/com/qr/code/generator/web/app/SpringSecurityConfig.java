package com.qr.code.generator.web.app;

import com.qr.code.generator.web.app.auth.LoginSuccessHandler;
//import com.qr.code.generator.web.app.service.UserDetailServiceImpl;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled=true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private LoginSuccessHandler successHandler;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	//http.cors().and().csrf().disable();
/*		
        http.authorizeRequests()
                .antMatchers("/", "/css/**", "/js/**", "/images/**").permitAll()
                .antMatchers("/QRCode/**").hasAnyRole("ADMIN")
                .anyRequest().authenticated().and().formLogin().successHandler(successHandler).loginPage("/QRCode/login")
                .permitAll().and().logout().permitAll().and().exceptionHandling().accessDeniedPage("/QRCode/Err403");
*/

        http.authorizeRequests().antMatchers("/", "/css/**", "/js/**", "/vendor/**", "/images/**").permitAll()
        .anyRequest().authenticated()
		.and()
		    .formLogin()
		        .successHandler(successHandler)
		        .loginPage("/login")
		    .permitAll()
		.and()
		.logout().permitAll()
		.and()
		.exceptionHandling().accessDeniedPage("/error_403");
    }

    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {
		build.jdbcAuthentication()
		.dataSource(dataSource)
		.passwordEncoder(passwordEncoder)
		.usersByUsernameQuery("SELECT username, pass, enabled FROM users WHERE username=?")
		.authoritiesByUsernameQuery("SELECT u.username, r.rolename FROM users u INNER JOIN roles r ON u.role_id = r.role_id WHERE u.username=?");
    }

}