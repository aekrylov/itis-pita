package ru.kpfu.itis.pita.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 3/17/17 4:05 PM
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:spring-config.xml"
})
public class UserRepositoryTest {

    @Autowired
    private UserRepository ur;

    @Test
    public void testSelectById() {
        //TODO mock up DB properly
        Assert.assertEquals(ur.findOne(1).getEmail(), "admin@adm.in");
    }

    @Test
    public void TestSelectByEmail() {
        Assert.assertEquals(ur.findByEmail("admin@adm.in").getId(), 1);
    }
}
