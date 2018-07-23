package com.luojilab.datadev.mapper;

import com.luojilab.datadev.entity.Student;

import java.util.List;

/**
 * 处理学生的数据操作接口
 *
 */
public interface StudentMapper {

    Integer insert(Student s);

    List<Student> findAll();

    List<Student> findByStudentIds(List<Integer> studentIds);

} 