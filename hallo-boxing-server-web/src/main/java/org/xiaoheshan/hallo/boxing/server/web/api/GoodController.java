package org.xiaoheshan.hallo.boxing.server.web.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xiaoheshan.hallo.boxing.server.common.rest.RestResult;
import org.xiaoheshan.hallo.boxing.server.common.rest.RestRetCodeEnum;
import org.xiaoheshan.hallo.boxing.server.dal.dao.cabinet.good.CabinetGoodDOMapperExt;
import org.xiaoheshan.hallo.boxing.server.dal.dao.good.GoodDOMapperExt;
import org.xiaoheshan.hallo.boxing.server.dal.dao.good.model.GoodDO;
import org.xiaoheshan.hallo.boxing.server.dal.dao.good.nfc.GoodNfcDOMapperExt;
import org.xiaoheshan.hallo.boxing.server.web.constant.WebConstant;

/**
 * @author : _Chf
 * @since : 03-27-2018
 */
@RestController
@RequestMapping(WebConstant.API_PATH + "good")
@Api("商品控制器")
public class GoodController {

    @Autowired
    private GoodDOMapperExt goodDOMapperExt;
    @Autowired
    private CabinetGoodDOMapperExt cabinetGoodDOMapperExt;
    @Autowired
    private GoodNfcDOMapperExt goodNfcDOMapperExt;

    @PostMapping("/get-by-cabinet/{cabinet_id}")
    @ApiOperation("获取商品信息")
    public RestResult<GoodDO> get(@PathVariable("cabinet_id") Integer cabinetId) {
        Integer goodId = cabinetGoodDOMapperExt.selectGoodIdByCabinetId(cabinetId);
        GoodDO goodDO = goodDOMapperExt.selectByPrimaryKey(goodId);
        if (goodDO != null) {
            return RestResult.<GoodDO>builder().success(goodDO).build();
        }
        return RestResult.<GoodDO>builder().success().build();
    }

    @PostMapping("/get-by-nfc/{nfc_code}")
    public RestResult<Integer> getByNfc(@PathVariable("nfc_code") Integer nfcCode) {
        Integer goodId = goodNfcDOMapperExt.selectGoodIdByNfcCode(nfcCode);
        if (goodId != null) {
            return RestResult.<Integer>builder().success(goodId).build();
        }
        return RestResult.<Integer>builder().failed(RestRetCodeEnum.ILLEGAL_ARGUMENT).build();
    }
}
