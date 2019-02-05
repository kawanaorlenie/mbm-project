package pl.mbm.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import pl.mbm.entity.UserBuilder;
import pl.mbm.constant.Roles;
import pl.mbm.dao.UserDao;
import pl.mbm.entity.User;

@Component
public class DatabaseFillerOnStartup implements
		ApplicationListener<ContextRefreshedEvent> {

	private static final String MATROM_NAME = "matrom";
	private static final String MATROM_PASSWORD = "password";

	@Autowired
	private UserDao userDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// brzydkie, ale na razie styknie
	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (userDao.findByName(MATROM_NAME) == null) {
			User matrom = new UserBuilder().name(MATROM_NAME)
					.password(passwordEncoder.encode(MATROM_PASSWORD))
					.email("matrom@matrom.pl").enabled(true)
					.role(Roles.ROLE_ADMIN).role(Roles.ROLE_USER).build();
			userDao.save(matrom);
		}

	}

}