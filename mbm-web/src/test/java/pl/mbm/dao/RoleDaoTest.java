package pl.mbm.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import pl.mbm.entity.Role;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


@RunWith(SpringRunner.class)
@DataJpaTest
public class RoleDaoTest {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void findOneByNameTest() {
        entityManager.persist(new Role("ROLE_USER"));

        Role role = roleDao.findOneByName("ROLE_USER");
        assertEquals("ROLE_USER", role.getName());
    }

    @Test
    public void findOneByNameTest_ShouldReturnNull() {
        Role role = roleDao.findOneByName("ROLE_NIKT");
        assertNull(role);
    }
}
