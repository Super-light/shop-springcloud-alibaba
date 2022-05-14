package io.plus.shop.order.service.api;

import io.plus.shop.params.OrderParams;

/**
 * Description:
 *
 * @author Super_light
 * @date 5/11/22 5:28 PM
 */
public interface OrderService {
    /**
     * 保存订单
     */
    void saveOrder(OrderParams orderParams);
}
