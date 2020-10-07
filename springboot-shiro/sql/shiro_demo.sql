/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 8.0.19 : Database - shiro_demo1
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`shiro_demo1` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `shiro_demo1`;

/*Table structure for table `resource` */

DROP TABLE IF EXISTS `resource`;

CREATE TABLE `resource` (
  `resource_id` bigint NOT NULL COMMENT '主键',
  `resource_name` varchar(50) NOT NULL COMMENT '资源名称',
  `resource_uri` varchar(100) NOT NULL COMMENT '资源uri',
  `resource_farther_id` bigint DEFAULT NULL COMMENT '父资源id',
  `resource_ico_url` varchar(100) DEFAULT NULL COMMENT '资源图标路径',
  `resource_desc` varchar(100) NOT NULL COMMENT '描述',
  `resource_permission_tag` varchar(100) NOT NULL COMMENT '权限标识符',
  PRIMARY KEY (`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='权限菜单表（资源表）';

/*Data for the table `resource` */

insert  into `resource`(`resource_id`,`resource_name`,`resource_uri`,`resource_farther_id`,`resource_ico_url`,`resource_desc`,`resource_permission_tag`) values (1,'查询产品权限','/product/get/list',NULL,NULL,'查询产品记录','product:get');

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `role_id` bigint NOT NULL,
  `role_name` varchar(50) NOT NULL,
  `role_desc` varchar(100) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色表';

/*Data for the table `role` */

insert  into `role`(`role_id`,`role_name`,`role_desc`) values (1,'admin','管理员'),(2,'user','用户');

/*Table structure for table `role_resource` */

DROP TABLE IF EXISTS `role_resource`;

CREATE TABLE `role_resource` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `resource_id` bigint DEFAULT NULL COMMENT '主键',
  `role_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_5` (`role_id`),
  KEY `resource_id` (`resource_id`),
  CONSTRAINT `FK_Reference_5` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `role_resource_ibfk_1` FOREIGN KEY (`resource_id`) REFERENCES `resource` (`resource_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色_资源表';

/*Data for the table `role_resource` */

insert  into `role_resource`(`id`,`resource_id`,`role_id`) values (1,1,1);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` bigint NOT NULL,
  `username` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `salt` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`user_id`,`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

/*Data for the table `user` */

insert  into `user`(`user_id`,`username`,`password`,`salt`) values (1,'xiangbei','ff595c47b51b4cf70fddce090f68879e','ee575f62-0dda-44f2-b75e-4efef795018f');

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint DEFAULT NULL COMMENT '角色id',
  `user_id` bigint DEFAULT NULL COMMENT '用户id',
  `username` varchar(32) DEFAULT NULL COMMENT '账户',
  PRIMARY KEY (`id`),
  UNIQUE KEY `FK_Reference_3` (`user_id`,`username`),
  KEY `FK_Reference_2` (`role_id`),
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`user_id`, `username`) REFERENCES `user` (`user_id`, `username`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户_角色表';

/*Data for the table `user_role` */

insert  into `user_role`(`id`,`role_id`,`user_id`,`username`) values (1,1,1,'xiangbei');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
