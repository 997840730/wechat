/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.5.40 : Database - wangchat
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`wangchat` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `wangchat`;

/*Table structure for table `blacklist` */

DROP TABLE IF EXISTS `blacklist`;

CREATE TABLE `blacklist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `bid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `u_b_fk` (`uid`),
  KEY `b_u_fk` (`bid`),
  CONSTRAINT `b_u_fk` FOREIGN KEY (`bid`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `u_b_fk` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `blacklist` */

/*Table structure for table `friends` */

DROP TABLE IF EXISTS `friends`;

CREATE TABLE `friends` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `fid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `uid_fid_fk` (`uid`),
  KEY `fid_uid_fk` (`fid`),
  CONSTRAINT `fid_uid_fk` FOREIGN KEY (`fid`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `uid_fid_fk` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `friends` */

insert  into `friends`(`id`,`uid`,`fid`) values (13,7,8),(14,8,7);

/*Table structure for table `groups` */

DROP TABLE IF EXISTS `groups`;

CREATE TABLE `groups` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `groupname` varchar(20) NOT NULL,
  `groupuser` int(11) NOT NULL,
  `introduce` varchar(50) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_group_fk` (`groupuser`),
  CONSTRAINT `user_group_fk` FOREIGN KEY (`groupuser`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `groups` */

/*Table structure for table `imgs` */

DROP TABLE IF EXISTS `imgs`;

CREATE TABLE `imgs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mid` int(11) NOT NULL,
  `img` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `moments_imgs` (`mid`),
  CONSTRAINT `moments_imgs` FOREIGN KEY (`mid`) REFERENCES `moments` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

/*Data for the table `imgs` */

insert  into `imgs`(`id`,`mid`,`img`) values (25,43,'/images/2021/5/24/1621866655757.jfif');

/*Table structure for table `member` */

DROP TABLE IF EXISTS `member`;

CREATE TABLE `member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gid` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_member_fk` (`uid`),
  KEY `group_member_fk` (`gid`),
  CONSTRAINT `group_member_fk` FOREIGN KEY (`gid`) REFERENCES `groups` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_member_fk` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `member` */

/*Table structure for table `message` */

DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `senderId` int(11) NOT NULL,
  `chatId` int(11) NOT NULL,
  `content` varchar(200) NOT NULL,
  `type` varchar(12) NOT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL,
  `readed` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

/*Data for the table `message` */

insert  into `message`(`id`,`senderId`,`chatId`,`content`,`type`,`date`,`time`,`readed`) values (34,1,1,'oidhwqi','public','2021-05-24','11:50:05',2),(35,1,1,'什么鬼啊啊','public','2021-05-24','13:50:59',2),(36,3,1,'家里的喀斯柯达就裂开','public','2021-05-24','13:58:49',2),(37,3,1,'dnlwqkdnwkqln','public','2021-05-24','14:20:04',2),(38,1,1,'我看起来带你玩去离开你','public','2021-05-24','14:20:30',2),(39,8,7,'嗨，你好呀！','private','2021-05-24','22:27:01',1),(40,7,8,'有什么问题么','private','2021-05-24','22:29:31',1);

/*Table structure for table `moments` */

DROP TABLE IF EXISTS `moments`;

CREATE TABLE `moments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `talk` varchar(100) DEFAULT NULL,
  `uuid` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_mements_fk` (`uid`),
  CONSTRAINT `user_mements_fk` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

/*Data for the table `moments` */

insert  into `moments`(`id`,`uid`,`talk`,`uuid`) values (43,7,NULL,'0b5bb9aec9d84aa2b8e53d84ca0fe419');

/*Table structure for table `notice` */

DROP TABLE IF EXISTS `notice`;

CREATE TABLE `notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sendid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `username` varchar(20) DEFAULT NULL,
  `gid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_notice` (`userid`),
  KEY `notice_user` (`sendid`),
  CONSTRAINT `notice_user` FOREIGN KEY (`sendid`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `notice` */

/*Table structure for table `noticed` */

DROP TABLE IF EXISTS `noticed`;

CREATE TABLE `noticed` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sendid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  `contant` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `groupname` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_noticed_fk` (`userid`),
  CONSTRAINT `user_noticed_fk` FOREIGN KEY (`userid`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `noticed` */

/*Table structure for table `readed` */

DROP TABLE IF EXISTS `readed`;

CREATE TABLE `readed` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mid` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  `gid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `u_m_fk` (`uid`),
  KEY `m_u_fk` (`mid`),
  KEY `g_m_fk` (`gid`),
  CONSTRAINT `g_m_fk` FOREIGN KEY (`gid`) REFERENCES `groups` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `m_u_fk` FOREIGN KEY (`mid`) REFERENCES `message` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `u_m_fk` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `readed` */

/*Table structure for table `test` */

DROP TABLE IF EXISTS `test`;

CREATE TABLE `test` (
  `time` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `test` */

insert  into `test`(`time`) values ('04:22:11'),('19:52:16'),('20:16:43'),('20:26:41');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户唯一id',
  `username` varchar(20) NOT NULL COMMENT '用户账号',
  `password` varchar(100) DEFAULT NULL COMMENT '用户密码',
  `name` varchar(20) DEFAULT NULL COMMENT '用户名称',
  `email` varchar(20) DEFAULT NULL COMMENT '用户邮箱',
  `phone` varchar(20) DEFAULT NULL COMMENT '用户手机号码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`name`,`email`,`phone`) values (7,'222','MQ3Lv0zOYvdioqqhSNVWvQ==','一号','123@3321.com','333'),(8,'111','MQ3Lv0zOYvdioqqhSNVWvQ==','二号','2645422866@qq.com','333');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
