package org.xiaoheshan.hallo.boxing.server.dal.dao.cabinet.good;

import org.apache.ibatis.annotations.Mapper;

/**
 * cabinet_goodMapper
 * 
 * @author Administrator
 * @since 03-27-2018
 */
@Mapper
public interface CabinetGoodDOMapperExt extends CabinetGoodDOMapper {

    Integer selectGoodIdByCabinetId(Integer cabinetId);

    int updateDataFlagByCabinetIdAndGoodId(Integer dataFlag,
                                               Integer cabinetId,
                                               Integer goodId);
}