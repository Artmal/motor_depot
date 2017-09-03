package com.artmal.utils;

import com.artmal.model.enums.Role;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

public class DatabaseUtils {
    public static void setPoolProperties(DataSource dataSource) {
        PoolProperties p = new PoolProperties();
        p.setUrl("jdbc:mysql://localhost:3306/motor_depot");
        p.setDriverClassName("com.mysql.jdbc.Driver");
        p.setUsername("root");
        p.setPassword("root");
        p.setJmxEnabled(true);
        p.setTestWhileIdle(false);
        p.setTestOnBorrow(true);
        p.setValidationQuery("SELECT 1");
        p.setTestOnReturn(false);
        p.setValidationInterval(30000);
        p.setTimeBetweenEvictionRunsMillis(30000);
        p.setMaxActive(1000);
        p.setInitialSize(10);
        p.setMaxWait(10000);
        p.setRemoveAbandonedTimeout(60);
        p.setMinEvictableIdleTimeMillis(30000);
        p.setMinIdle(10);
        p.setLogAbandoned(true);
        p.setRemoveAbandoned(true);
        p.setJdbcInterceptors(
                "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;" +
                        "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");

        dataSource.setPoolProperties(p);
    }

    public static Role intToRole(int valueFromDB) {
        switch(valueFromDB) {
            case 1:
                return Role.DRIVER;
            case 2:
                return Role.DISPATCHER;
            case 3:
                return Role.ADMIN;
        }

        return null;
    }
}
