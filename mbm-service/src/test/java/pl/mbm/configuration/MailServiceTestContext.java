package pl.mbm.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pl.mbm.service.MailService;
import pl.mbm.service.impl.MailServiceImpl;

@Configuration
public class MailServiceTestContext {

	@Bean
	public MailService mailService() {
		return new MailServiceImpl();
	}
}
