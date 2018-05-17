package org.xiaoheshan.hallo.boxing.server.web.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.xiaoheshan.hallo.boxing.server.common.rest.RestPageResult;
import org.xiaoheshan.hallo.boxing.server.common.rest.RestResult;
import org.xiaoheshan.hallo.boxing.server.common.rest.RestRetCodeEnum;
import org.xiaoheshan.hallo.boxing.server.dal.dao.cabinet.good.CabinetGoodDOMapperExt;
import org.xiaoheshan.hallo.boxing.server.dal.dao.good.GoodDOMapperExt;
import org.xiaoheshan.hallo.boxing.server.dal.dao.good.model.GoodDO;
import org.xiaoheshan.hallo.boxing.server.dal.dao.order.OrderDOMapperExt;
import org.xiaoheshan.hallo.boxing.server.dal.dao.order.model.OrderDO;
import org.xiaoheshan.hallo.boxing.server.dal.dao.user.UserDOMapperExt;
import org.xiaoheshan.hallo.boxing.server.dal.dao.user.model.UserDO;
import org.xiaoheshan.hallo.boxing.server.web.constant.WebConstant;
import org.xiaoheshan.hallo.boxing.server.web.vo.OrderVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author : _Chf
 * @since : 03-29-2018
 */
@RestController
@RequestMapping(WebConstant.API_PATH + "order")
@Api("订单控制器")
public class OrderController {

    private final Logger logger = LoggerFactory.getLogger(ImageController.class);

    @Autowired
    private OrderDOMapperExt orderDOMapperExt;
    @Autowired
    private CabinetGoodDOMapperExt cabinetGoodDOMapperExt;
    @Autowired
    private GoodDOMapperExt goodDOMapperExt;
    @Autowired
    private UserDOMapperExt userDOMapperExt;

    @PostMapping("/create")
    @ApiOperation("创建订单")
    public RestResult<OrderDO> create(@RequestBody OrderDO orderDO) {
        logger.info("创建订单");
        int result = cabinetGoodDOMapperExt.updateDataFlagByCabinetIdAndGoodId(
                0,
                orderDO.getDeliveryCabinetId(),
                orderDO.getGoodId());
        if (result <= 0) {
            return RestResult.<OrderDO>builder().failed(RestRetCodeEnum.ILLEGAL_ARGUMENT).build();
        }

        GoodDO goodDO = goodDOMapperExt.selectByPrimaryKey(orderDO.getGoodId());
        Integer rentNum = Optional.ofNullable(goodDO).map(GoodDO::getRentNum).orElse(-1) + 1;
        goodDO.setRentNum(rentNum);
        goodDOMapperExt.updateByPrimaryKey(goodDO);

        orderDO.setIsPay((byte) 0);
        orderDO.setCreateTime(new Date());
        /* 0：未付款 */
        orderDO.setStatus((byte) 0);
        orderDO.setDeliveryTime(new Date());
        orderDO.setCreateTime(new Date());
        int result0 = orderDOMapperExt.insertSelective(orderDO);
        if (result0 <= 0) {
            return RestResult.<OrderDO>builder().failed(RestRetCodeEnum.ILLEGAL_ARGUMENT).build();
        }
        return RestResult.<OrderDO>builder().success(orderDO).build();
    }

    @PostMapping("/list-from/{user_id}")
    @ApiOperation("获取订单")
    public RestResult<List<OrderVO>> listByFromUserId(@PathVariable("user_id") Integer userId) {
        logger.info("获取订单");
        List<OrderVO> result = new ArrayList<>();
        List<OrderDO> orderDOS = orderDOMapperExt.selectOrderByFromUserId(userId);
        if (orderDOS == null) {
            return RestResult.<List<OrderVO>>builder().failed(RestRetCodeEnum.ILLEGAL_ARGUMENT).build();
        }
        for (OrderDO orderDO : orderDOS) {
            OrderVO orderVO = new OrderVO();
            GoodDO goodDO = goodDOMapperExt.selectByPrimaryKey(orderDO.getGoodId());
            BeanUtils.copyProperties(goodDO, orderVO);
            UserDO userDO = userDOMapperExt.selectByPrimaryKey(orderDO.getToUserId());
            orderVO.setToUsername(userDO.getName());
            BeanUtils.copyProperties(orderDO, orderVO);
            result.add(orderVO);
        }
        return RestResult.<List<OrderVO>>builder().success(result).build();
    }

    @PostMapping("/list-to/{user_id}")
    @ApiOperation("获取订单")
    public RestResult<List<OrderVO>> listByToUserId(@PathVariable("user_id") Integer userId) {
        logger.info("获取订单");
        List<OrderVO> result = new ArrayList<>();
        List<OrderDO> orderDOS = orderDOMapperExt.selectOrderByToUserId(userId);
        if (orderDOS == null) {
            return RestResult.<List<OrderVO>>builder().failed(RestRetCodeEnum.ILLEGAL_ARGUMENT).build();
        }
        for (OrderDO orderDO : orderDOS) {
            OrderVO orderVO = new OrderVO();
            GoodDO goodDO = goodDOMapperExt.selectByPrimaryKey(orderDO.getGoodId());
            BeanUtils.copyProperties(goodDO, orderVO);
            UserDO userDO = userDOMapperExt.selectByPrimaryKey(orderDO.getToUserId());
            orderVO.setToUsername(userDO.getName());
            BeanUtils.copyProperties(orderDO, orderVO);
            result.add(orderVO);
        }
        return RestResult.<List<OrderVO>>builder().success(result).build();
    }

    @PostMapping("/{order_id}/pay")
    @ApiOperation("付款")
    public RestResult<Void> pay(@PathVariable("order_id") Integer orderId) {
        logger.info("付款");
        OrderDO orderDO = orderDOMapperExt.selectByPrimaryKey(orderId);
        /* 付款成功，完成订单 */
        orderDO.setStatus((byte) 1);
        orderDO.setDeliveryTime(new Date());
        int result = orderDOMapperExt.updateByPrimaryKeySelective(orderDO);
        if (result <= 0) {
            return RestResult.<Void>builder().failed(RestRetCodeEnum.ILLEGAL_ARGUMENT).build();
        }
        return RestResult.<Void>builder().success().build();
    }

    @PostMapping("/{order_id}/cancel")
    @ApiOperation("取消订单")
    public RestResult<Void> cancel(@PathVariable("order_id") Integer orderId) {
        OrderDO orderDO = orderDOMapperExt.selectByPrimaryKey(orderId);
        /* 取消订单 */
        orderDO.setStatus((byte) 2);
        orderDO.setDeliveryTime(new Date());
        int result = orderDOMapperExt.updateByPrimaryKeySelective(orderDO);
        if (result <= 0) {
            return RestResult.<Void>builder().failed(RestRetCodeEnum.ILLEGAL_ARGUMENT).build();
        }
        return RestResult.<Void>builder().success().build();
    }

    @PostMapping("/return/{user_id}/{order_id}/{cabinet_id}")
    @ApiOperation("归还物品")
    public RestResult<Void> retur(@PathVariable("user_id") Integer userId,
                                  @PathVariable("order_id") Integer orderId,
                                  @PathVariable("cabinet_id") Integer cabinetId) {
        logger.info("归还物品");
        OrderDO orderDO = orderDOMapperExt.selectByPrimaryKey(orderId);
        /* 待付款 */
        orderDO.setStatus((byte) 3);
        orderDO.setReceiveCabinetId(cabinetId);
        orderDO.setReceiveTime(new Date());
        int result = orderDOMapperExt.updateByPrimaryKeySelective(orderDO);
        if (result > 0) {
            return RestResult.<Void>builder().success().build();
        }
        return RestResult.<Void>builder().failed(RestRetCodeEnum.ILLEGAL_ARGUMENT).build();
    }

}
