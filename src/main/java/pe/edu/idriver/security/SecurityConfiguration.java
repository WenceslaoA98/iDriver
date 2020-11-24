package pe.edu.login.idriver.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UDetailsService uDetailsService;

	@Autowired
	private LoggingAccessDeniedHandler loggingAccessDeniedHandler;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		// super.configure(http); 
		http
			.authorizeRequests()
				.antMatchers("/idriver/index.html").permitAll()
				.antMatchers("/idriver/department/listar").hasRole("MANAGER")
				.antMatchers("/idriver/province/listar").hasRole("MANAGER")
				.antMatchers("/idriver/district/listar").hasRole("MANAGER")
				.antMatchers("/idriver/brand/listar").hasRole("MANAGER")
				.antMatchers("/idriver/carmodel/listar").hasRole("MANAGER")
				.antMatchers("/idriver/color/listar").hasRole("MANAGER")
				.antMatchers("/idriver/usuario/listar").hasRole("MANAGER")
				.antMatchers("/idriver/vehiculo/listar").hasRole("MANAGER")
				.antMatchers("/idriver/documento/listar").hasRole("MANAGER")
				.antMatchers("/idriver/licencia/listar").hasRole("MANAGER")
				.antMatchers("/idriver/polarizado/listar").hasRole("MANAGER")
				.antMatchers("/idriver/seguro/listar").hasRole("MANAGER")
				.antMatchers("/idriver/soat/listar").hasRole("MANAGER")
				.antMatchers("/idriver/tarjeta/listar").hasRole("MANAGER")
				.antMatchers("/idriver/usuario/listar").hasRole("DRIVER")
				.antMatchers("/idriver/vehiculo/listar").hasRole("DRIVER")
				.antMatchers("/idriver/soat/listar").hasRole("DRIVER")
				.antMatchers("/idriver/licencia/listar").hasRole("DRIVER")
				.antMatchers("/idriver/tarjeta/listar").hasRole("DRIVER")
				.antMatchers("/idriver/documento/listar").hasRole("DRIVER")
				.antMatchers("/idriver/seguro/listar").hasRole("DRIVER")
				.antMatchers("/idriver/polarizado/listar").hasRole("DRIVER")
				
			
				
			.and()
			.formLogin()
				.loginProcessingUrl("/signin")
				.loginPage("/idriver/login")
				.usernameParameter("inputUsername")
				.passwordParameter("inputPassword")
			.and()
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/idriver")
			.and()
			.rememberMe()
				.tokenValiditySeconds(2592000)
				.key("Cl4v3.")
				.rememberMeParameter("checkRememberMe")
				.userDetailsService(uDetailsService)
			.and()
				.exceptionHandling()
				.accessDeniedHandler(loggingAccessDeniedHandler);

	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// Aqui se crea el vinculo entre el Spring security y: el PasswordEncoder y
	// UDetailsService
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(this.uDetailsService);
		return daoAuthenticationProvider;
	}

}
