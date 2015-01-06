package pl.mbm.configuration;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pl.mbm.dao.UserDao;
import pl.mbm.service.ActivationService;
import pl.mbm.service.impl.ActivationServiceImpl;
import pl.mbm.service.validator.ActivationValidator;

@Configuration
public class ActivationServiceTestContext {

	@Bean
	public ActivationValidator activationValidatorMock() {
		return Mockito.mock(ActivationValidator.class, Mockito.withSettings()
				.verboseLogging());
	}

	@Bean
	public UserDao userDaoMock() {
		return Mockito.mock(UserDao.class, Mockito.withSettings()
				.verboseLogging());
	}

	@Bean
	public ActivationService activationService() {
		return new ActivationServiceImpl();
	}
}
