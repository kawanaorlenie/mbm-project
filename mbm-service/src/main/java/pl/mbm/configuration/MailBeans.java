package pl.mbm.configuration;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;

@Configuration
public class MailBeans {

	@Bean
	public JavaMailSenderImpl mailSender() {
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		sender.setHost("matrom.pl");
		sender.setUsername("noreply@matrom.pl");
		sender.setPassword("kijek123");
		Properties javaMailProps = new Properties();
		javaMailProps.put("mail.smtp.auth", "true");
		javaMailProps.put("mail.smtp.starttls.enable", "true");
		javaMailProps.put("mail.smtp.ssl.trust", "matrom.pl");
		javaMailProps.put("mail.debug", "true");

		sender.getJavaMailProperties().putAll(javaMailProps);
		return sender;
	}

	@Bean
	public SimpleMailMessage templateMessage() {
		SimpleMailMessage templateMessage = new SimpleMailMessage();
		templateMessage.setFrom("noreply@matrom.pl");
		templateMessage.setSubject("Activate your account");
		return templateMessage;
	}

	@Bean
	public VelocityEngineFactoryBean velocityEngine() {
		VelocityEngineFactoryBean velocityEngine = new VelocityEngineFactoryBean();
		Properties props = new Properties();
		props.put("resource.loader", "class");
		props.put("class.resource.loader.class",
				"org.apache.velocity.runtime.resource.loader."
						+ "ClasspathResourceLoader");
		velocityEngine.setVelocityProperties(props);
		return velocityEngine;
	}

}
