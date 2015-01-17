package pl.mbm.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static pl.mbm.dao.util.DaoTestUtils.USER_ACTIVATION_CODE;
import static pl.mbm.dao.util.DaoTestUtils.USER_NAME;
import static pl.mbm.dao.util.DaoTestUtils.getActivatedUserWithId;
import static pl.mbm.dao.util.DaoTestUtils.getUserWithId;
import static pl.mbm.service.util.ServiceTestUtils.getUserJTable;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.mbm.configuration.ActivationServiceTestContext;
import pl.mbm.dao.UserDao;
import pl.mbm.exception.ActivationException;
import pl.mbm.service.dto.UserJTable;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ActivationServiceTestContext.class })
public class ActivationServiceTest {

	@Autowired
	private UserDao userDaoMock;
	@Autowired
	private ActivationService activationService;
	@Autowired
	private ConversionService conversionServiceMock;
	
	@Before
	public void setUp(){
		Mockito.reset(userDaoMock,conversionServiceMock);
	}

	@Test
	public void autowiredTest() {
		assertNotNull(userDaoMock);
		assertNotNull(activationService);
	}

	@Test
	public void activateUserTest() {
		Mockito.when(userDaoMock.findByNameAndActivationCode(USER_NAME,	USER_ACTIVATION_CODE))
			.thenReturn(getUserWithId());
		Mockito.when(userDaoMock.save(getActivatedUserWithId()))
			.thenReturn(getUserWithId());
		Mockito.when(conversionServiceMock.convert(getUserWithId(), UserJTable.class))
			.thenReturn(getUserJTable());

		UserJTable user = activationService.activateUser(USER_NAME,USER_ACTIVATION_CODE);
		
		assertEquals(USER_NAME, user.getName());
		
		Mockito.verify(userDaoMock,Mockito.times(1)).findByNameAndActivationCode(USER_NAME, USER_ACTIVATION_CODE);
		Mockito.verify(userDaoMock,Mockito.times(1)).save(getActivatedUserWithId());
		Mockito.verify(conversionServiceMock,Mockito.times(1)).convert(getUserWithId(), UserJTable.class);
	}
	
	@Test(expected=ActivationException.class)
	public void activateUserTest_ShouldThrowException() {
		Mockito.when(userDaoMock.findByNameAndActivationCode(USER_NAME,	USER_ACTIVATION_CODE))
			.thenReturn(null);
		Mockito.when(userDaoMock.save(getActivatedUserWithId()))
			.thenReturn(getUserWithId());
		Mockito.when(conversionServiceMock.convert(getUserWithId(), UserJTable.class))
			.thenReturn(getUserJTable());

		activationService.activateUser(USER_NAME,USER_ACTIVATION_CODE);
		
		Mockito.verifyNoMoreInteractions(conversionServiceMock);
		Mockito.verify(userDaoMock,Mockito.times(1)).findByNameAndActivationCode(USER_NAME, USER_ACTIVATION_CODE);
		Mockito.verifyNoMoreInteractions(userDaoMock);
		
	}
}
