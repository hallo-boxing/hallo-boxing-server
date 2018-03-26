SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `order_status` tinyint(4) NOT NULL DEFAULT '-2' COMMENT '订单状态',
  `is_pay` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否支付',
  `order_remarks` varchar(255) DEFAULT NULL COMMENT '订单备注',
  `is_closed` tinyint(4) NOT NULL DEFAULT '0' COMMENT '订单ID',
  `receive_time` datetime DEFAULT NULL COMMENT '收货时间',
  `receive_cabinet_id` int(11) NOT NULL COMMENT '收货共享柜ID',
  `delivery_time` datetime DEFAULT NULL COMMENT '发货时间',
  `delivery_cabinet_id` int(11) NOT NULL COMMENT '发货共享柜ID',
  `data_flag` tinyint(4) NOT NULL DEFAULT '1' COMMENT '订单有效标志',
  `create_time` datetime NOT NULL COMMENT '下单时间',
  PRIMARY KEY (`order_id`),
  KEY `cabinet_id` (`receive_cabinet_id`,`delivery_cabinet_id`,`data_flag`),
  KEY `user_id` (`user_id`,`data_flag`),
  KEY `order_status` (`order_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
