CREATE TABLE `zentao`.`t_user`(  
  `userid` DECIMAL NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `usertype` CHAR(4) NOT NULL COMMENT '用户类别',
  `account` VARCHAR(32) NOT NULL COMMENT '账号',
  `password` CHAR(32) NOT NULL COMMENT '密码',
  `tinyname` VARCHAR(32) COMMENT '昵称',
  `email` VARCHAR(32) NOT NULL COMMENT '邮箱',
  `username` VARCHAR(32) COMMENT '姓名',
  `telnum` VARCHAR(32) COMMENT '电话号',
  `province` VARCHAR(32) COMMENT '省份',
  `city` VARCHAR(32) COMMENT '城市',
  `area` VARCHAR(32) COMMENT '区域',
  `address` TEXT COMMENT '地址',
  `postalcode` VARCHAR(16) COMMENT '邮编',
  `registerdate` TIMESTAMP COMMENT '注册时间',
  `status` CHAR(1) COMMENT '用户状态',
  PRIMARY KEY (`userid`)
) ENGINE=INNODB CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE `zentao`.`t_product`(  
  `productid` INT NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `productcode` VARCHAR(32) NOT NULL COMMENT '商品编号',
  `productbrand` VARCHAR(32) NOT NULL COMMENT '品牌',
  `productimage` VARCHAR(160) COMMENT '商品图片地址',
  `retailprice` DECIMAL NOT NULL COMMENT '单价',
  `productunit` VARCHAR(12) NOT NULL COMMENT '单位',
  `standardprice` DECIMAL NOT NULL COMMENT '标准价',
  `standardunit` VARCHAR(12) NOT NULL COMMENT '标准单位',
  `introduce` TEXT COMMENT '产品介绍',
  `appliedrange` TEXT COMMENT '应用范围',
  `attributes` TEXT NOT NULL COMMENT '商品属性',
  `specification` TEXT NOT NULL COMMENT '规格',
  `tags` TEXT COMMENT '商品标签',
  `repertory` DECIMAL NOT NULL COMMENT '库存',
  `mainclassify` VARCHAR(32) NOT NULL COMMENT '主分类',
  `secondclassify` VARCHAR(32) COMMENT '二级分类',
  `thirdclassify` VARCHAR(32) COMMENT '三级分类',
  PRIMARY KEY (`productid`)
) ENGINE=INNODB CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE `zentao`.`t_order`(  
  `orderid` INT(32) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `productid` INT(32) NOT NULL COMMENT '商品id',
  `ordertime` TIMESTAMP NOT NULL COMMENT '订货时间',
  `productnum` DECIMAL NOT NULL COMMENT '商品数量',
  `diliverycost` DECIMAL NOT NULL COMMENT '运输费用',
  `discount` DECIMAL COMMENT '折扣',
  `realprice` DECIMAL NOT NULL COMMENT '实际价格',
  `status` CHAR(1) NOT NULL COMMENT '订单状态',
  `receipt` TEXT COMMENT '发票信息',
  `userid` CHAR(32) NOT NULL COMMENT '购买用户id',
  `diliveryaddress` TEXT NOT NULL COMMENT '送货地址',
  `diliverytype` CHAR(1) NOT NULL COMMENT '送货方式',
  `paytype` CHAR(1) NOT NULL COMMENT '支付方式',
  PRIMARY KEY (`orderid`)
) ENGINE=INNODB CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
