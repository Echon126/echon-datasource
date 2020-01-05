package com.echon.mybatis.mapper;



import com.echon.mybatis.domain.StudentInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface StudentInfoMapper {
    List<StudentInfo> getStudentInfo();
}
