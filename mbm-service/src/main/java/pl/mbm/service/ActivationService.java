package pl.mbm.service;

import pl.mbm.model.dto.UserJTable;

public interface ActivationService {

	UserJTable activateUser(String name, String activationCode);

}
