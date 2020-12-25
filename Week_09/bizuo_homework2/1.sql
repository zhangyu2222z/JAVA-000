/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.32-log : Database - db0
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db0` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `db0`;

/*Table structure for table `t_account0` */

DROP TABLE IF EXISTS `t_account0`;

CREATE TABLE `t_account0` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `userId` int(32) NOT NULL,
  `rmb` decimal(12,4) DEFAULT '0.0000',
  `dollar` decimal(12,4) DEFAULT '0.0000',
  `status` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_account0` */

insert  into `t_account0`(`id`,`userId`,`rmb`,`dollar`,`status`) values (1,2,'7.0000','1.0000','0');

/*Table structure for table `t_user0` */

DROP TABLE IF EXISTS `t_user0`;

CREATE TABLE `t_user0` (
  `userId` int(32) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_user0` */

insert  into `t_user0`(`userId`,`name`) values (2,'A');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
