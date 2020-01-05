package com.echon.mybatis.mapper;



import com.echon.mybatis.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface UserInfoMapper {
    List<UserInfo> getUserInfo();
}
