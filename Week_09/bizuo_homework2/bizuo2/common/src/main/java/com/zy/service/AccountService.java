package com.zy.service;

import com.zy.entity.AccountEntity;
import org.dromara.hmily.annotation.Hmily;

import java.math.BigDecimal;

/**
 * @author zhangy
 * @version 3.0
 * @description:
 * @email: zhangy2222z@sina.cn
 * @create 2020-12-24 19:36
 **/
public interface AccountService {

    @Hmily
    Boolean rmb2Dollar(AccountEntity account);
    @Hmily
    Boolean dollar2Rmb(AccountEntity account);

    AccountEntity getAccountById(BigDecimal acctId);

}
