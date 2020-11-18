package com.zy;

import com.zy.test.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class confZy {

    //    6、
//    @Autowired
    @Resource
    private Order order;


    @Bean(name = "order1")
    public Order getOrder(){
        Order order = new Order();
        return order;
    }

}
