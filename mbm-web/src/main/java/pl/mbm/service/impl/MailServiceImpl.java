package pl.mbm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.mail.MailException;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import pl.mbm.entity.ResetPassword;
import pl.mbm.entity.User;
import pl.mbm.service.MailService;

import javax.servlet.http.HttpServletRequest;

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

	public String createLink(User user, String activationCode) {

		return new StringBuffer(getFullContextPath())
				.append("/activation?code=").append(activationCode)
				.append("&name=").append(user.getName()).toString();
	}

	@Override
	public void sendPasswordRecoveryMail(ResetPassword resetPassword) {
		SimpleMailMessage msg = new SimpleMailMessage(
				(SimpleMailMessage) this.templateMessage);
		msg.setTo(resetPassword.getEmail());
		String text = String
				.format("To change your password click:  \n%s \n\nIgnore this mail, if you have not requested password recovery",
						createLink(resetPassword));
		msg.setText(text);
		try {
			this.mailSender.send(msg);
		} catch (MailException ex) {
			// simply log it and go on...
			System.err.println(ex.getMessage());
		}

	}

	private Object createLink(ResetPassword resetPassword) {
		return new StringBuffer(getFullContextPath())
				.append("/resetPassword?uuid=").append(resetPassword.getUuid())
				.append("&email=").append(resetPassword.getEmail()).toString();
	}

	private String getFullContextPath() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes()).getRequest();
		return new StringBuffer(request.getScheme()).append("://")
				.append(request.getServerName()).append(":")
				.append(request.getServerPort())
				.append(request.getContextPath()).toString();

	}
}
