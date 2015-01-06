package pl.mbm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.mail.MailException;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import pl.mbm.model.entity.ActivationCode;
import pl.mbm.service.MailService;

@Service
@DependsOn("templateMessage")
public class MailServiceImpl implements MailService {

	@Autowired
	private MailMessage templateMessage;
	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void sendActivationMail(ActivationCode activationCode) {
		SimpleMailMessage msg = new SimpleMailMessage(
				(SimpleMailMessage) this.templateMessage);
		msg.setTo(activationCode.getUser().getEmail());
		String text = String.format(
				"Dear %s, \n \nTo activate your account click:  \n%s",
				activationCode.getUser().getName(), createLink(activationCode));
		System.out.println("text: " + text);
		msg.setText(text);
		try {
			this.mailSender.send(msg);
		} catch (MailException ex) {
			// simply log it and go on...
			System.err.println(ex.getMessage());
		}
	}

	private String createLink(ActivationCode savedActivationCode) {
		return new StringBuffer("http://matrom.pl/activation?code=")
				.append(savedActivationCode.getCode()).append("&name=")
				.append(savedActivationCode.getUser().getName()).toString();
	}

}
