package ru.kpfu.itis.pita.repository;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import ru.kpfu.itis.pita.entity.User;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 3/17/17 4:05 PM
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:spring-db-config-test.xml"
})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup(value = "/datasets/users.xml")
@Ignore
public class UserRepositoryTest {

    @Autowired
    private UserRepository ur;

    @Test
    public void testSelectById() {
        Assert.assertEquals(ur.findOne(1).getEmail(), "admin@adm.in");
    }

    @Test
    public void testSelectByEmail() {
        Assert.assertEquals(ur.findByEmail("admin@adm.in").getId(), 1);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void testInsertMandatoryFields() {
        User user = new User();
        ur.save(user);
    }
}
