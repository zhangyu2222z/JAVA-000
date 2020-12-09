package com.zy.demo;

import com.zy.service.XAOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author zhangy
 * @version 3.0
 * @description:
 * @email: zhangy2222z@sina.cn
 * @create 2020-12-09 17:18
 **/
@Controller
public class DemoController {


    //		orderService.init();
//		orderService.selectAll();
//		orderService.cleanup();

    @Autowired
    private XAOrderService orderService;

    @GetMapping("init")
    public void init(){
        orderService.init();
    }

    @GetMapping("selectAll")
    public void selectAll(){
        orderService.selectAll();
    }

    @GetMapping("cleanup")
    public void cleanup(){
        orderService.cleanup();
    }

}
