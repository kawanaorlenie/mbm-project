package pl.mbm.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import pl.mbm.dao.configuration.PersistenceContext;
import pl.mbm.listener.DatabaseFillerOnStartup;
import pl.mbm.security.configuration.SecurityConfig;
import pl.mbm.service.impl.UserServiceImpl;
import pl.mbm.validator.RegistrationValidator;

@Configuration
@ComponentScan(basePackageClasses = { UserServiceImpl.class,
		RegistrationValidator.class, DatabaseFillerOnStartup.class })
@Import({ ConversionServiceFactoryBeanContext.class, PersistenceContext.class,
		WebAppContext.class, SecurityConfig.class })
public class ApplicationContext {

}