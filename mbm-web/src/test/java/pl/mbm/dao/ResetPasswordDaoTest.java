package pl.mbm.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.mbm.dao.util.DaoTestUtils;
import pl.mbm.entity.ResetPassword;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ResetPasswordDaoTest {


    @Autowired
    private TestEntityManager entityManager;

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
    public void findByEmailAndUuidTest() {
        entityManager.persist(new ResetPassword("somemail@mbm.pl", "someuuid"));

        ResetPassword resetPassword = resetPasswordDao.findByEmailAndUuid("somemail@mbm.pl", "someuuid");
        assertNotNull("should return entity", resetPassword);
    }

}
