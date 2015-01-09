package pl.mbm.service.builder;

import pl.mbm.service.dto.UserJTable;

public class UserJTableBuilder {

	private UserJTable userJTable = new UserJTable();

	public UserJTableBuilder id(Long id) {
		userJTable.setId(id);
		return this;
	}

	public UserJTableBuilder name(String name) {
		userJTable.setName(name);
		return this;
	}

	public UserJTableBuilder email(String email) {
		userJTable.setEmail(email);
		return this;
	}

	public UserJTableBuilder password(String password) {
		userJTable.setPassword(password);
		return this;
	}

	public UserJTableBuilder enabled(Boolean enabled) {
		userJTable.setEnabled(enabled);
		return this;
	}

	public UserJTableBuilder roleUser(Boolean roleUser) {
		userJTable.setRoleUser(roleUser);
		return this;
	}

	public UserJTableBuilder roleAdmin(Boolean roleAdmin) {
		userJTable.setRoleAdmin(roleAdmin);
		return this;
	}

	public UserJTable build() {
		return userJTable;
	}
}
