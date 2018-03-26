package org.xiaoheshan.hallo.boxing.server.dal.dao.area.model;

import java.util.Date;

/**
 * areaDO
 * 
 * @author Administrator
 * @since 03-26-2018
 */
@lombok.Data
public class AreaDO {
    /**  */
    private Integer id;

    /**  */
    private Integer parentId;

    /**  */
    private String name;

    /**  */
    private Byte isShow;

    /**  */
    private Integer sort;

    /**  */
    private String key;

    /**  */
    private Byte type;

    /**  */
    private Byte dataFlag;

    /**  */
    private Date createFime;
}