package org.xiaoheshan.hallo.boxing.server.dal.dao.image.model;

import java.util.Date;

/**
 * imageDO
 * 
 * @author Administrator
 * @since 03-26-2018
 */
@lombok.Data
public class ImageDO {
    /** 图片ID */
    private Integer imgId;

    /** 来自哪 */
    private Byte fromType;

    /** 对象ID */
    private Integer dataId;

    /** 图片路径 */
    private String imgPath;

    /** 图片大小 */
    private Integer imgSize;

    /** 是否有使用 */
    private Byte isUse;

    /** 创建时间 */
    private Date createTime;

    /** 来自哪张表	 */
    private String fromTable;

    /** 上传者Id */
    private Integer ownId;

    /** 删除标志 */
    private Byte dataFlag;
}