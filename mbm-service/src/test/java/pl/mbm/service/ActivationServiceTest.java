package pl.mbm.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.mbm.configuration.ActivationServiceTestContext;
import pl.mbm.dao.UserDao;
import pl.mbm.dao.util.TestUtils;
import pl.mbm.model.entity.User;
import pl.mbm.service.validator.ActivationValidator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ActivationServiceTestContext.class })
public class ActivationServiceTest {

	@Autowired
	private UserDao userDaoMock;
	@Autowired
	private ActivationValidator activationValidatorMock;
	@Autowired
	private ActivationService activationService;

	@Test
	public void autowiredTest() {
		assertNotNull(userDaoMock);
		assertNotNull(activationValidatorMock);
	}

	@Test
	public void activateUserTest() {
		Mockito.when(userDaoMock.save(TestUtils.getUserWithId())).thenReturn(
				TestUtils.getUserWithId());
		Mockito.when(
				activationValidatorMock.validate(TestUtils.getActivationCode()))
				.thenReturn(TestUtils.getActivationCodeWithId());
		User activateUser = activationService.activateUser(TestUtils
				.getActivationCode());
		assertEquals(TestUtils.USER_NAME, activateUser.getName());
	}
}
