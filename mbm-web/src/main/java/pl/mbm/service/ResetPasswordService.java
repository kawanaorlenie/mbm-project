package pl.mbm.service;

import pl.mbm.entity.User;
import pl.mbm.service.dto.PasswordsForm;

public interface ResetPasswordService {

	boolean beginProcedure(String email);

	User changePassword(PasswordsForm passwordsForm);
}
