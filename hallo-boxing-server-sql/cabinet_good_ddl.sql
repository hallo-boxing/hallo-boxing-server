SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `cabinet_good`;
CREATE TABLE `cabinet_good` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cabinet_id` int(11) NOT NULL DEFAULT '0',
  `good_id` int(11) NOT NULL DEFAULT '0',
  `data_flag` tinyint(4) NOT NULL DEFAULT '1',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `cabinet_id` (`cabinet_id`),
  KEY `good_id` (`good_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;