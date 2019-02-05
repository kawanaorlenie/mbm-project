package pl.mbm.service.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailBeans {

	@Bean
	public JavaMailSender mailSender() {
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
	public MailMessage templateMessage() {
		SimpleMailMessage templateMessage = new SimpleMailMessage();
		templateMessage.setFrom("noreply@matrom.pl");
		templateMessage.setSubject("Activate your account");
		return templateMessage;
	}
}
