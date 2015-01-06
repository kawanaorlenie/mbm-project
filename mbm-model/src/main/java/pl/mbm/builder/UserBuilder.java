package pl.mbm.builder;

import java.util.HashSet;
import java.util.Set;

import pl.mbm.model.entity.Role;
import pl.mbm.model.entity.User;

public class UserBuilder {

	private User user = new User();

	public UserBuilder() {
		user.setRoles(new HashSet<Role>());
	}

	public UserBuilder id(Long id) {
		user.setId(id);
		return this;
	}

	public UserBuilder name(String name) {
		user.setName(name);
		return this;
	}

	public UserBuilder email(String email) {
		user.setEmail(email);
		return this;
	}

	public UserBuilder password(String password) {
		user.setPassword(password);
		return this;
	}

	public UserBuilder enabled(boolean enabled) {
		user.setEnabled(enabled);
		return this;
	}

	public UserBuilder roles(Set<Role> roles) {
		user.getRoles().addAll(roles);
		return this;
	}

	public UserBuilder role(String roleName) {
		user.getRoles().add(new Role(roleName));
		return this;
	}

	public UserBuilder role(Role role) {
		user.getRoles().add(role);
		return this;
	}

	public UserBuilder activationCode(String activationCode) {
		user.setActivationCode(activationCode);
		return this;
	}

	public User build() {
		return user;
	}

}
