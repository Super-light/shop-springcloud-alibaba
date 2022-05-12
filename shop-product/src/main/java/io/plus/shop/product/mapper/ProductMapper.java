package io.plus.shop.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.plus.shop.bean.Product;
import org.apache.ibatis.annotations.Param;

/**
 * Description:
 *
 * @author Super_light
 * @date 5/11/22 5:04 PM
 */
public interface ProductMapper extends BaseMapper<Product> {

    /**
     * 扣减商品库存
     */
    int updateProductStockById(@Param("count") Integer count, @Param("id") Long id);
}
