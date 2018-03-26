SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `good_appraisal`;
CREATE TABLE `good_appraisal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cabinet_id` int(11) NOT NULL DEFAULT '0',
  `order_id` int(11) NOT NULL DEFAULT '0',
  `good_id` int(11) NOT NULL DEFAULT '0',
  `user_id` int(11) NOT NULL DEFAULT '0',
  `good_score` int(11) NOT NULL DEFAULT '0',
  `content` varchar(512) NOT NULL,
  `images` varchar(512),
  `is_show` tinyint(4) NOT NULL DEFAULT '1',
  `data_flag` tinyint(4) NOT NULL DEFAULT '1',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `cabinet_id` (`cabinet_id`),
  KEY `good_id` (`good_id`,`data_flag`,`is_show`) USING BTREE,
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;