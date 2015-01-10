package pl.mbm.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;

import pl.mbm.controller.UserManagementController;
import pl.mbm.dao.configuration.PersistenceContext;
import pl.mbm.listener.DatabaseFillerOnStartup;
import pl.mbm.security.configuration.SecurityConfig;
import pl.mbm.service.UserService;
import pl.mbm.service.configuration.ConversionServiceFactoryBeanContext;
import pl.mbm.service.configuration.MailBeans;
import pl.mbm.service.impl.UserServiceImpl;
import pl.mbm.service.util.UUIDGenerator;
import pl.mbm.service.validator.RegistrationValidator;

@Configuration
@ComponentScan(basePackageClasses = { UserServiceImpl.class, UserService.class,
		RegistrationValidator.class, DatabaseFillerOnStartup.class,
		UUIDGenerator.class, UserManagementController.class })
@Import({ ConversionServiceFactoryBeanContext.class, MailBeans.class,
		PersistenceContext.class, WebAppContext.class, SecurityConfig.class })
public class ApplicationContext {

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("i18n/messages");
		messageSource.setUseCodeAsDefaultMessage(true);
		return messageSource;
	}

}