package pl.mbm.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.mbm.model.entity.ResetPassword;

public interface ResetPasswordDao extends JpaRepository<ResetPassword, String> {
	
	ResetPassword findByEmailAndUuid(String email,String uuid);

}
