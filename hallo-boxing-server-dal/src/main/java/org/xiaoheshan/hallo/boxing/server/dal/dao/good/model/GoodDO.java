package org.xiaoheshan.hallo.boxing.server.dal.dao.good.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * goodDO
 * 
 * @author Administrator
 * @since 04-07-2018
 */
@lombok.Data
public class GoodDO {
    /** 商品ID */
    private Integer id;

    /** 所属用户 */
    private Integer userId;

    /** 商品名称 */
    private String name;

    /** 商品图片 */
    private String img;

    /** 市场价 */
    private BigDecimal marketPrice;

    /** 出租价 */
    private BigDecimal rentPrice;

    /** 商品分类ID路径 */
    private String catIdPath;

    /** 商品分类最后一级ID */
    private Integer catId;

    /** 商品分类第一级ID */
    private Integer catId1;

    /** 商品分类第二级ID */
    private Integer catId2;

    /** 商品描述 */
    private String desc;

    /** 商品状态 */
    private Byte status;

    /** 出租次数 */
    private Integer rentNum;

    /** 出租时间 */
    private Integer rentTime;

    /** 商品相册 */
    private String gallery;

    /** 0.删除 1.有效 */
    private Byte dataFlag;

    /**  */
    private Date createTime;
}