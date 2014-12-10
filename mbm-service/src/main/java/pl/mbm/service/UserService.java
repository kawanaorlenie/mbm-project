package pl.mbm.service;

import java.util.List;

import pl.mbm.model.dto.UserJTable;
import pl.mbm.model.dto.UserRegistrationForm;
import pl.mbm.model.entity.User;

public interface UserService {

	User createUser(User user);

	UserJTable registerUser(UserRegistrationForm user);

	List<UserJTable> listUsers();

}
