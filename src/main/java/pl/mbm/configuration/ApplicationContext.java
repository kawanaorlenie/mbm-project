package pl.mbm.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import pl.mbm.controller.UserManagementController;
import pl.mbm.listener.DatabaseFillerOnStartup;
import pl.mbm.security.SecurityConfig;
import pl.mbm.service.UserService;
import pl.mbm.service.configuration.ConversionServiceFactoryBeanContext;
import pl.mbm.service.configuration.MailBeans;
import pl.mbm.service.impl.UserServiceImpl;
import pl.mbm.service.util.UUIDGenerator;

@Configuration
@ComponentScan(basePackageClasses = {UserServiceImpl.class, UserService.class,
        DatabaseFillerOnStartup.class, UUIDGenerator.class,
        UserManagementController.class})
@Import({
        ConversionServiceFactoryBeanContext.class,
        MailBeans.class, WebAppContext.class, SecurityConfig.class})
public class ApplicationContext {

}