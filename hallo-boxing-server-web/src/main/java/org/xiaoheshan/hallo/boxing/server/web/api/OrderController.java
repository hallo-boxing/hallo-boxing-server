package org.xiaoheshan.hallo.boxing.server.web.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.xiaoheshan.hallo.boxing.server.common.rest.RestPageResult;
import org.xiaoheshan.hallo.boxing.server.common.rest.RestResult;
import org.xiaoheshan.hallo.boxing.server.common.rest.RestRetCodeEnum;
import org.xiaoheshan.hallo.boxing.server.dal.dao.cabinet.good.CabinetGoodDOMapperExt;
import org.xiaoheshan.hallo.boxing.server.dal.dao.order.OrderDOMapperExt;
import org.xiaoheshan.hallo.boxing.server.dal.dao.order.model.OrderDO;
import org.xiaoheshan.hallo.boxing.server.web.constant.WebConstant;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : _Chf
 * @since : 03-29-2018
 */
@RestController
@RequestMapping(WebConstant.API_PATH + "order")
@Api("订单控制器")
public class OrderController {

    @Autowired
    private OrderDOMapperExt orderDOMapperExt;
    @Autowired
    private CabinetGoodDOMapperExt cabinetGoodDOMapperExt;

    @PostMapping("/create")
    @ApiOperation("创建订单")
    public RestResult<OrderDO> create(@RequestBody OrderDO orderDO) {
        int result = cabinetGoodDOMapperExt.updateDataFlagByCabinetIdAndGoodId(0,
                orderDO.getGoodId(), orderDO.getDeliveryCabinetId());
        if (result <= 0) {
            return RestResult.<OrderDO>builder().failed(RestRetCodeEnum.ILLEGAL_ARGUMENT).build();
        }
        orderDO.setIsPay((byte) 0);
        orderDO.setCreateTime(new Date());
        /* 0：未付款 */
        orderDO.setStatus((byte) 0);
        int result0 = orderDOMapperExt.insertSelective(orderDO);
        if (result0 <= 0) {
            return RestResult.<OrderDO>builder().failed(RestRetCodeEnum.ILLEGAL_ARGUMENT).build();
        }
        return RestResult.<OrderDO>builder().success(orderDO).build();
    }

    @PostMapping("/list/{user_id}")
    @ApiOperation("获取订单")
    public RestPageResult<OrderDO> list(@PathVariable("user_id") Integer userId) {
        List<OrderDO> orderDOS = orderDOMapperExt.selectOrderByUserId(userId);
        if (orderDOS == null) {
            return RestPageResult.<OrderDO>builder().failed(RestRetCodeEnum.ILLEGAL_ARGUMENT).build();
        }
        return RestPageResult.<OrderDO>builder().success(orderDOS, orderDOS.size()).build();
    }

    @PostMapping("/{order_id}/pay")
    @ApiOperation("付款")
    public RestResult<Void> pay(@PathVariable("order_id") Integer orderId) {
        OrderDO orderDO = orderDOMapperExt.selectByPrimaryKey(orderId);
        /* 付款成功，完成订单 */
        orderDO.setStatus((byte) 2);
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
        orderDO.setStatus((byte) 3);
        orderDO.setDeliveryTime(new Date());
        int result = orderDOMapperExt.updateByPrimaryKeySelective(orderDO);
        if (result <= 0) {
            return RestResult.<Void>builder().failed(RestRetCodeEnum.ILLEGAL_ARGUMENT).build();
        }
        return RestResult.<Void>builder().success().build();
    }

    @PostMapping("/return/{user_id}/{good_id}")
    @ApiOperation("归还物品")
    public RestResult<Void> retur(@PathVariable("user_id") Integer userId,
                                  @PathVariable("good_id") Integer goodId) {
        List<OrderDO> orderDOS = orderDOMapperExt.selectOrderByUserId(userId);
        List<OrderDO> mapList = orderDOS.stream().filter(orderDO -> orderDO.getGoodId().equals(goodId)).collect(Collectors.toList());
        if (mapList.get(0) != null) {
            OrderDO orderDO = orderDOS.get(0);
            /* 待付款 */
            orderDO.setStatus((byte) 3);
            int result = orderDOMapperExt.updateByPrimaryKeySelective(orderDO);
            if (result > 0) {
                return RestResult.<Void>builder().success().build();
            }
        }
        return RestResult.<Void>builder().failed(RestRetCodeEnum.ILLEGAL_ARGUMENT).build();
    }

}
