package io.plus.shop.order.feign;

import io.plus.shop.bean.Product;
import io.plus.shop.order.feign.fallback.ProductServiceFallBack;
import io.plus.shop.order.feign.fallback.factory.ProductServiceFallBackFactory;
import io.plus.shop.utils.response.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Description:
 *
 * @author Super_light
 * @date 5/13/22 6:36 PM
 */
@FeignClient(value = "server-product", fallbackFactory = ProductServiceFallBackFactory.class)
public interface ProductService {

    /**
     * 获取商品信息
     */
    @GetMapping(value = "/product/get/{pid}")
    Product getProduct(@PathVariable("pid") Long pid);

    /**
     * 更新库存数量
     */
    @GetMapping(value = "/product/update_count/{pid}/{count}")
    Result<Integer> updateCount(@PathVariable("pid") Long pid, @PathVariable("count") Integer count);
}
