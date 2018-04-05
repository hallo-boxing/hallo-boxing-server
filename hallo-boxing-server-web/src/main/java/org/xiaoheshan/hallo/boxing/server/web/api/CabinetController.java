package org.xiaoheshan.hallo.boxing.server.web.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.xiaoheshan.hallo.boxing.server.common.rest.RestPageResult;
import org.xiaoheshan.hallo.boxing.server.common.rest.RestResult;
import org.xiaoheshan.hallo.boxing.server.common.rest.RestRetCodeEnum;
import org.xiaoheshan.hallo.boxing.server.dal.dao.cabinet.CabinetDOMapperExt;
import org.xiaoheshan.hallo.boxing.server.dal.dao.cabinet.good.CabinetGoodDOMapperExt;
import org.xiaoheshan.hallo.boxing.server.dal.dao.cabinet.good.model.CabinetGoodDO;
import org.xiaoheshan.hallo.boxing.server.dal.dao.cabinet.model.CabinetDO;
import org.xiaoheshan.hallo.boxing.server.dal.dao.good.GoodDOMapperExt;
import org.xiaoheshan.hallo.boxing.server.dal.dao.good.model.GoodDO;
import org.xiaoheshan.hallo.boxing.server.dal.dao.good.nfc.GoodNfcDOMapperExt;
import org.xiaoheshan.hallo.boxing.server.dal.dao.good.nfc.model.GoodNfcDO;
import org.xiaoheshan.hallo.boxing.server.service.connetor.PiConnector;
import org.xiaoheshan.hallo.boxing.server.service.enums.PiCommandEnum;
import org.xiaoheshan.hallo.boxing.server.service.enums.ResponseEnum;
import org.xiaoheshan.hallo.boxing.server.web.constant.WebConstant;

import java.util.Date;

/**
 * @author : _Chf
 * @since : 03-27-2018
 */
@RestController
@RequestMapping(WebConstant.API_PATH + "cabinet")
@Api("共享柜控制器")
public class CabinetController {

    @Autowired
    private CabinetDOMapperExt cabinetDOMapperExt;
    @Autowired
    private GoodDOMapperExt goodDOMapperExt;
    @Autowired
    private CabinetGoodDOMapperExt cabinetGoodDOMapperExt;
    @Autowired
    private GoodNfcDOMapperExt goodNfcDOMapperExt;
    @Autowired
    private PiConnector piConnector;

    @PostMapping("/{cabinet_id}")
    @ApiOperation("获取共享柜信息")
    public RestResult<CabinetDO> get(@PathVariable("cabinet_id") Integer id) {
        return RestResult.<CabinetDO>builder().success(cabinetDOMapperExt.selectByPrimaryKey(id)).build();
    }

    @PostMapping("/{cabinet_id}/get-nfc-code")
    @ApiOperation("获取nfc code")
    public RestResult<String> getNfcCode(@PathVariable("cabinet_id") Integer cabinetId) {
        CabinetDO cabinetDO = cabinetDOMapperExt.selectByPrimaryKey(cabinetId);
        String ipAddress = cabinetDO.getIpAddress();
        String result = piConnector.send(ipAddress, PiCommandEnum.CHECKOUT.get());
        if (ResponseEnum.ERROR.is(result)) {
            return RestResult.<String>builder().failed(RestRetCodeEnum.ILLEGAL_ARGUMENT).build();
        }
        return RestResult.<String>builder().success(result).build();
    }

    @PostMapping("/{cabinet_id}/open-door")
    @ApiOperation("开门")
    public RestResult<Void> openDoor(@PathVariable("cabinet_id") Integer cabinetId) {
        CabinetDO cabinetDO = cabinetDOMapperExt.selectByPrimaryKey(cabinetId);
        String ipAddress = cabinetDO.getIpAddress();
        String result = piConnector.send(ipAddress, PiCommandEnum.DOOR.getWithParam("1"));
        if (ResponseEnum.ERROR.is(result)) {
            return RestResult.<Void>builder().failed(RestRetCodeEnum.ILLEGAL_ARGUMENT).build();
        }
        return RestResult.<Void>builder().success().build();
    }

    @PostMapping("/{cabinet_id}/close-door")
    @ApiOperation("关门")
    public RestResult<Void> closeDoor(@PathVariable("cabinet_id") Integer cabinetId) {
        CabinetDO cabinetDO = cabinetDOMapperExt.selectByPrimaryKey(cabinetId);
        String ipAddress = cabinetDO.getIpAddress();
        String result = piConnector.send(ipAddress, PiCommandEnum.DOOR.getWithParam("0"));
        if (ResponseEnum.ERROR.is(result)) {
            return RestResult.<Void>builder().failed(RestRetCodeEnum.ILLEGAL_ARGUMENT).build();
        }
        return RestResult.<Void>builder().success().build();
    }

    @PostMapping("/{cabinet_id}/take-photo")
    @ApiOperation("拍照")
    public RestResult<Void> takePhoto(@PathVariable("cabinet_id") Integer cabinetId,
                                      @RequestBody Integer userId) {
        CabinetDO cabinetDO = cabinetDOMapperExt.selectByPrimaryKey(cabinetId);
        String ipAddress = cabinetDO.getIpAddress();
        String result = piConnector.send(ipAddress, PiCommandEnum.CAMARA.getWithParam(userId.toString()));
        if (ResponseEnum.ERROR.is(result)) {
            return RestResult.<Void>builder().failed(RestRetCodeEnum.ILLEGAL_ARGUMENT).build();
        }
        return RestResult.<Void>builder().success().build();
    }

    @PostMapping("/{cabinet_id}/entry/{nfc_code}")
    @ApiOperation("录入商品")
    public RestResult<Void> entry(@PathVariable("cabinet_id") Integer cabinetId,
                                  @PathVariable("nfc_code") String nfcCode,
                                  @RequestBody GoodDO good) {
        CabinetDO cabinetDO = cabinetDOMapperExt.selectByPrimaryKey(cabinetId);
        if (cabinetDO == null) {
            return RestResult.<Void>builder().failed(RestRetCodeEnum.ILLEGAL_ARGUMENT).build();
        }
        int goodId = goodDOMapperExt.insertSelective(good);
        if (goodId <= 0) {
            return RestResult.<Void>builder().failed(RestRetCodeEnum.ILLEGAL_ARGUMENT).build();
        }
        CabinetGoodDO cabinetGoodDO = new CabinetGoodDO();
        cabinetGoodDO.setCabinetId(cabinetId);
        cabinetGoodDO.setGoodId(goodId);
        cabinetGoodDO.setCreateTime(new Date());
        int result = cabinetGoodDOMapperExt.insertSelective(cabinetGoodDO);
        if (result <= 0) {
            return RestResult.<Void>builder().failed(RestRetCodeEnum.ILLEGAL_ARGUMENT).build();
        }
        GoodNfcDO goodNfcDO = new GoodNfcDO();
        goodNfcDO.setNfcCode(nfcCode);
        goodNfcDO.setGoodId(goodId);
        goodNfcDO.setCreateTime(new Date());
        int result1 = goodNfcDOMapperExt.insertSelective(goodNfcDO);
        if (result1 <= 0) {
            return RestResult.<Void>builder().failed(RestRetCodeEnum.ILLEGAL_ARGUMENT).build();
        }
        return RestResult.<Void>builder().success().build();
    }



    @PostMapping("/{cabinet_id}/entry/{nfc_code}")
    @ApiOperation("归还物品")
    public RestPageResult<Void> retur(@PathVariable("cabinet_id") Integer cabinetId,
                                      @PathVariable("nfc_code") Integer nfcCode) {
        return null;
    }

}
