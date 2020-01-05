package com.echon.mybatis.services.impl;

import com.echon.mybatis.domain.DBInfo;
import com.echon.mybatis.mapper.DBInfoMapper;
import com.echon.mybatis.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DBServiceImpl implements DBService {
    @Autowired
    DBInfoMapper dbInfoMapper;

    @Override
    public DBInfo getDBInfoByprimayrId(int primayrId) {
          return dbInfoMapper.getDBInfoById(primayrId);
    }

    @Override
    public List<DBInfo> lists() {
        return dbInfoMapper.lists();
    }
}
