/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.32-log : Database - db1
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db1` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `db1`;

/*Table structure for table `t_account1` */

DROP TABLE IF EXISTS `t_account1`;

CREATE TABLE `t_account1` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `userId` int(32) NOT NULL,
  `rmb` decimal(12,4) DEFAULT '0.0000',
  `dollar` decimal(12,4) DEFAULT '0.0000',
  `status` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_account1` */

insert  into `t_account1`(`id`,`userId`,`rmb`,`dollar`,`status`) values (1,3,'7.0000','1.0000','0');

/*Table structure for table `t_user1` */

DROP TABLE IF EXISTS `t_user1`;

CREATE TABLE `t_user1` (
  `userId` int(32) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `t_user1` */

insert  into `t_user1`(`userId`,`name`) values (3,'B');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
