package org.xiaoheshan.hallo.boxing.server.dal.dao.user.model;

import java.util.Date;

/**
 * userDO
 * 
 * @author Administrator
 * @since 04-14-2018
 */
@lombok.Data
public class UserDO {
    /**  */
    private Integer id;

    /**  */
    private String loginName;

    /**  */
    private String loginPwd;

    /**  */
    private Byte sex;

    /**  */
    private String name;

    /**  */
    private String trueName;

    /**  */
    private Date birthday;

    /**  */
    private String photo;

    /**  */
    private String qq;

    /**  */
    private String phone;

    /**  */
    private String email;

    /**  */
    private String lastIp;

    /**  */
    private Date lastTime;

    /**  */
    private Byte dataFlag;

    /**  */
    private Date createTime;

    /**  */
    private String payPwd;
}