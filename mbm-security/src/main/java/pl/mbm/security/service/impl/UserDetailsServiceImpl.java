package pl.mbm.security.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.mbm.dao.UserDao;
import pl.mbm.model.entity.Role;

@Service
public class UserDetailsServiceImpl implements
		org.springframework.security.core.userdetails.UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String name)
			throws UsernameNotFoundException {
		UserDetails user = getUserDetails(userDao.findByName(name));
		return user;
	}

	private User getUserDetails(pl.mbm.model.entity.User user) {
		if (user == null)
			throw new UsernameNotFoundException("no user with this name");
		return new User(user.getName(), user.getPassword(), user.isEnabled(),
				true, true, true, getAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> getAuthorities(
			Set<Role> roles) {
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return authorities;
	}

}
