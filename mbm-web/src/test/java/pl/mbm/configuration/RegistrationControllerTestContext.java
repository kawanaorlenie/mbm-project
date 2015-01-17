package pl.mbm.configuration;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pl.mbm.controller.RegistrationController;
import pl.mbm.controller.RestErrorHandler;
import pl.mbm.dao.UserDao;
import pl.mbm.service.UserService;

@Configuration
public class RegistrationControllerTestContext {

	@Bean
	public UserService userServiceMock(){
		return Mockito.mock(UserService.class);
	}
	
	@Bean
	public UserDao userDaoMock(){
		return Mockito.mock(UserDao.class);
	}

	
	@Bean
	public RegistrationController registrationController(){
		return new RegistrationController();
	}
	
	@Bean
	public RestErrorHandler restErrorHandler() {
		return new RestErrorHandler();
	}
}
