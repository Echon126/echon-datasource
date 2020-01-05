package com.echon.mybatis.services.impl;


import com.echon.mybatis.domain.UserInfo;
import com.echon.mybatis.mapper.UserInfoMapper;
import com.echon.mybatis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public List<UserInfo> getUserInfo() {
        return userInfoMapper.getUserInfo();
    }
}
