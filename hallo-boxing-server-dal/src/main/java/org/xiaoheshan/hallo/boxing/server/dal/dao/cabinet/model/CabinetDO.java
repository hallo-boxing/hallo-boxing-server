package org.xiaoheshan.hallo.boxing.server.dal.dao.cabinet.model;

import java.util.Date;

/**
 * cabinetDO
 * 
 * @author Administrator
 * @since 03-27-2018
 */
@lombok.Data
public class CabinetDO {
    /** 共享柜ID */
    private Integer id;

    /** 区域路径 */
    private String areaIdPath;

    /** 最终所属区域ID */
    private Integer areaId;

    /** 共享柜名称 */
    private String name;

    /** 终端IP地址 */
    private String ipAddress;

    /** 删除标志 */
    private Byte dataFlag;

    /** 创建时间 */
    private Date createTime;
}