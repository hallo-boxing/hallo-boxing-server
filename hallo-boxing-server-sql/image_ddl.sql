SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `image`;
CREATE TABLE image
(
  img_id      INT AUTO_INCREMENT
  COMMENT '图片ID'
    PRIMARY KEY,
  from_type   TINYINT DEFAULT '0' NOT NULL
  COMMENT '来自哪',
  data_id     INT DEFAULT '0'     NOT NULL
  COMMENT '对象ID',
  img_path    VARCHAR(150)        NOT NULL
  COMMENT '图片路径',
  img_size    INT DEFAULT '0'     NOT NULL
  COMMENT '图片大小',
  is_use      TINYINT DEFAULT '1' NOT NULL
  COMMENT '是否有使用',
  create_time DATETIME            NOT NULL
  COMMENT '创建时间',
  from_table  VARCHAR(50)         NULL
  COMMENT '来自哪张表	',
  own_id      INT                 NULL
  COMMENT '上传者Id',
  data_flag   TINYINT DEFAULT '1' NULL
  COMMENT '删除标志'
)
  ENGINE = InnoDB;

CREATE INDEX is_use
  ON image (is_use, from_table, data_flag);