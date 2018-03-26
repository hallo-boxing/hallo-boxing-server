SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(20) NOT NULL,
  `login_pwd` varchar(50) NOT NULL,
  `user_sex` tinyint(4) DEFAULT '0',
  `user_name` varchar(20) DEFAULT NULL,
  `true_name` varchar(100) DEFAULT NULL,
  `brithday` date DEFAULT NULL,
  `user_photo` varchar(150) DEFAULT '',
  `user_qq` varchar(20) DEFAULT NULL,
  `user_phone` char(11) DEFAULT '',
  `user_email` varchar(50) DEFAULT '',
  `last_ip` varchar(16) DEFAULT NULL,
  `last_time` datetime DEFAULT NULL,
  `data_flag` tinyint(4) NOT NULL DEFAULT '1',
  `create_time` datetime NOT NULL,
  `pay_pwd` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `user_status` (`data_flag`),
  KEY `login_name` (`login_name`),
  KEY `user_phone` (`user_phone`),
  KEY `user_email` (`user_email`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;