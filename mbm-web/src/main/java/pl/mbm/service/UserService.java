package pl.mbm.service;

import pl.mbm.entity.User;
import pl.mbm.service.dto.UserJTable;
import pl.mbm.service.dto.UserRegistrationForm;

import java.util.List;

public interface UserService {

	User createUser(User user);

	UserJTable registerUser(UserRegistrationForm user);

	List<UserJTable> listUsers();

}
