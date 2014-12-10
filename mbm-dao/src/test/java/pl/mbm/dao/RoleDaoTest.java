package pl.mbm.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import pl.mbm.dao.configuration.PersistenceContext;
import pl.mbm.model.entity.Role;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceContext.class })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@DatabaseSetup(value = "UserData.xml")
public class RoleDaoTest {

	@Autowired
	private RoleDao roleDao;

	@Test
	public void findOneByNameTest() {
		Role role = roleDao.findOneByName("ROLE_USER");
		assertEquals("ROLE_USER", role.getName());
	}

	@Test
	public void findOneByNameTest_ShouldReturnNull() {
		Role role = roleDao.findOneByName("ROLE_NIKT");
		assertNull(role);
	}
}
