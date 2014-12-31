package pl.mbm.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.mbm.model.entity.ActivationCode;

public interface ActivationCodeDao extends
		JpaRepository<ActivationCode, String> {

	ActivationCode findByCode(String code);

}
