package pl.mbm.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.mbm.entity.Role;
import pl.mbm.entity.User;
import pl.mbm.entity.UserBuilder;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;
import static pl.mbm.dao.util.DaoTestUtils.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void findOne_NoUserEntryFound_ShouldRetrunNull() {
        User user = userDao.findById(3L).orElseGet(() -> null);
        assertNull(user);
    }

    @Test
    @Transactional
    public void findOne_ShouldReturnUser() {
        User givenUser = givenUser();
        entityManager.persist(givenUser);

        User user = userDao.findById(givenUser.getId()).get();

        assertUserEquals(givenUser, user);
        assertEquals("should have two roles", 2, user.getRoles().size());
    }

    private User givenUser() {
        return givenUser("matrom");
    }

    private User givenUser(String name) {
        Role admin = new Role("ROLE_ADMIN");
        Role user = new Role("ROLE_USER");
//        entityManager.persist(admin);
//        entityManager.persist(user);

        return new UserBuilder().name(name).email("matrom@mbm.pl")
                .password("password").enabled(true).role(admin).role(user).build();
    }

    @Test
    @Transactional
    public void findByName_ShouldRetrunUser() {
        User givenUser = givenUser();
        entityManager.persist(givenUser);

        User user = userDao.findByName("matrom");

        assertUserEquals(givenUser, user);
        assertEquals("should have two roles", 2, user.getRoles().size());
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
    public void findByNameAndActivationCodeTest() {
        User givenUser = new UserBuilder().email(USER_EMAIL).name(USER_NAME)
                .enabled(true).password(USER_PASSWORD).role(USER_ROLE_TEST)
                .activationCode(USER_ACTIVATION_CODE).id(USER_ID).build();

        userDao.save(givenUser);
        User user = userDao.findByNameAndActivationCode(USER_NAME, USER_ACTIVATION_CODE);
        assertNotNull(user);
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
