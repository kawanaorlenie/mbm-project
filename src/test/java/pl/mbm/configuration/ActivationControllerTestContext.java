package pl.mbm.configuration;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pl.mbm.controller.ActivationController;
import pl.mbm.service.ActivationService;

@Configuration
public class ActivationControllerTestContext {

	@Bean
	public ActivationService activationServiceMock() {
		return Mockito.mock(ActivationService.class);
	}

	@Bean
	public ActivationController activationController() {
		return new ActivationController();
	}
}
