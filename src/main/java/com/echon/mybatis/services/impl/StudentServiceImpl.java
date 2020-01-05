package com.echon.mybatis.services.impl;


import com.echon.mybatis.domain.StudentInfo;
import com.echon.mybatis.mapper.StudentInfoMapper;
import com.echon.mybatis.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentInfoMapper studentInfoMapper;

    @Override
    public List<StudentInfo> getStudentInfo() {
        return studentInfoMapper.getStudentInfo();
    }
}
