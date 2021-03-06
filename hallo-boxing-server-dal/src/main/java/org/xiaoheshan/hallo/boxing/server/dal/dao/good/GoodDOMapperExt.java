package org.xiaoheshan.hallo.boxing.server.dal.dao.good;

import org.apache.ibatis.annotations.Mapper;
import org.xiaoheshan.hallo.boxing.server.dal.dao.good.model.GoodDO;

import java.util.List;

/**
 * goodMapper
 * 
 * @author Administrator
 * @since 03-26-2018
 */
@Mapper
public interface GoodDOMapperExt extends GoodDOMapper {
    List<GoodDO> selectGoodByUserId(Integer userId);
}