package pl.mbm.configuration;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.mbm.dao.ResetPasswordDao;
import pl.mbm.dao.UserDao;
import pl.mbm.service.MailService;
import pl.mbm.service.ResetPasswordService;
import pl.mbm.service.impl.ResetPasswordServiceImpl;
import pl.mbm.service.util.UUIDGenerator;

@Configuration
public class ResetPasswordServiceTestContext {

	@Bean
	public UUIDGenerator generatorMock() {
		return Mockito.mock(UUIDGenerator.class);
	}

	@Bean
	public MailService mailServiceMock() {
		return Mockito.mock(MailService.class);
	}

	@Bean
	public UserDao userDaoMock() {
		return Mockito.mock(UserDao.class);
	}

	@Bean
	public ResetPasswordDao resetPasswordDaoMock() {
		return Mockito.mock(ResetPasswordDao.class);
	}

	@Bean
	public ResetPasswordService resetPasswordService() {
		return new ResetPasswordServiceImpl();
	}
	
	@Bean PasswordEncoder passwordEncoder(){
		return Mockito.mock(PasswordEncoder.class);
	}

}
