/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.1.62-community : Database - bootstrap
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

/*Table structure for table `tb_logs` */

DROP TABLE IF EXISTS `tb_logs`;

CREATE TABLE `tb_logs` (
  `id` bigint(20) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `ip_address` varchar(16) DEFAULT NULL,
  `log_level` varchar(16) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_10` (`user`),
  CONSTRAINT `FK_Reference_10` FOREIGN KEY (`user`) REFERENCES `tb_users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_logs` */

/*Table structure for table `tb_modules` */

DROP TABLE IF EXISTS `tb_modules`;

CREATE TABLE `tb_modules` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(32) NOT NULL,
  `priority` int(11) NOT NULL,
  `sn` varchar(32) NOT NULL,
  `url` varchar(255) NOT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6510844BB3395F9` (`parent_id`),
  CONSTRAINT `FK6510844BB3395F9` FOREIGN KEY (`parent_id`) REFERENCES `tb_modules` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_modules` */

/*Table structure for table `tb_org_role` */

DROP TABLE IF EXISTS `tb_org_role`;

CREATE TABLE `tb_org_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `priority` int(11) NOT NULL,
  `organization_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK557CA4C3D069FDD7` (`organization_id`),
  KEY `FK557CA4C3C592DFF7` (`role_id`),
  CONSTRAINT `FK557CA4C3D069FDD7` FOREIGN KEY (`organization_id`) REFERENCES `tb_orgnizations` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_org_role` */

/*Table structure for table `tb_orgnizations` */

DROP TABLE IF EXISTS `tb_orgnizations`;

CREATE TABLE `tb_orgnizations` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL,
  `name` varchar(64) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1DBDA7D2FCC01B00` (`parent_id`),
  CONSTRAINT `FK1DBDA7D2FCC01B00` FOREIGN KEY (`parent_id`) REFERENCES `tb_orgnizations` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_orgnizations` */

/*Table structure for table `tb_permissions` */

DROP TABLE IF EXISTS `tb_permissions`;

CREATE TABLE `tb_permissions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(32) NOT NULL,
  `short_name` varchar(16) NOT NULL,
  `module_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKBA7A9C2E334A08F7` (`module_id`),
  CONSTRAINT `FKBA7A9C2E334A08F7` FOREIGN KEY (`module_id`) REFERENCES `tb_modules` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_permissions` */

/*Table structure for table `tb_role_permission` */

DROP TABLE IF EXISTS `tb_role_permission`;

CREATE TABLE `tb_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `permission_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK679E223926E70397` (`permission_id`),
  KEY `FK679E2239C592DFF7` (`role_id`),
  CONSTRAINT `FK679E223926E70397` FOREIGN KEY (`permission_id`) REFERENCES `tb_permissions` (`id`),
  CONSTRAINT `FK679E2239C592DFF7` FOREIGN KEY (`role_id`) REFERENCES `tb_roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_role_permission` */

/*Table structure for table `tb_roles` */

DROP TABLE IF EXISTS `tb_roles`;

CREATE TABLE `tb_roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_roles` */

/*Table structure for table `tb_user_role` */

DROP TABLE IF EXISTS `tb_user_role`;

CREATE TABLE `tb_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `priority` int(11) NOT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6DD3562BC592DFF7` (`role_id`),
  KEY `FK6DD3562B6ABDA3D7` (`user_id`),
  CONSTRAINT `FK6DD3562B6ABDA3D7` FOREIGN KEY (`user_id`) REFERENCES `tb_users` (`id`),
  CONSTRAINT `FK6DD3562BC592DFF7` FOREIGN KEY (`role_id`) REFERENCES `tb_roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_user_role` */

/*Table structure for table `tb_users` */

DROP TABLE IF EXISTS `tb_users`;

CREATE TABLE `tb_users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `email` varchar(128) DEFAULT NULL,
  `password` varchar(64) NOT NULL,
  `phone` varchar(32) DEFAULT NULL,
  `realname` varchar(32) NOT NULL,
  `salt` varchar(32) NOT NULL,
  `picture` varchar(100) DEFAULT NULL,
  `username` varchar(32) NOT NULL,
  `org_id` bigint(20) DEFAULT NULL,
  `status` varchar(16) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKD607B56A453A1286` (`org_id`),
  CONSTRAINT `FKD607B56A453A1286` FOREIGN KEY (`org_id`) REFERENCES `tb_orgnizations` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_users` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
