package pl.mbm.configuration;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import pl.mbm.dao.RoleDao;
import pl.mbm.dao.UserDao;

@Configuration
@Import(ConversionServiceFactoryBeanContext.class)
public class ConverterTestContext {

	@Bean
	public UserDao userDaoMock() {
		return Mockito.mock(UserDao.class);
	}

	@Bean
	public RoleDao roleDaoMock() {
		return Mockito.mock(RoleDao.class);
	}
}
