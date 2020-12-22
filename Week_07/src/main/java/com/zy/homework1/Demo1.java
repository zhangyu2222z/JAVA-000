package com.zy.homework1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author zhangy
 * @version 3.0
 * @description:
 * @email: zhangy2222z@sina.cn
 * @create 2020-12-22 8:48
 **/
@RestController
public class Demo1 {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    //	24秒
    @RequestMapping("insert")
    public String insert() {
        long begin = System.currentTimeMillis();
        for (int i =0;i <100; i++) {
            String sql = "insert into t_order (productid, ordertime, productnum, diliverycost, discount, realprice, " +
                    "status, receipt, userid, diliveryaddress, diliverytype, paytype) values";
            StringBuffer valstr = new StringBuffer();
            for (int j=0;j<10000;j++) {
                valstr.append("('2222','2020-12-02 11:39:28','2','4','5','4','1',NULL,'1','','1','1'),");
            }
            sql = sql + valstr.substring(0, valstr.length() - 1);
            jdbcTemplate.execute(sql);
        }
        long end = System.currentTimeMillis();
        return "插入完毕," + ((end-begin)/1000) + "s";
    }

    //	26秒
    @RequestMapping("insert2")
    public String insert2() {
        long begin = System.currentTimeMillis();
        for (int i =0;i <100; i++) {
            String sql = "insert into t_order (productid,  productnum, diliverycost, discount, realprice, status, receipt, userid, diliveryaddress, diliverytype, paytype) " +
                    "values (?,  ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    long time = new java.util.Date().getTime();
                    ps.setInt(1, 2222);
                    ps.setBigDecimal(2,  new BigDecimal(2));
                    ps.setBigDecimal(3,  new BigDecimal(4));
                    ps.setBigDecimal(4,  new BigDecimal(5));
                    ps.setBigDecimal(5,  new BigDecimal(4));
                    ps.setString(6, "1");
                    ps.setString(7, "1");
                    ps.setString(8, "1");
                    ps.setString(9, "abc");
                    ps.setString(10, "1");
                    ps.setString(11, "1");
                }
                @Override
                public int getBatchSize() {
                    return 10000;
                }
            });
        }
        long end = System.currentTimeMillis();
        return "插入完毕," + ((end-begin)/1000) + "s";
    }

    //	25-37秒
    @RequestMapping("insert3")
    public String insert3() {
        long begin = System.currentTimeMillis();
        String prefix = "insert into t_order (productid, ordertime, productnum, diliverycost, discount, realprice, " +
                "status, receipt, userid, diliveryaddress, diliverytype, paytype) values";
        try {
            Connection conn = dataSource.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement pst = conn.prepareStatement(";");
            StringBuffer suffix = new StringBuffer();
            for (int i = 1; i <= 100; i++) {
                for (int j = 1; j <= 10000; j++) {
                    suffix.append("('2222','2020-12-02 11:39:28','2','4','5','4','1',NULL,'1','','1','1'),");
                }
                String sql = prefix + suffix.substring(0, suffix.length() - 1);
                pst.addBatch(sql);
                pst.executeBatch();
                conn.commit();
                suffix = new StringBuffer();
            }
            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        return "插入完毕," + ((end-begin)/1000) + "s";
    }
}
