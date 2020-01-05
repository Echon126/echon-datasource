package com.echon.mybatis.services;



import com.echon.mybatis.domain.DBInfo;

import java.util.List;


public interface DBService {
    DBInfo getDBInfoByprimayrId(int primayrId);

    List<DBInfo> lists();
}
