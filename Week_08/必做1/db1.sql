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

/*Table structure for table `t1` */

DROP TABLE IF EXISTS `t1`;

CREATE TABLE `t1` (
  `id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t1` */

/*Table structure for table `tb0` */

DROP TABLE IF EXISTS `tb0`;

CREATE TABLE `tb0` (
  `id` int(11) DEFAULT NULL,
  `str` char(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `tb0` */

insert  into `tb0`(`id`,`str`) values (0,'tb0');

/*Table structure for table `tb1` */

DROP TABLE IF EXISTS `tb1`;

CREATE TABLE `tb1` (
  `id` int(11) DEFAULT NULL,
  `str` char(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb1` */

insert  into `tb1`(`id`,`str`) values (1,'tb1'),(17,'nnnn');

/*Table structure for table `tb10` */

DROP TABLE IF EXISTS `tb10`;

CREATE TABLE `tb10` (
  `id` int(11) DEFAULT NULL,
  `str` char(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb10` */

insert  into `tb10`(`id`,`str`) values (10,'tb10');

/*Table structure for table `tb11` */

DROP TABLE IF EXISTS `tb11`;

CREATE TABLE `tb11` (
  `id` int(11) DEFAULT NULL,
  `str` char(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb11` */

insert  into `tb11`(`id`,`str`) values (11,'tb11');

/*Table structure for table `tb12` */

DROP TABLE IF EXISTS `tb12`;

CREATE TABLE `tb12` (
  `id` int(11) DEFAULT NULL,
  `str` char(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb12` */

insert  into `tb12`(`id`,`str`) values (12,'tb12');

/*Table structure for table `tb13` */

DROP TABLE IF EXISTS `tb13`;

CREATE TABLE `tb13` (
  `id` int(11) DEFAULT NULL,
  `str` char(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb13` */

insert  into `tb13`(`id`,`str`) values (13,'tb13');

/*Table structure for table `tb14` */

DROP TABLE IF EXISTS `tb14`;

CREATE TABLE `tb14` (
  `id` int(11) DEFAULT NULL,
  `str` char(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb14` */

insert  into `tb14`(`id`,`str`) values (14,'tb14');

/*Table structure for table `tb15` */

DROP TABLE IF EXISTS `tb15`;

CREATE TABLE `tb15` (
  `id` int(11) DEFAULT NULL,
  `str` char(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb15` */

insert  into `tb15`(`id`,`str`) values (15,'tb15');

/*Table structure for table `tb2` */

DROP TABLE IF EXISTS `tb2`;

CREATE TABLE `tb2` (
  `id` int(11) DEFAULT NULL,
  `str` char(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb2` */

insert  into `tb2`(`id`,`str`) values (2,'tb2');

/*Table structure for table `tb3` */

DROP TABLE IF EXISTS `tb3`;

CREATE TABLE `tb3` (
  `id` int(11) DEFAULT NULL,
  `str` char(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb3` */

insert  into `tb3`(`id`,`str`) values (3,'tb3');

/*Table structure for table `tb4` */

DROP TABLE IF EXISTS `tb4`;

CREATE TABLE `tb4` (
  `id` int(11) DEFAULT NULL,
  `str` char(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb4` */

insert  into `tb4`(`id`,`str`) values (4,'tb4');

/*Table structure for table `tb5` */

DROP TABLE IF EXISTS `tb5`;

CREATE TABLE `tb5` (
  `id` int(11) DEFAULT NULL,
  `str` char(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb5` */

insert  into `tb5`(`id`,`str`) values (5,'tb5');

/*Table structure for table `tb6` */

DROP TABLE IF EXISTS `tb6`;

CREATE TABLE `tb6` (
  `id` int(11) DEFAULT NULL,
  `str` char(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb6` */

insert  into `tb6`(`id`,`str`) values (6,'tb6');

/*Table structure for table `tb7` */

DROP TABLE IF EXISTS `tb7`;

CREATE TABLE `tb7` (
  `id` int(11) DEFAULT NULL,
  `str` char(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb7` */

insert  into `tb7`(`id`,`str`) values (7,'tb7');

/*Table structure for table `tb8` */

DROP TABLE IF EXISTS `tb8`;

CREATE TABLE `tb8` (
  `id` int(11) DEFAULT NULL,
  `str` char(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb8` */

insert  into `tb8`(`id`,`str`) values (8,'tb8');

/*Table structure for table `tb9` */

DROP TABLE IF EXISTS `tb9`;

CREATE TABLE `tb9` (
  `id` int(11) DEFAULT NULL,
  `str` char(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb9` */

insert  into `tb9`(`id`,`str`) values (9,'tb9');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
