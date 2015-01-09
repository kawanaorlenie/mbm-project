package pl.mbm.service;

import java.util.List;

import pl.mbm.model.entity.User;
import pl.mbm.service.dto.UserJTable;
import pl.mbm.service.dto.UserRegistrationForm;

public interface UserService {

	User createUser(User user);

	UserJTable registerUser(UserRegistrationForm user);

	List<UserJTable> listUsers();

}
