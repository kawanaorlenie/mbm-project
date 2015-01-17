package pl.mbm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import pl.mbm.dao.custom.UserDaoCustom;
import pl.mbm.model.entity.User;

public interface UserDao extends JpaRepository<User, Long>, UserDaoCustom,
		QueryDslPredicateExecutor<User> {

	User findByName(String name);

	User findByEmail(String email);
	
	User findByNameAndActivationCode(String name, String activationCode);

}
