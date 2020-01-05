package com.echon.mybatis.dynamic;

import com.alibaba.druid.pool.DruidDataSource;
import com.echon.mybatis.datasource.DynamicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties("spring")
public class DataSourceConfig {
    private Map<String,DruidDataSource> datasource;

    public Map<String, DruidDataSource> getDatasource() {
        return datasource;
    }

    public void setDatasource(Map<String, DruidDataSource> datasource) {
        this.datasource = datasource;
    }

    @Bean
    public DynamicDataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = DynamicDataSource.getInstance();
        Map<Object,Object> map = new HashMap<>();
        map.put("default", this.datasource.get("default"));
        map.put("master", this.datasource.get("master"));
        dynamicDataSource.setTargetDataSources(map);

        dynamicDataSource.setDefaultTargetDataSource(this.datasource.get("default"));

        return dynamicDataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dynamicDataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dynamicDataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath*:mapper/*.xml"));
        return bean.getObject();

    }

    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(
            @Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory)
            throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
