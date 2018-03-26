SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `good`;
CREATE TABLE `good` (
  `good_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `good_name` varchar(50) NOT NULL COMMENT '商品名称',
  `good_img` varchar(150) NOT NULL COMMENT '商品图片',
  `market_price` decimal(11,2) NOT NULL DEFAULT '0.00' COMMENT '市场价',
  `rent_price` decimal(11,2) NOT NULL DEFAULT '0.00' COMMENT '出租价',
  `good_cat_id_path` varchar(255) DEFAULT NULL COMMENT '商品分类ID路径',
  `good_cat_id` int(11) NOT NULL COMMENT '商品分类最后一级ID',
  `good_cat_id1` int(11) NOT NULL COMMENT '商品分类第一级ID',
  `good_cat_id2` int(11) NOT NULL COMMENT '商品分类第二级ID',
  `good_desc` varchar(512) NOT NULL COMMENT '商品描述',
  `good_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '商品状态',
  `rent_num` int(11) NOT NULL DEFAULT '0' COMMENT '出租次数',
  `rent_time` datetime NOT NULL COMMENT '出租时间',
  `gallery` varchar(512) COMMENT '商品相册',
  `data_flag` tinyint(4) NOT NULL DEFAULT '1' COMMENT '0.删除 1.有效',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`good_id`),
  KEY `cabinet_id` (`cabinet_id`) USING BTREE,
  KEY `good_status` (`good_status`,`data_flag`) USING BTREE,
  KEY `good_cat_id_path` (`good_cat_id_path`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8;