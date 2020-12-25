package com.zy.service;

import com.zy.entity.AccountEntity;
import com.zy.entity.FreezeEntity;
import com.zy.mapper.AccountMapper;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author zhangy
 * @version 3.0
 * @description:
 * @email: zhangy2222z@sina.cn
 * @create 2020-12-24 19:48
 **/
@Service
public class AccountServiceImpl implements AccountService{

    private final AccountMapper accountMapper;

    @Autowired(required = false)
    public AccountServiceImpl(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    @Override
    @HmilyTCC(confirmMethod = "confirmRmb", cancelMethod = "cancelRmb")
    public Boolean rmb2Dollar(AccountEntity account) {
        account.setRmb(account.getRmb().subtract(BigDecimal.valueOf(7)));
        account.setDollar(account.getDollar().add(BigDecimal.ONE));
        accountMapper.updateAccount(account);
        FreezeEntity temp = new FreezeEntity();
        temp.setDollar(BigDecimal.ONE);
        temp.setRmb(BigDecimal.valueOf(7));
        temp.setAcctid(account.getId());
        accountMapper.tempVal(temp);
        return Boolean.TRUE;
    }

    @Override
    @HmilyTCC(confirmMethod = "confirmDollar", cancelMethod = "cancelDollar")
    public Boolean dollar2Rmb(AccountEntity account) {
        account.setRmb(account.getRmb().add(BigDecimal.valueOf(7)));
        account.setDollar(account.getDollar().subtract(BigDecimal.ONE));
        accountMapper.updateAccount(account);
        FreezeEntity temp = new FreezeEntity();
        temp.setDollar(BigDecimal.ONE);
        temp.setRmb(BigDecimal.valueOf(7));
        temp.setAcctid(account.getId());
        accountMapper.tempVal(temp);
        return Boolean.TRUE;
    }

    public void confirmRmb(AccountEntity account){
        accountMapper.delTemp(account.getId());
    }

    public void cancelRmb(AccountEntity account){
        accountMapper.recoveryRmb2DollarAccount(account);
        accountMapper.delTemp(account.getId());
    }

    public void confirmDollar(AccountEntity account){
        accountMapper.delTemp(account.getId());
    }

    public void cancelDollar(AccountEntity account){
        accountMapper.recoveryDollar2RmbAccount(account);
        accountMapper.delTemp(account.getId());
    }

    @Override
    public AccountEntity getAccountById(BigDecimal acctId) {
        AccountEntity entity = accountMapper.getAccountById(acctId);
        return entity;
    }
}
