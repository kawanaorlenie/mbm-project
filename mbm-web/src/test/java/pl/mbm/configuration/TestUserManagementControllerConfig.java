package pl.mbm.configuration;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

import pl.mbm.service.UserService;

@Configuration
public class TestUserManagementControllerConfig {

	@Bean
	public UserService userServiceMock() {
		return Mockito.mock(UserService.class);
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return Mockito.mock(UserDetailsService.class);
	}

}
