package pl.mbm.configuration;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import pl.mbm.dao.UserDao;
import pl.mbm.service.ActivationService;
import pl.mbm.service.impl.ActivationServiceImpl;

@Configuration
public class ActivationServiceTestContext {

	@Bean
	public UserDao userDaoMock() {
		return Mockito.mock(UserDao.class, Mockito.withSettings()
				.verboseLogging());
	}

	@Bean
	public ConversionService conversionServiceMock() {
		return Mockito.mock(ConversionService.class, Mockito.withSettings()
				.verboseLogging());
	}

	@Bean
	public ActivationService activationServiceMock() {
		return new ActivationServiceImpl();
	}
}
