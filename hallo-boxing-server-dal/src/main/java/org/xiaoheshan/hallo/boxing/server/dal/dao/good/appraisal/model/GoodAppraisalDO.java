package org.xiaoheshan.hallo.boxing.server.dal.dao.good.appraisal.model;

import java.util.Date;

/**
 * good_appraisalDO
 * 
 * @author Administrator
 * @since 03-26-2018
 */
@lombok.Data
public class GoodAppraisalDO {
    /**  */
    private Integer id;

    /**  */
    private Integer cabinetId;

    /**  */
    private Integer orderId;

    /**  */
    private Integer goodId;

    /**  */
    private Integer userId;

    /**  */
    private Integer goodScore;

    /**  */
    private String content;

    /**  */
    private String images;

    /**  */
    private Byte isShow;

    /**  */
    private Byte dataFlag;

    /**  */
    private Date createTime;
}