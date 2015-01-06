package pl.mbm.configuration;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pl.mbm.controller.RegistrationValidationController;
import pl.mbm.service.UserService;
import pl.mbm.service.validator.RegistrationValidator;

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

	@Bean
	public RegistrationValidationController registrationValidationController() {
		return new RegistrationValidationController();
	}
}
