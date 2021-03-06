package pl.mbm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mbm.dao.ResetPasswordDao;
import pl.mbm.dao.UserDao;
import pl.mbm.entity.ResetPassword;
import pl.mbm.entity.User;
import pl.mbm.exception.ResetPasswordException;
import pl.mbm.service.MailService;
import pl.mbm.service.ResetPasswordService;
import pl.mbm.service.dto.PasswordsForm;
import pl.mbm.service.util.UUIDGenerator;

@Service
public class ResetPasswordServiceImpl implements ResetPasswordService {

	@Autowired
	private UUIDGenerator generator;
	@Autowired
	private MailService mailService;
	@Autowired
	private UserDao userDao;
	@Autowired
	private ResetPasswordDao resetPasswordDao;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public boolean beginProcedure(String email) {
		String uuid = generator.generate();
		ResetPassword resetPassword = resetPasswordDao.save(new ResetPassword(
				email, uuid));
		mailService.sendPasswordRecoveryMail(resetPassword);
		return true;
	}

	@Override
	@Transactional
	public User changePassword(PasswordsForm passwordsForm) {
		ResetPassword resetPassword = resetPasswordDao.findByEmailAndUuid(passwordsForm.getEmail(), passwordsForm.getUuid());
		if(resetPassword==null)
			throw new ResetPasswordException();
		User user = userDao.findByEmail(passwordsForm.getEmail());
		user.setPassword(passwordEncoder.encode(passwordsForm.getPassword()));
		return userDao.save(user);
	}
}
