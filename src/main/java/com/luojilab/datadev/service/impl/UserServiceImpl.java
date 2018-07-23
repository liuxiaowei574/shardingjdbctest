package com.luojilab.datadev.service.impl;

import com.luojilab.datadev.entity.Student;
import com.luojilab.datadev.entity.User;
import com.luojilab.datadev.mapper.StudentMapper;
import com.luojilab.datadev.mapper.UserMapper;
import com.luojilab.datadev.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    public UserMapper userMapper;

    @Resource
    public StudentMapper studentMapper;

    public boolean insert(User u) {
        return userMapper.insert(u) > 0 ? true : false;
    }

    public List<User> findAll() {
        return userMapper.findAll();
    }

    public List<User> findByUserIds(List<Integer> ids) {
        return userMapper.findByUserIds(ids);
    }

    @Override
    public List<User> findBetweenUserIds(int userId1, int userId2) {
        return userMapper.findBetweenUserIds(userId1, userId2);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void transactionTestSucess() {
        User u = new User();
        u.setUserId(13);
        u.setAge(25);
        u.setName("war3 1.27");
        userMapper.insert(u);

        Student student = new Student();
        student.setStudentId(21);
        student.setAge(21);
        student.setName("hehe");
        studentMapper.insert(student);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void transactionTestFailure() throws IllegalAccessException {
        User u = new User();
        u.setUserId(13);
        u.setAge(25);
        u.setName("war3 1.27 good2222");
        userMapper.insert(u);

        Student student = new Student();
        student.setStudentId(21);
        student.setAge(21);
        student.setName("hehe2222");
        studentMapper.insert(student);

        throw new IllegalAccessException();
    }

}