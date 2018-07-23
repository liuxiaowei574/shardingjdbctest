package com.luojilab.datadev.service;

import com.luojilab.datadev.entity.User;

import java.util.List;

/**
 * 处理用户的Service
 */
public interface UserService {

    public boolean insert(User u);

    public List<User> findAll();

    public List<User> findByUserIds(List<Integer> ids);

    public List<User> findBetweenUserIds(int userId1, int userId2);

    public void transactionTestSucess();

    public void transactionTestFailure() throws IllegalAccessException;

}