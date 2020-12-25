package com.zy.controller;

import com.zy.entity.AccountEntity;
import com.zy.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author zhangy
 * @version 3.0
 * @description:
 * @email: zhangy2222z@sina.cn
 * @create 2020-12-25 10:06
 **/
@RestController
@RequestMapping("/trade")
public class TradeController {

    @Autowired
    private AccountService service;

    @PostMapping(value = "/orderPay")
    public String orderPay(@RequestParam(value = "type") String type,
                           @RequestParam(value = "acctId") BigDecimal acctId) {
        AccountEntity acct = service.getAccountById(acctId);
        if ("1".equals(type)) {
            service.rmb2Dollar(acct);
        } else {
            service.dollar2Rmb(acct);
        }
        return "success";
    }
}
