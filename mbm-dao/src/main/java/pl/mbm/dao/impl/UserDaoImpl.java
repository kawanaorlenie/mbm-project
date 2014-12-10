package pl.mbm.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import pl.mbm.dao.custom.UserDaoCustom;
import pl.mbm.model.entity.QRole;
import pl.mbm.model.entity.QUser;
import pl.mbm.model.entity.User;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;

public class UserDaoImpl implements UserDaoCustom {

	@Autowired
	private EntityManagerFactory emf;

	@Override
	public Set<User> findAllWithRoles() {
		JPQLQuery query = new JPAQuery(emf.createEntityManager());
		QUser user = QUser.user;

		List<User> users = query.from(user).leftJoin(user.roles, QRole.role)
				.fetch().list(user);
		// TODO to nie powinno byc zamieniane na set w taki sposob
		return new HashSet<User>(users);
	}

}
