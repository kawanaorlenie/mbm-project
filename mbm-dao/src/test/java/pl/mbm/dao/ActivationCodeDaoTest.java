package pl.mbm.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import pl.mbm.dao.configuration.PersistenceContextTest;
import pl.mbm.dao.util.TestUtils;
import pl.mbm.model.entity.ActivationCode;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceContextTest.class })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@DatabaseSetup("UserData.xml")
public class ActivationCodeDaoTest {

	@Autowired
	private ActivationCodeDao activationCodeDao;

	@Test
	public void autowireTest() {
		assertNotNull(activationCodeDao);
	}

	@Test
	public void save_ShouldAddEntityToDatabase() {
		ActivationCode activationCode = activationCodeDao.save(TestUtils
				.getActivationCode());
		assertNotNull(activationCode);
		assertEquals(TestUtils.USER_NAME, activationCode.getUser().getName());
	}

}
