package pl.mbm.configuration;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pl.mbm.service.UserService;

@Configuration
public class TestUserManagementControllerConfig {

	@Bean
	public UserService userServiceMock() {
		return Mockito.mock(UserService.class);
	}

}
