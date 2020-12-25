package com.zy.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author zhangy
 * @version 3.0
 * @description:
 * @email: zhangy2222z@sina.cn
 * @create 2020-12-25 13:53
 **/
@Data
public class FreezeEntity implements Serializable {
    private static final long serialVersionUID = -8551347266419380333L;
    private BigDecimal acctid;
    private BigDecimal rmb;
    private BigDecimal dollar;


}
