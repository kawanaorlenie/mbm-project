package pl.mbm.service;

import pl.mbm.entity.ResetPassword;
import pl.mbm.entity.User;

public interface MailService {

    void sendActivationMail(User user, String activationCode);

    void sendPasswordRecoveryMail(ResetPassword resetPassword);
}
