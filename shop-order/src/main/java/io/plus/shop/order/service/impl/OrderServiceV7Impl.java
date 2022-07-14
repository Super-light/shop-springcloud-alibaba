package io.plus.shop.order.service.impl;

import com.alibaba.fastjson.JSONObject;
import io.plus.shop.bean.Order;
import io.plus.shop.bean.OrderItem;
import io.plus.shop.bean.Product;
import io.plus.shop.bean.User;
import io.plus.shop.order.feign.ProductService;
import io.plus.shop.order.feign.UserService;
import io.plus.shop.order.mapper.OrderItemMapper;
import io.plus.shop.order.mapper.OrderMapper;
import io.plus.shop.order.service.api.OrderService;
import io.plus.shop.params.OrderParams;
import io.plus.shop.utils.contants.HttpCode;
import io.plus.shop.utils.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * Description:
 *
 * @author Super_light
 * @date 7/2/22 6:11 PM
 */
@Service(value = "v7")
@Slf4j
public class OrderServiceV7Impl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrder(OrderParams orderParams) {
        if (orderParams.isEmpty()){
            throw new RuntimeException("参数异常: " + JSONObject.toJSONString(orderParams));
        }

        User user = userService.getUser(orderParams.getUserId());
        checkUser(user,orderParams);

        Product product = productService.getProduct(orderParams.getProductId());
        checkProduct(product,orderParams);

        final Order order = insertDB(user, orderParams, product);
        Result<Integer> result = productService.updateCount(orderParams.getProductId(), orderParams.getCount());
        if (result.getCode() == 1001){
            throw new RuntimeException("触发了商品微服务的容错逻辑: " + JSONObject.toJSONString(orderParams));
        }
        if (result.getCode() != HttpCode.SUCCESS){
            throw new RuntimeException("库存扣减失败");
        }
        log.info("库存扣减成功");
        //发送消息队列
        rocketMQTemplate.convertAndSend("order-topic", order);
    }


    /**
     * 校验用户
     * @param user 用户实体
     * @param orderParams 订单参数
     */
    private void checkUser (User user,OrderParams orderParams) {
        if (user == null){
            throw new RuntimeException("未获取到用户信息: " + JSONObject.toJSONString(orderParams));
        }
        if (user.getId() == -1){
            throw new RuntimeException("触发了用户微服务的容错逻辑: " + JSONObject.toJSONString(orderParams));
        }
    }

    /**
     * 校验商品数据
     * @param product 商品实体
     * @param orderParams 订单参数
     */
    private void checkProduct(Product product, OrderParams orderParams) {
        if (product == null){
            throw new RuntimeException("未获取到商品信息: " + JSONObject.toJSONString(orderParams));
        }
        if (product.getId() == -1){
            throw new RuntimeException("触发了商品微服务的容错逻辑: " + JSONObject.toJSONString(orderParams));
        }

        if (product.getProStock() < orderParams.getCount()){
            throw new RuntimeException("商品库存不足: " + JSONObject.toJSONString(orderParams));
        }
    }

    /**
     * 入库操作
     * @param user 用户实体
     * @param orderParams 订单参数
     * @param product 商品实体
     * @return
     */
    private Order insertDB(User user,OrderParams orderParams,Product product) {
        Order order = new Order();
        order.setAddress(user.getAddress());
        order.setPhone(user.getPhone());
        order.setUserId(user.getId());
        order.setUsername(user.getUsername());
        order.setTotalPrice(product.getProPrice().multiply(BigDecimal.valueOf(orderParams.getCount())));
        orderMapper.insert(order);

        OrderItem orderItem = new OrderItem();
        orderItem.setNumber(orderParams.getCount());
        orderItem.setOrderId(order.getId());
        orderItem.setProId(product.getId());
        orderItem.setProName(product.getProName());
        orderItem.setProPrice(product.getProPrice());
        orderItemMapper.insert(orderItem);
        return order;
    }
}

