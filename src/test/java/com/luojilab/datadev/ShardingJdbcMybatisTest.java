package com.luojilab.datadev;

import com.luojilab.datadev.entity.Student;
import com.luojilab.datadev.entity.User;
import com.luojilab.datadev.service.StudentService;
import com.luojilab.datadev.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 测试分库分表规则
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/spring-database.xml", "classpath*:spring/spring-sharding.xml"})
public class ShardingJdbcMybatisTest {

    @Resource
    public UserService userService;

    @Resource
    public StudentService studentService;

    @Test
    public void testUserInsert() {
        User u = new User();
        u.setUserId(12);
        u.setAge(25);
        u.setName("github");
        Assert.assertEquals(userService.insert(u), true);
    }

    @Test
    public void testStudentInsert() {
        Student student = new Student();
        student.setStudentId(22);
        student.setAge(21);
        student.setName("hehe");
        Assert.assertEquals(studentService.insert(student), true);
    }

    @Test
    public void testFindAll() {
        List<User> users = userService.findAll();
        if (null != users && !users.isEmpty()) {
            for (User u : users) {
                System.out.println(u);
            }
        }
    }

    @Test
    public void testSQLIN() {
        List<User> users = userService.findByUserIds(Arrays.asList(11, 12));
        if (null != users && !users.isEmpty()) {
            for (User u : users) {
                System.out.println(u);
            }
        }
    }

    @Test
    public void testSQLBetween() {
        List<User> users = userService.findBetweenUserIds(11, 12);
        if (null != users && !users.isEmpty()) {
            for (User u : users) {
                System.out.println(u);
            }
        }
    }

    @Test
    public void testTransactionTestSucess() {
        userService.transactionTestSucess();
    }

    @Test(expected = IllegalAccessException.class)
    public void testTransactionTestFailure() throws IllegalAccessException {
        userService.transactionTestFailure();
    }
}