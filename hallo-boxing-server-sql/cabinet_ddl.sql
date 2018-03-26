SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `cabinet`;
CREATE TABLE `cabinet` (
  `cabinet_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '共享柜ID',
  `area_id_path` varchar(255) NOT NULL COMMENT '区域路径',
  `area_id` int(11) NOT NULL COMMENT '最终所属区域ID',
  `cabinet_name` varchar(100) NOT NULL COMMENT '共享柜名称',
  `ip_address` varchar(100) NOT NULL COMMENT '终端IP地址',
  `good_id` int(11) NOT NULL COMMENT '正在存放的商品ID',
  `is_use` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否在用：0.否 1.是',
  `data_flag` tinyint(4) NOT NULL DEFAULT '1' COMMENT '删除标志',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`cabinet_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;