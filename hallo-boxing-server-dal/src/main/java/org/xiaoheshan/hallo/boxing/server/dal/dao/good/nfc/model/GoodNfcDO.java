package org.xiaoheshan.hallo.boxing.server.dal.dao.good.nfc.model;

import java.util.Date;

/**
 * good_nfcDO
 * 
 * @author Administrator
 * @since 03-27-2018
 */
@lombok.Data
public class GoodNfcDO {
    /**  */
    private Integer id;

    /**  */
    private Integer goodId;

    /**  */
    private String nfcCode;

    /**  */
    private Byte dataFlag;

    /**  */
    private Date createTime;
}