package pl.mbm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import pl.mbm.dao.custom.UserDaoCustom;
import pl.mbm.model.entity.User;

public interface UserDao extends JpaRepository<User, Long>, UserDaoCustom,
		QueryDslPredicateExecutor<User> {

	public User findByName(String name);

	public User findByEmail(String email);

}
