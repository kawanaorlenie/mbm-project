package pl.mbm.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.mbm.configuration.ResetPasswordServiceTestContext;
import pl.mbm.dao.ResetPasswordDao;
import pl.mbm.dao.util.DaoTestUtils;
import pl.mbm.service.util.ServiceTestUtils;
import pl.mbm.service.util.UUIDGenerator;

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
	private ResetPasswordService resetPasswordService;

	@Test
	public void beginProcedureTest() {
		Mockito.when(generatorMock.generate()).thenReturn(
				DaoTestUtils.USER_ACTIVATION_CODE);
		Mockito.doNothing().when(mailServiceMock)
				.sendPasswordRecoveryMail(ServiceTestUtils.getResetPassword());
		Mockito.when(
				resetPasswordDaoMock.save(ServiceTestUtils.getResetPassword()))
				.thenReturn(ServiceTestUtils.getResetPassword());

		assertTrue(resetPasswordService
				.beginProcedure(ServiceTestUtils.USER_EMAIL));

	}
}
