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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.mbm.builder.UserBuilder;
import pl.mbm.builder.UserRegistrationFormBuilder;
import pl.mbm.configuration.UserServiceTestContext;
import pl.mbm.converter.UserJTableConverter;
import pl.mbm.dao.UserDao;
import pl.mbm.model.dto.UserJTable;
import pl.mbm.model.dto.UserRegistrationForm;
import pl.mbm.model.entity.User;
import pl.mbm.validator.Validator;

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
	private UserService userService;

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
		User user = new UserBuilder().name("user00").password("asdfasdf")
				.email("user00@mbm.pl").enabled(true).role("ROLE_NIKT").build();

		Mockito.when(userDaoMock.save(user)).thenReturn(assignId(user));

		// TODO : this should asserts that other fields are correct too, in
		// UserDaoTest there is already private method which does it
		assertEquals(assignId(user).getName(), userService.createUser(user)
				.getName());
		verify(userDaoMock, times(1)).save(user);
	}

	@Test
	public void registerUser_ShouldRetrunUserJTable() {
		UserRegistrationForm userRegistrationForm = new UserRegistrationFormBuilder()
				.name("user00").email("user00@mbm.pl").password("asdfasdf")
				.confirmPassword("asdfasdf").build();
		User user = new UserBuilder().name("user00").password("asdfasdf")
				.email("user00@mbm.pl").enabled(true).role("ROLE_USER").build();

		Mockito.when(registrationValidatorMock.validate(userRegistrationForm))
				.thenReturn(true);
		Mockito.when(
				conversionServiceMock.convert(userRegistrationForm, User.class))
				.thenReturn(user);
		Mockito.when(userDaoMock.save(user)).thenReturn(assignId(user));

		Mockito.when(conversionServiceMock.convert(user, UserJTable.class))
				.thenReturn(new UserJTableConverter().convert(user));

		UserJTable userJTable = userService.registerUser(userRegistrationForm);
		assertEquals(1L, userJTable.getId().longValue());
		assertEquals("user00", userJTable.getName());
		verify(registrationValidatorMock, times(1)).validate(
				userRegistrationForm);
		verify(conversionServiceMock, times(1)).convert(userRegistrationForm,
				User.class);
		verify(conversionServiceMock, times(1)).convert(user, UserJTable.class);
		verify(userDaoMock, times(1)).save(user);
	}

	@Test
	public void listUsers_ShouldRetrunListOfUsers() {
		// TODO:implement this
	}

	private User assignId(User user) {
		user.setId(1L);
		return user;
	}
}
