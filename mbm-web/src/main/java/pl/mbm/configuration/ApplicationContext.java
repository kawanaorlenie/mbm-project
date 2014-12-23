package pl.mbm.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import pl.mbm.dao.configuration.PersistenceContext;
import pl.mbm.service.impl.UserServiceImpl;
import pl.mbm.validator.RegistrationValidator;

@Configuration
@ComponentScan(basePackageClasses = { UserServiceImpl.class,
		RegistrationValidator.class })
@Import({ ConversionServiceFactoryBeanContext.class, PersistenceContext.class,
		WebAppContext.class })
public class ApplicationContext {

}