package org.xiaoheshan.hallo.boxing.server.dal.dao.cabinet.good.model;

import java.util.Date;

/**
 * cabinet_goodDO
 * 
 * @author Administrator
 * @since 03-27-2018
 */
@lombok.Data
public class CabinetGoodDO {
    /**  */
    private Integer id;

    /**  */
    private Integer cabinetId;

    /**  */
    private Integer goodId;

    /**  */
    private Byte dataFlag;

    /**  */
    private Date createTime;
}