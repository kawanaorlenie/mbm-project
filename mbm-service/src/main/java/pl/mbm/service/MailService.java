package pl.mbm.service;

import pl.mbm.model.entity.ActivationCode;

public interface MailService {

	void sendActivationMail(ActivationCode activationCode);
}
