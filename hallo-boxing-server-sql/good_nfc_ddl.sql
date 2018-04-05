SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `good_nfc`;
CREATE TABLE `good_nfc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `good_id` int(11) NOT NULL DEFAULT '0',
  `nfc_code` char(8) NOT NULL,
  `data_flag` tinyint(4) NOT NULL DEFAULT '1',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `nfc_code` (`nfc_code`),
  KEY `good_id` (`good_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;