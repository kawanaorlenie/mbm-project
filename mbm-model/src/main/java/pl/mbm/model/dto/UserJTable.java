package pl.mbm.model.dto;

public class UserJTable {

	private Long id;
	private String name;
	private String password;
	private String email;
	private Boolean enabled;
	private Boolean roleAdmin;
	private Boolean roleUser;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getRoleAdmin() {
		return roleAdmin;
	}

	public void setRoleAdmin(Boolean roleAdmin) {
		this.roleAdmin = roleAdmin;
	}

	public Boolean getRoleUser() {
		return roleUser;
	}

	public void setRoleUser(Boolean roleUser) {
		this.roleUser = roleUser;
	}

}
