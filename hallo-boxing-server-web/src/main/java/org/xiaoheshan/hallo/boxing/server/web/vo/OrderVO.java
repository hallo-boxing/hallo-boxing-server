package org.xiaoheshan.hallo.boxing.server.web.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author : _Chf
 * @since : 05-05-2018
 */
@Data
public class OrderVO {

    /** 订单ID */
    private Integer id;

    /**  */
    private Integer goodId;

    /** 付款人用户ID */
    private Integer fromUserId;

    /** 付款人用户ID */
    private Integer toUserId;

    /** 订单状态 */
    private Byte status;

    /** 是否支付 */
    private Byte isPay;

    /** 订单备注 */
    private String remarks;

    /** 订单ID */
    private Byte isClosed;

    /** 收货时间 */
    private Date receiveTime;

    /** 收货共享柜ID */
    private Integer receiveCabinetId;

    /** 发货时间 */
    private Date deliveryTime;

    /** 发货共享柜ID */
    private Integer deliveryCabinetId;

    /** 订单有效标志 */
    private Byte dataFlag;

    /** 下单时间 */
    private Date createTime;

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

    /** 出租次数 */
    private Integer rentNum;

    /** 出租时间 */
    private Integer rentTime;

    /** 商品相册 */
    private String gallery;

    private String toUsername;
}
