package org.xiaoheshan.hallo.boxing.server.web.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(ImageController.class);

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
        logger.info("获取nfc code：" + cabinetId);
        CabinetDO cabinetDO = cabinetDOMapperExt.selectByPrimaryKey(cabinetId);
        String ipAddress = cabinetDO.getIpAddress();
//        String result = "test-nfc-code";
        String result = piConnector.send(ipAddress, PiCommandEnum.CHECKOUT.get());
        if (ResponseEnum.ERROR.is(result)) {
            return RestResult.<String>builder().failed(RestRetCodeEnum.ILLEGAL_ARGUMENT).build();
        }
        result = result.substring(3);
        return RestResult.<String>builder().success(result).build();
    }

    @PostMapping("/{cabinet_id}/open-door")
    @ApiOperation("开门")
    public RestResult<Void> openDoor(@PathVariable("cabinet_id") Integer cabinetId) {
        logger.info("开门：" + cabinetId);
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
        logger.info("关门：" + cabinetId);
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
    public RestResult<Integer> takePhoto(@PathVariable("cabinet_id") Integer cabinetId,
                                      @RequestBody Integer userId) {
        logger.info("拍照：" + cabinetId);
        CabinetDO cabinetDO = cabinetDOMapperExt.selectByPrimaryKey(cabinetId);
        String ipAddress = cabinetDO.getIpAddress();
        String result = piConnector.send(ipAddress, PiCommandEnum.CAMERA.getWithParam(userId.toString()));
        if (ResponseEnum.ERROR.is(result)) {
            return RestResult.<Integer>builder().failed(RestRetCodeEnum.ILLEGAL_ARGUMENT).build();
        }
        result = result.substring(3);
        logger.info("拍照成功");
        return RestResult.<Integer>builder().success(Integer.valueOf(result)).build();
    }

    @PostMapping("/{cabinet_id}/entry/{nfc_code}")
    @ApiOperation("录入商品")
    public RestResult<Void> entry(@PathVariable("cabinet_id") Integer cabinetId,
                                  @PathVariable("nfc_code") String nfcCode,
                                  @RequestBody GoodDO good) {
        logger.info("录入商品，cabinetId：" + cabinetId + "，nfcCode：" + nfcCode);
        CabinetDO cabinetDO = cabinetDOMapperExt.selectByPrimaryKey(cabinetId);
        if (cabinetDO == null) {
            return RestResult.<Void>builder().failed(RestRetCodeEnum.ILLEGAL_ARGUMENT).build();
        }
        good.setCreateTime(new Date());
        good.setDataFlag((byte) 0);
        good.setRentNum(0);
        good.setRentTime(0);
        int result = goodDOMapperExt.insertSelective(good);
        if (result <= 0) {
            return RestResult.<Void>builder().failed(RestRetCodeEnum.ILLEGAL_ARGUMENT).build();
        }
        logger.info("商品id：" + good.getId());
        CabinetGoodDO cabinetGoodDO = new CabinetGoodDO();
        cabinetGoodDO.setCabinetId(cabinetId);
        cabinetGoodDO.setGoodId(good.getId());
        cabinetGoodDO.setCreateTime(new Date());
        int result0 = cabinetGoodDOMapperExt.insertSelective(cabinetGoodDO);
        if (result0 <= 0) {
            return RestResult.<Void>builder().failed(RestRetCodeEnum.ILLEGAL_ARGUMENT).build();
        }
        GoodNfcDO goodNfcDO = new GoodNfcDO();
        goodNfcDO.setNfcCode(nfcCode);
        goodNfcDO.setGoodId(good.getId());
        goodNfcDO.setCreateTime(new Date());
        int result1 = goodNfcDOMapperExt.insertSelective(goodNfcDO);
        if (result1 <= 0) {
            return RestResult.<Void>builder().failed(RestRetCodeEnum.ILLEGAL_ARGUMENT).build();
        }
        return RestResult.<Void>builder().success().build();
    }

}
