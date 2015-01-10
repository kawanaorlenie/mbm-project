package pl.mbm.service;

import pl.mbm.model.entity.ResetPassword;
import pl.mbm.model.entity.User;

public interface MailService {

	void sendActivationMail(User user, String activationCode);

	void sendPasswordRecoveryMail(ResetPassword resetPassword);
}
