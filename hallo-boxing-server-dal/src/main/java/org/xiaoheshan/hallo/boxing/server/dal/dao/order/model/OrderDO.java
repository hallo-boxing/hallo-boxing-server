package org.xiaoheshan.hallo.boxing.server.dal.dao.order.model;

import java.util.Date;

/**
 * orderDO
 * 
 * @author Administrator
 * @since 03-29-2018
 */
@lombok.Data
public class OrderDO {
    /** 订单ID */
    private Integer id;

    /**  */
    private Integer goodId;

    /** 用户ID */
    private Integer userId;

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
}