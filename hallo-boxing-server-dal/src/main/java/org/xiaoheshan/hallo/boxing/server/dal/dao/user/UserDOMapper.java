package org.xiaoheshan.hallo.boxing.server.dal.dao.user;

import org.xiaoheshan.hallo.boxing.server.dal.dao.user.model.UserDO;

public interface UserDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserDO record);

    int insertSelective(UserDO record);

    UserDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserDO record);

    int updateByPrimaryKey(UserDO record);
}