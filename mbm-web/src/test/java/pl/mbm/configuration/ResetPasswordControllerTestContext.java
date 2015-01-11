package pl.mbm.configuration;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pl.mbm.controller.ResetPasswordController;
import pl.mbm.controller.RestErrorHandler;
import pl.mbm.dao.UserDao;
import pl.mbm.service.ResetPasswordService;

@Configuration
public class ResetPasswordControllerTestContext {

	@Bean
	public ResetPasswordService resetPasswordServiceMock() {
		return Mockito.mock(ResetPasswordService.class);
	}

	@Bean
	public UserDao userDaoMock() {
		return Mockito.mock(UserDao.class);
	}

	@Bean
	public ResetPasswordController resetPasswordController() {
		return new ResetPasswordController();
	}

	@Bean
	public RestErrorHandler restErrorHandler() {
		return new RestErrorHandler();
	}
}
