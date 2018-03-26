package org.xiaoheshan.hallo.boxing.server.dal.dao.good.cat.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * good_catDO
 * 
 * @author Administrator
 * @since 03-26-2018
 */
@lombok.Data
public class GoodCatDO {
    /** 分类ID */
    private Integer catId;

    /** 父类ID */
    private Integer parentId;

    /** 分类名称 */
    private String catName;

    /** 是否显示 */
    private Byte isShow;

    /** 是否显示楼层 */
    private Byte isFloor;

    /** 排序号 */
    private Integer catSort;

    /** 删除标志 */
    private Byte dataFlag;

    /** 建立时间 */
    private Date createTime;

    /** 商品佣金比例 */
    private BigDecimal commissionRate;
}