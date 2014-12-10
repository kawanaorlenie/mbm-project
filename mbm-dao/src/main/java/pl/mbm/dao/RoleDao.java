package pl.mbm.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.mbm.model.entity.Role;

public interface RoleDao extends JpaRepository<Role, Long> {

	Role findOneByName(String name);

}
