package pl.mbm.configuration;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;

import pl.mbm.dao.UserDao;
import pl.mbm.model.dto.UserRegistrationForm;
import pl.mbm.service.impl.UserServiceImpl;
import pl.mbm.validator.Validator;

@Configuration
@ComponentScan(basePackageClasses = { UserServiceImpl.class })
// @Import(PersistenceContext.class)
public class UserServiceTestContext {
	@Bean
	public UserDao userDaoMock() {
		return Mockito.mock(UserDao.class);
	}

	@Bean
	public Validator<UserRegistrationForm> registrationValidatorMock() {
		return Mockito.mock(Validator.class);
	}

	@Bean
	public ConversionService conversionServiceMock() {
		return Mockito.mock(ConversionService.class);
	}

}
