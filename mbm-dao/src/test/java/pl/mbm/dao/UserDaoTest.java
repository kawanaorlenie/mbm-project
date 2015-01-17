package pl.mbm.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static pl.mbm.dao.util.DaoTestUtils.USER_ACTIVATION_CODE;
import static pl.mbm.dao.util.DaoTestUtils.USER_NAME;
import static pl.mbm.dao.util.DaoTestUtils.getActivatedUserWithId;

import java.util.Collection;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import pl.mbm.builder.UserBuilder;
import pl.mbm.dao.configuration.PersistenceContextTest;
import pl.mbm.model.entity.User;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceContextTest.class })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@DatabaseSetup("UserData.xml")
public class UserDaoTest {

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;

	@Test
	public void findOne_NoUserEntryFound_ShouldRetrunNull() {
		User user = userDao.findOne(3L);
		assertNull(user);
	}

	@Test
	@Transactional
	public void findOne_ShouldReturnUser() {
		User user = userDao.findOne(1L);
		assertUserEquals(createExpectedUser(), user);
		assertEquals("should have two roles", 2, user.getRoles().size());
	}

	@Test
	@Transactional
	public void findByName_ShouldRetrunUser() {
		User user = userDao.findByName("matrom");
		assertUserEquals(createExpectedUser(), user);
		assertEquals("should have two roles", 2, user.getRoles().size());
	}

	@Test
	@Transactional
	public void findAll_ShouldRetrunCollectionOfUsers() {
		Collection<User> users = userDao.findAll();
		assertEquals("should have two users", 2, users.size());
	}

	@Test
	@Transactional
	public void save_ShouldAddUser() {
		User userToSave = new UserBuilder().name("user00")
				.email("user00@mbm.pl").password("asdf").enabled(true)
				.role(roleDao.findOneByName("ROLE_USER")).build();
		User user = userDao.save(userToSave);
		assertNotNull("should return user", user);
	}

	@Test
	public void findAllWithRolesTest() {
		Set<User> users = userDao.findAllWithRoles();
		assertEquals(2, users.size());
	}
	
	@Test
	public void findByNameAndActivationCodeTest(){
		userDao.save(getActivatedUserWithId());
		User user = userDao.findByNameAndActivationCode(USER_NAME, USER_ACTIVATION_CODE);
		assertNotNull(user);
	}

	private User createExpectedUser() {
		return new UserBuilder().id(1L).name("matrom").email("matrom@mbm.pl")
				.password("password").enabled(true).build();
	}

	private void assertUserEquals(User expectedUser, User user) {
		assertEquals("name should equals " + expectedUser.getName(),
				expectedUser.getName(), user.getName());
		assertEquals("id should equals " + expectedUser.getId(), expectedUser
				.getId().longValue(), user.getId().longValue());
		assertEquals("password should equals " + expectedUser.getPassword(),
				expectedUser.getPassword(), user.getPassword());
		assertEquals("email should equals " + expectedUser.getEmail(),
				expectedUser.getEmail(), user.getEmail());
		assertEquals("email should equals " + expectedUser.isEnabled(),
				expectedUser.isEnabled(), user.isEnabled());
	}
}
