package com.echon.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;


@Mapper
public interface TeacherMapper {
    @Select("select * from teacher_info")
    Map<String,Object> listTeacherInfo();
}
