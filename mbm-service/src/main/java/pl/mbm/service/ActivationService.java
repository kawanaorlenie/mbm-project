package pl.mbm.service;

import pl.mbm.model.entity.ActivationCode;
import pl.mbm.model.entity.User;

public interface ActivationService {

	User activateUser(ActivationCode activationCode);

}
