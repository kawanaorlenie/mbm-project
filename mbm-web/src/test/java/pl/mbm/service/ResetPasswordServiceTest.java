package pl.mbm.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.mbm.configuration.ResetPasswordServiceTestContext;
import pl.mbm.dao.ResetPasswordDao;
import pl.mbm.dao.UserDao;
import pl.mbm.entity.User;
import pl.mbm.service.util.UUIDGenerator;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static pl.mbm.dao.util.DaoTestUtils.*;
import static pl.mbm.service.util.ServiceTestUtils.getPasswordsForm;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ResetPasswordServiceTestContext.class })
public class ResetPasswordServiceTest {

	@Autowired
	private UUIDGenerator generatorMock;
	@Autowired
	private MailService mailServiceMock;
	@Autowired
	private ResetPasswordDao resetPasswordDaoMock;
	@Autowired
	private UserDao userDaoMock;
	@Autowired
	private PasswordEncoder passwordEncoderMock;

	@Autowired
	private ResetPasswordService resetPasswordService;

	@Test
	public void beginProcedureTest() {
		Mockito.when(generatorMock.generate())
			.thenReturn(USER_ACTIVATION_CODE);
		Mockito.doNothing().when(mailServiceMock).sendPasswordRecoveryMail(getResetPassword());
		Mockito.when(resetPasswordDaoMock.save(getResetPassword()))
			.thenReturn(getResetPassword());

		assertTrue(resetPasswordService.beginProcedure(USER_EMAIL));

	}
	
	@Test
	public void changePasswordTest(){
		Mockito.when(resetPasswordDaoMock.findByEmailAndUuid(USER_EMAIL, USER_ACTIVATION_CODE))
			.thenReturn(getResetPassword());
		Mockito.when(userDaoMock.findByEmail(USER_EMAIL))
			.thenReturn(getUserWithId());
		Mockito.when(passwordEncoderMock.encode(USER_PASSWORD))
			.thenReturn(USER_PASSWORD);
		Mockito.when(userDaoMock.save(getUserWithId()))
			.thenReturn(getUserWithId());
		
		User user = resetPasswordService.changePassword(getPasswordsForm());
		
		assertNotNull(user);
		
		Mockito.verify(resetPasswordDaoMock,Mockito.times(1)).findByEmailAndUuid(USER_EMAIL, USER_ACTIVATION_CODE);
		Mockito.verify(userDaoMock,Mockito.times(1)).findByEmail(USER_EMAIL);
		Mockito.verify(passwordEncoderMock,Mockito.times(1)).encode(USER_PASSWORD);
		Mockito.verify(userDaoMock,Mockito.times(1)).save(getUserWithId());
	}
}
