package com.zy.homework2;/**
 * @ClassName Demo2
 * @Description TODO
 * @Author zhangy
 * @Create 2020/12/228:49
 * @Version 1.0
 **/

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.shardingsphere.api.config.masterslave.MasterSlaveRuleConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.MasterSlaveDataSourceFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author zhangy
 * @version 3.0
 * @description:
 * @email: zhangy2222z@sina.cn
 * @create 2020-12-22 8:49
 **/
@RestController
public class Demo2 {
    @RequestMapping("homework2")
    public void insert() {
        Map<String, DataSource> dataSourceMap = new HashMap<>();

        // 配置主库
        BasicDataSource masterDataSource = new BasicDataSource();
        masterDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        masterDataSource.setUrl("jdbc:mysql://localhost:3306/db0");
        masterDataSource.setUsername("root");
        masterDataSource.setPassword("root");
        dataSourceMap.put("ds_master", masterDataSource);

        // 配置第一个从库
        BasicDataSource slaveDataSource1 = new BasicDataSource();
        slaveDataSource1.setDriverClassName("com.mysql.jdbc.Driver");
        slaveDataSource1.setUrl("jdbc:mysql://localhost:3306/db1");
        slaveDataSource1.setUsername("root");
        slaveDataSource1.setPassword("root");
        dataSourceMap.put("ds_slave0", slaveDataSource1);

        // 配置第二个从库
        BasicDataSource slaveDataSource2 = new BasicDataSource();
        slaveDataSource2.setDriverClassName("com.mysql.jdbc.Driver");
        slaveDataSource2.setUrl("jdbc:mysql://localhost:3306/db2");
        slaveDataSource2.setUsername("root");
        slaveDataSource2.setPassword("root");
        dataSourceMap.put("ds_slave1", slaveDataSource2);

        // 配置读写分离规则
        MasterSlaveRuleConfiguration masterSlaveRuleConfig = new MasterSlaveRuleConfiguration("ds_master_slave", "ds_master", Arrays.asList("ds_slave0", "ds_slave1"));

        // 获取数据源对象
        try {
            DataSource dataSource = MasterSlaveDataSourceFactory.createDataSource(dataSourceMap, masterSlaveRuleConfig,  new Properties());
            String sql = "SELECT t.* FROM test t ";
            try (
                    Connection conn = dataSource.getConnection();
                    PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                try (ResultSet rs = preparedStatement.executeQuery()) {
                    while(rs.next()) {
                        System.out.println(rs.getInt(1));
                        System.out.println(rs.getString(2));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
