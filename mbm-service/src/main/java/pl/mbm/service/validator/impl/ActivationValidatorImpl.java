package pl.mbm.service.validator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.mbm.dao.ActivationCodeDao;
import pl.mbm.exception.IncorrectCodeException;
import pl.mbm.model.entity.ActivationCode;
import pl.mbm.service.validator.ActivationValidator;

@Service
public class ActivationValidatorImpl implements ActivationValidator {

	@Autowired
	private ActivationCodeDao activationCodeDao;

	@Override
	@Transactional
	public ActivationCode validate(ActivationCode activationCode) {
		ActivationCode foundCode = activationCodeDao.findByCode(activationCode
				.getCode());
		if (foundCode != null
				&& foundCode.getUser().getName()
						.equals(activationCode.getUser().getName()))
			return foundCode;
		else
			throw new IncorrectCodeException();
	}
}
