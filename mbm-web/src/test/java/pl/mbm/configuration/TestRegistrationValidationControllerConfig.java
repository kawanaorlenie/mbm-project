package pl.mbm.configuration;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pl.mbm.service.UserService;
import pl.mbm.validator.RegistrationValidator;

@Configuration
public class TestRegistrationValidationControllerConfig {

	@Bean
	public RegistrationValidator registrationValidatorMock() {
		return Mockito.mock(RegistrationValidator.class);
	}

	@Bean
	public UserService userServiceMock() {
		return Mockito.mock(UserService.class);
	}
}
