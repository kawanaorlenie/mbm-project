package pl.mbm.dao.custom;

import java.util.Set;

import pl.mbm.model.entity.User;

public interface UserDaoCustom {

	Set<User> findAllWithRoles();

}
