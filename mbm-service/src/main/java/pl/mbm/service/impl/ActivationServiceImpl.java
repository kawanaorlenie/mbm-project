package pl.mbm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.mbm.dao.UserDao;
import pl.mbm.exception.ActivationException;
import pl.mbm.model.entity.User;
import pl.mbm.service.ActivationService;
import pl.mbm.service.dto.UserJTable;

@Service
public class ActivationServiceImpl implements ActivationService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private ConversionService conversionService;

	@Override
	@Transactional
	public UserJTable activateUser(String name, String activationCode) {
		User user = userDao.findByName(name);
		if (activationCode.equals(user.getActivationCode())) {
			user.setEnabled(true);
			UserJTable userJTable = conversionService.convert(
					userDao.save(user), UserJTable.class);
			return userJTable;
		} else
			throw new ActivationException();
	}
}
