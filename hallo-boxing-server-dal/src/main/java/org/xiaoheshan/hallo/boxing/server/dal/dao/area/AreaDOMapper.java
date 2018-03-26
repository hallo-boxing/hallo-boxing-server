package org.xiaoheshan.hallo.boxing.server.dal.dao.area;

import org.xiaoheshan.hallo.boxing.server.dal.dao.area.model.AreaDO;

public interface AreaDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AreaDO record);

    int insertSelective(AreaDO record);

    AreaDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AreaDO record);

    int updateByPrimaryKey(AreaDO record);
}