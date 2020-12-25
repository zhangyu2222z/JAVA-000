package com.zy.mapper;

import com.zy.entity.AccountEntity;
import com.zy.entity.FreezeEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;

/**
 * @author zhangy
 * @version 3.0
 * @description:
 * @email: zhangy2222z@sina.cn
 * @create 2020-12-25 10:46
 **/

public interface AccountMapper {

    @Update("update account_test set rmb = ${rmb}, dollar = ${dollar} where id = ${id}")
    int updateAccount(AccountEntity account);

    @Insert("insert into freeze_test (acctid, rmb, dollar) values (#{acctid}, #{rmb}, #{dollar})")
    int tempVal(FreezeEntity account);

    @Delete("delete from freeze_test where acctid = #{acctId}")
    void delTemp(BigDecimal acctId);

    @Update("update account_test set rmb = rmb + ${rmb}, dollar = dollar - ${dollar} where id = ${id}")
    int recoveryRmb2DollarAccount(AccountEntity account);


    @Update("update account_test set rmb = rmb - ${rmb}, dollar = dollar + ${dollar} where id = ${id}")
    int recoveryDollar2RmbAccount(AccountEntity account);

    @Select("select * from account_test where id = #{acctId}")
    AccountEntity getAccountById(BigDecimal acctId);

}
