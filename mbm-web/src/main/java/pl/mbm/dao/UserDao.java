package pl.mbm.dao;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.mbm.entity.User;

import java.util.Set;

@Repository
public interface UserDao extends CrudRepository<User, Long> {

	User findByName(String name);

	User findByEmail(String email);

	User findByNameAndActivationCode(String name, String activationCode);

	@EntityGraph(value = "graph.User.roles", type = EntityGraphType.LOAD)
	Set<User> findAll();
	
}
