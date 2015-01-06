package pl.mbm.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.mbm.configuration.ActivationServiceTestContext;
import pl.mbm.dao.UserDao;
import pl.mbm.dao.util.TestUtils;
import pl.mbm.model.dto.UserJTable;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ActivationServiceTestContext.class })
public class ActivationServiceTest {

	@Autowired
	private UserDao userDaoMock;
	@Autowired
	private ActivationService activationService;
	@Autowired
	private ConversionService conversionServiceMock;

	@Test
	public void autowiredTest() {
		assertNotNull(userDaoMock);
		assertNotNull(activationService);
	}

	@Test
	public void activateUserTest() {
		Mockito.when(userDaoMock.findByName(TestUtils.USER_NAME)).thenReturn(
				TestUtils.getUserWithId());
		Mockito.when(userDaoMock.save(TestUtils.getActivatedUserWithId()))
				.thenReturn(TestUtils.getUserWithId());
		Mockito.when(
				conversionServiceMock.convert(TestUtils.getUserWithId(),
						UserJTable.class))
				.thenReturn(TestUtils.getUserJTable());

		UserJTable user = activationService.activateUser(TestUtils.USER_NAME,
				TestUtils.USER_ACTIVATION_CODE);
		assertEquals(TestUtils.USER_NAME, user.getName());
	}
}
