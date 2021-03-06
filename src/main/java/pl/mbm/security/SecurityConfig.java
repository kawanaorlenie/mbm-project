package pl.mbm.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import pl.mbm.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = { UserDetailsServiceImpl.class })
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/resources/**").permitAll()
				.antMatchers("/login**","/register", "/validate/registerForm/**", "/activation**", "/forgot**", "/resetPassword**").anonymous()
				.antMatchers("/admin/**").hasRole("ADMIN")
				// all requests require authentication
				.anyRequest().authenticated()
				// custom login page
				.and().formLogin().loginPage("/login").permitAll(false)
				// logout after "logout" get request
				.and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				// redirect to "/" when unauthorized
				.and().exceptionHandling().accessDeniedPage("/");
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(
				passwordEncoder());
	}

}
