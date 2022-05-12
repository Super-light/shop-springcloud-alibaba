package io.plus.shop.product.service.api;

import io.plus.shop.bean.Product;

/**
 * Description:
 *
 * @author Super_light
 * @date 5/11/22 5:03 PM
 */
public interface ProductService {

    /**
     * 根据商品id获取商品信息
     */
    Product getProductById(Long pid);


    /**
     * 扣减商品库存
     */
    int updateProductStockById(Integer count, Long id);
}
