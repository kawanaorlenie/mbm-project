package pl.mbm.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.mbm.configuration.UserServiceTestContext;
import pl.mbm.dao.UserDao;
import pl.mbm.dao.util.DaoTestUtils;
import pl.mbm.model.entity.User;
import pl.mbm.service.dto.UserJTable;
import pl.mbm.service.dto.UserRegistrationForm;
import pl.mbm.service.util.ServiceTestUtils;
import pl.mbm.service.util.UUIDGenerator;
import pl.mbm.service.validator.Validator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { UserServiceTestContext.class })
public class UserServiceTest {

	@Autowired
	private UserDao userDaoMock;

	@Autowired
	private Validator<UserRegistrationForm> registrationValidatorMock;

	@Autowired
	private ConversionService conversionServiceMock;

	@Autowired
	private MailService mailServiceMock;

	@Autowired
	private UserService userService;

	@Autowired
	private UUIDGenerator uuidGeneratorMock;

	@Autowired
	private PasswordEncoder passwordEncoderMock;

	@Before
	public void setUp() {
		Mockito.reset(userDaoMock, registrationValidatorMock,
				conversionServiceMock);
	}

	@Test
	public void autowireUserDaoTest() {
		assertNotNull(userDaoMock);
	}

	@Test
	public void createUser_ShouldReturnUser() {
		User user = DaoTestUtils.USER;

		Mockito.when(userDaoMock.save(user)).thenReturn(
				DaoTestUtils.getUserWithId());

		// TODO : this should asserts that other fields are correct too, in
		// UserDaoTest there is already private method which does it
		assertEquals(DaoTestUtils.USER_NAME, userService.createUser(user)
				.getName());
		verify(userDaoMock, times(1)).save(user);
	}

// @formatter:off
	@Test
	public void registerUser_ShouldRetrunUserJTable() {
		UserRegistrationForm userRegistrationForm = ServiceTestUtils.getUserRegistrationForm();
		User user = DaoTestUtils.USER;

		Mockito.when(registrationValidatorMock.validate(userRegistrationForm))
			.thenReturn(userRegistrationForm);
		Mockito.when(conversionServiceMock.convert(userRegistrationForm, User.class))
			.thenReturn(user);
		Mockito.when(conversionServiceMock.convert(DaoTestUtils.getUserWithId(), UserJTable.class))
			.thenReturn(ServiceTestUtils.getUserJTable());
		Mockito.doNothing().when(mailServiceMock)
			.sendActivationMail(DaoTestUtils.USER,DaoTestUtils.USER_ACTIVATION_CODE);
		Mockito.when(uuidGeneratorMock.generate())
			.thenReturn(DaoTestUtils.USER_ACTIVATION_CODE);
		Mockito.when(passwordEncoderMock.encode(DaoTestUtils.USER_PASSWORD))
			.thenReturn(DaoTestUtils.USER_ENCODED_PASSWORD);
		Mockito.when(userDaoMock.save(DaoTestUtils.USER))
			.thenReturn(DaoTestUtils.getUserWithId());

		UserJTable userJTable = userService.registerUser(userRegistrationForm);
		assertEquals(DaoTestUtils.USER_NAME, userJTable.getName());
		
		verify(registrationValidatorMock, times(1)).validate(userRegistrationForm);
		verify(conversionServiceMock, times(1)).convert(userRegistrationForm,User.class);
		verify(conversionServiceMock, times(1)).convert(DaoTestUtils.getUserWithId(), UserJTable.class);
		verify(mailServiceMock, times(1)).sendActivationMail(DaoTestUtils.USER,DaoTestUtils.USER_ACTIVATION_CODE);
		verify(uuidGeneratorMock,times(1)).generate();
		verify(passwordEncoderMock,times(1)).encode(DaoTestUtils.USER_PASSWORD);
		verify(userDaoMock,times(1)).save(DaoTestUtils.USER);
	}
// @formatter:on
	@Test
	public void listUsers_ShouldRetrunListOfUsers() {
		// TODO:implement this
	}
}
