package pl.mbm.service;

import pl.mbm.service.dto.UserJTable;

public interface ActivationService {

	UserJTable activateUser(String name, String activationCode);

}
