package pl.mbm.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.mail.MailException;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import pl.mbm.model.entity.User;
import pl.mbm.service.MailService;

@Service
@DependsOn("templateMessage")
public class MailServiceImpl implements MailService {

	@Autowired
	private MailMessage templateMessage;
	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void sendActivationMail(User user, String activationCode) {
		SimpleMailMessage msg = new SimpleMailMessage(
				(SimpleMailMessage) this.templateMessage);
		msg.setTo(user.getEmail());
		String text = String.format(
				"Dear %s, \n \nTo activate your account click:  \n%s",
				user.getName(), createLink(user, activationCode));
		msg.setText(text);
		try {
			this.mailSender.send(msg);
		} catch (MailException ex) {
			// simply log it and go on...
			System.err.println(ex.getMessage());
		}

	}

	private String createLink(User user, String activationCode) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes()).getRequest();
		System.out.println("CP: " + request.getContextPath());
		return new StringBuffer(request.getContextPath())
				.append("/activation?code=").append(activationCode)
				.append("&name=").append(user.getName()).toString();
	}
}
