package pl.mbm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.mbm.dao.UserDao;
import pl.mbm.model.entity.ActivationCode;
import pl.mbm.model.entity.User;
import pl.mbm.service.ActivationService;
import pl.mbm.service.validator.ActivationValidator;

@Service
public class ActivationServiceImpl implements ActivationService {

	@Autowired
	private ActivationValidator activationValidator;
	@Autowired
	private UserDao userDao;

	@Override
	@Transactional
	public User activateUser(ActivationCode activationCode) {
		ActivationCode validActivationCode = activationValidator
				.validate(activationCode);
		validActivationCode.getUser().setEnabled(true);
		return userDao.save(validActivationCode.getUser());
	}

}
