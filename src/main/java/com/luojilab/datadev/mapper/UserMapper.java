package com.luojilab.datadev.mapper;

import com.luojilab.datadev.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 处理用户的数据操作接口
 */
public interface UserMapper {

    Integer insert(User u);

    List<User> findAll();

    List<User> findByUserIds(List<Integer> userIds);

    public List<User> findBetweenUserIds(@Param("userId1") int userId1, @Param("userId2") int userId2);

} 