package pl.mbm.configuration;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.mbm.dao.UserDao;
import pl.mbm.service.MailService;
import pl.mbm.service.UserService;
import pl.mbm.service.impl.UserServiceImpl;
import pl.mbm.service.util.UUIDGenerator;

@Configuration
public class UserServiceTestContext {
	@Bean
	public UserService userService() {
		return new UserServiceImpl();
	}

	@Bean
	public UserDao userDaoMock() {
		return Mockito.mock(UserDao.class);
	}

	@Bean
	public ConversionService conversionServiceMock() {
		return Mockito.mock(ConversionService.class);
	}

	@Bean
	public MailService mailServiceMock() {
		return Mockito.mock(MailService.class);
	}

	@Bean
	public UUIDGenerator uuidGeneratorMock() {
		return Mockito.mock(UUIDGenerator.class);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return Mockito.mock(PasswordEncoder.class);
	}

}
