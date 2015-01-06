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
import pl.mbm.dao.util.TestUtils;
import pl.mbm.model.dto.UserJTable;
import pl.mbm.model.dto.UserRegistrationForm;
import pl.mbm.model.entity.User;
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
		User user = TestUtils.USER;

		Mockito.when(userDaoMock.save(user)).thenReturn(
				TestUtils.getUserWithId());

		// TODO : this should asserts that other fields are correct too, in
		// UserDaoTest there is already private method which does it
		assertEquals(TestUtils.USER_NAME, userService.createUser(user)
				.getName());
		verify(userDaoMock, times(1)).save(user);
	}

// @formatter:off
	@Test
	public void registerUser_ShouldRetrunUserJTable() {
		UserRegistrationForm userRegistrationForm = TestUtils.getUserRegistrationForm();
		User user = TestUtils.USER;

		Mockito.when(registrationValidatorMock.validate(userRegistrationForm))
			.thenReturn(userRegistrationForm);
		Mockito.when(conversionServiceMock.convert(userRegistrationForm, User.class))
			.thenReturn(user);
		Mockito.when(conversionServiceMock.convert(TestUtils.getUserWithId(), UserJTable.class))
			.thenReturn(TestUtils.getUserJTable());
		Mockito.doNothing().when(mailServiceMock)
			.sendActivationMail(TestUtils.USER,TestUtils.USER_ACTIVATION_CODE);
		Mockito.when(uuidGeneratorMock.generate())
			.thenReturn(TestUtils.USER_ACTIVATION_CODE);
		Mockito.when(passwordEncoderMock.encode(TestUtils.USER_PASSWORD))
			.thenReturn(TestUtils.USER_ENCODED_PASSWORD);
		Mockito.when(userDaoMock.save(TestUtils.USER))
			.thenReturn(TestUtils.getUserWithId());

		UserJTable userJTable = userService.registerUser(userRegistrationForm);
		assertEquals(TestUtils.USER_NAME, userJTable.getName());
		
		verify(registrationValidatorMock, times(1)).validate(userRegistrationForm);
		verify(conversionServiceMock, times(1)).convert(userRegistrationForm,User.class);
		verify(conversionServiceMock, times(1)).convert(TestUtils.getUserWithId(), UserJTable.class);
		verify(mailServiceMock, times(1)).sendActivationMail(TestUtils.USER,TestUtils.USER_ACTIVATION_CODE);
		verify(uuidGeneratorMock,times(1)).generate();
		verify(passwordEncoderMock,times(1)).encode(TestUtils.USER_PASSWORD);
		verify(userDaoMock,times(1)).save(TestUtils.USER);
	}
// @formatter:on
	@Test
	public void listUsers_ShouldRetrunListOfUsers() {
		// TODO:implement this
	}
}
