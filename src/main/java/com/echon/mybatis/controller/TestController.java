package com.echon.mybatis.controller;

import com.alibaba.druid.pool.DruidDataSource;
import com.echon.mybatis.datasource.DataSourceContextHolder;
import com.echon.mybatis.datasource.DynamicDataSource;
import com.echon.mybatis.domain.DBInfo;
import com.echon.mybatis.mapper.StudentInfoMapper;
import com.echon.mybatis.mapper.TeacherMapper;
import com.echon.mybatis.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class TestController {
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    StudentInfoMapper studentInfoMapper;


    @Autowired
    DBService dbService;

    @RequestMapping("/test")
    public void test() {

//        DataSourceContextHolder.setDBType("dynamic-slave-001");
//        studentInfoMapper.getStudentInfo().forEach(x->{
//            System.out.println(x.toString());
//        });

        DataSourceContextHolder.setDBType("dynamic-slave-002");
        System.out.println(teacherMapper.listTeacherInfo());
    }

    @RequestMapping("/init/datasource")
    public void initDataSources(){
        DataSourceContextHolder.setDBType("master");
        List<DBInfo> dbInfos = dbService.lists();
        for (DBInfo dbInfo : dbInfos) {
            System.out.println("dbName is -> " + dbInfo.getDbName() + "; dbIP is  -> " + dbInfo.getDbIp() + "; dbUser is  -> " + dbInfo.getDbUser() + "; dbPasswd is -> " + dbInfo.getDbPassword());
            DruidDataSource dynamicDataSource = new DruidDataSource();
            dynamicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dynamicDataSource.setUrl("jdbc:mysql://" + dbInfo.getDbIp() + ":" + dbInfo.getDbPort() + "/" + dbInfo.getDbName() + "?autoReconnect=true&useUnicode=true&characterEncoding=utf8&useSSL=false");
            dynamicDataSource.setUsername(dbInfo.getDbUser());
            dynamicDataSource.setPassword(dbInfo.getDbPassword());
            /**
             * 创建动态数据源
             */
            Map<Object, Object> dataSourceMap = DynamicDataSource.getInstance().getDataSourceMap();
            dataSourceMap.put(dbInfo.getDbFlag(), dynamicDataSource);
            DynamicDataSource.getInstance().setTargetDataSources(dataSourceMap);
        }
    }
}
