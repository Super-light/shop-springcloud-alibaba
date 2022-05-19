package io.plus.shop.order.feign.fallback;

import io.plus.shop.bean.Product;
import io.plus.shop.order.feign.ProductService;
import io.plus.shop.utils.response.Result;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author Super_light
 * @date 5/19/22 2:39 PM
 */
@Component
public class ProductServiceFallBack implements ProductService {
    @Override
    public Product getProduct(Long pid) {
        Product product = new Product();
        product.setId(-1L);
        return product;
    }

    @Override
    public Result<Integer> updateCount(Long pid, Integer count) {
        Result<Integer> result = new Result<>();
        result.setCode(1001);
        result.setCodeMsg("触发了容错逻辑");
        return result;
    }
}
