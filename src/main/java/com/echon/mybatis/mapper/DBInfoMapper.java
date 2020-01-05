package com.echon.mybatis.mapper;

import com.echon.mybatis.domain.DBInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface DBInfoMapper {
    DBInfo getDBInfoById(int primayrId);


    @Select("select id,t.db_name dbName,t.db_ip dbIp,t.db_port dbPort, t.db_user dbUser,t.db_password dbPassword,t.db_flag dbFlag from db_info t")
    List<DBInfo> lists();
}
