package pl.mbm.dao;

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
import org.springframework.transaction.annotation.Transactional;

import pl.mbm.dao.configuration.PersistenceContextTest;
import pl.mbm.dao.util.DaoTestUtils;
import pl.mbm.model.entity.ResetPassword;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceContextTest.class })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@DatabaseSetup("ResetPasswordData.xml")
public class ResetPasswordDaoTest {

	@Autowired
	private ResetPasswordDao resetPasswordDao;

	@Test
	@Transactional
	public void save_ShouldAddResetPassword() {
		ResetPassword resetPassword = resetPasswordDao.save(DaoTestUtils
				.getResetPassword());
		assertNotNull("should return entity", resetPassword);
	}
	
	@Test
	public void findByEmailAndUuidTest(){
		ResetPassword resetPassword = resetPasswordDao.findByEmailAndUuid("somemail@mbm.pl", "someuuid");
		assertNotNull("should return entity", resetPassword);
	}

}
