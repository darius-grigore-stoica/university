package com.company.repository;


import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Component
public class JdbcUtils {
    private final Properties jdbcProps;

    public JdbcUtils(Properties props){
        this.jdbcProps=props;
    }

    private Connection instance = null;

    private Connection getNewConnection(){
        String url=jdbcProps.getProperty("spring.datasource.url");
        String user=jdbcProps.getProperty("spring.datasource.username");
        String pass=jdbcProps.getProperty("spring.datasource.password");
        Connection con=null;
        try {

            if (user!=null && pass!=null)
                con= DriverManager.getConnection(url,user,pass);
            else
                con=DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("Error getting connection "+e);
        }
        return con;
    }

    public Connection getConnection(){
        try {
            if (instance==null || instance.isClosed())
                instance=getNewConnection();

        } catch (SQLException e) {
            System.out.println("Error DB "+e);
        }
        return instance;
    }
}