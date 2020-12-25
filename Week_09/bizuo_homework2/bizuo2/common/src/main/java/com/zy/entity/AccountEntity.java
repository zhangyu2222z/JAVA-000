package com.zy.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author zhangy
 * @version 3.0
 * @description:
 * @email: zhangy2222z@sina.cn
 * @create 2020-12-24 18:59
 **/
@Data
public class AccountEntity implements Serializable {

    private static final long serialVersionUID = -8551347266419380333L;

    private BigDecimal id;
    private BigDecimal userId;
    private BigDecimal rmb;
    private BigDecimal dollar;
    private String status;
}
