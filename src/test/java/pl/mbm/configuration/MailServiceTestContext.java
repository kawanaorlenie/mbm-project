package pl.mbm.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import pl.mbm.service.MailService;
import pl.mbm.service.configuration.MailBeans;
import pl.mbm.service.impl.MailServiceImpl;

@Configuration
@Import(MailBeans.class)
public class MailServiceTestContext {

	@Bean
	public MailService mailService() {
		return new MailServiceImpl();
	}
}
