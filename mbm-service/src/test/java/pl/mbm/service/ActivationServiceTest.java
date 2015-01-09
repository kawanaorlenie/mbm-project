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
import pl.mbm.dao.util.DaoTestUtils;
import pl.mbm.service.dto.UserJTable;
import pl.mbm.service.util.ServiceTestUtils;

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
		Mockito.when(userDaoMock.findByName(DaoTestUtils.USER_NAME)).thenReturn(
				DaoTestUtils.getUserWithId());
		Mockito.when(userDaoMock.save(DaoTestUtils.getActivatedUserWithId()))
				.thenReturn(DaoTestUtils.getUserWithId());
		Mockito.when(
				conversionServiceMock.convert(DaoTestUtils.getUserWithId(),
						UserJTable.class)).thenReturn(
				ServiceTestUtils.getUserJTable());

		UserJTable user = activationService.activateUser(DaoTestUtils.USER_NAME,
				DaoTestUtils.USER_ACTIVATION_CODE);
		assertEquals(DaoTestUtils.USER_NAME, user.getName());
	}
}
