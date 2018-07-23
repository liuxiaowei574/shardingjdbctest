package com.luojilab.datadev.service.impl;

import com.luojilab.datadev.entity.Student;
import com.luojilab.datadev.mapper.StudentMapper;
import com.luojilab.datadev.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    public StudentMapper studentMapper;

    public boolean insert(Student student) {
        return studentMapper.insert(student) > 0 ? true : false;
    }

}  