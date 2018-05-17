package org.xiaoheshan.hallo.boxing.server.dal.dao.user;

import org.apache.ibatis.annotations.Mapper;
import org.xiaoheshan.hallo.boxing.server.dal.dao.user.model.UserDO;

/**
 * userMapper
 * 
 * @author Administrator
 * @since 03-26-2018
 */
@Mapper
public interface UserDOMapperExt extends UserDOMapper {

    UserDO selectByMobile(String mobile);
}