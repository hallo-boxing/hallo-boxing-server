package org.xiaoheshan.hallo.boxing.server.dal.dao.good.nfc;

import org.apache.ibatis.annotations.Mapper;

/**
 * good_nfcMapper
 * 
 * @author Administrator
 * @since 03-27-2018
 */
@Mapper
public interface GoodNfcDOMapperExt extends GoodNfcDOMapper {
    Integer selectGoodIdByNfcCode(Integer nfcCode);
}