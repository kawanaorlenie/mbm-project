package pl.mbm.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import pl.mbm.builder.UserBuilder;
import pl.mbm.dao.UserDao;
import pl.mbm.model.entity.User;

@Component
public class DatabaseFillerOnStartup implements
		ApplicationListener<ContextRefreshedEvent> {

	private static final String MATROM_NAME = "matrom";

	@Autowired
	private UserDao userDao;

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (userDao.findByName(MATROM_NAME) == null) {
			User matrom = new UserBuilder().name(MATROM_NAME)
					.password("password").email("matrom@matrom.pl")
					.enabled(true).role("ROLE_AMIDN").role("ROLE_USER").build();
			userDao.save(matrom);
		}

	}

}