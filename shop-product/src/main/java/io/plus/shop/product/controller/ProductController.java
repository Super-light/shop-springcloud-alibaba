package io.plus.shop.product.controller;

import com.alibaba.fastjson.JSONObject;
import io.plus.shop.bean.Product;
import io.plus.shop.product.service.api.ProductService;
import io.plus.shop.utils.contants.HttpCode;
import io.plus.shop.utils.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @author Super_light
 * @date 5/11/22 5:04 PM
 */
@RestController
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/get/{pid}")
    public Product getProduct(@PathVariable("pid") Long pid){
        Product product = productService.getProductById(pid);
        log.info("获取到的商品信息为：{}", JSONObject.toJSONString(product));
        return product;
    }

    @GetMapping(value = "/update_count/{pid}/{count}")
    public Result<Integer> updateCount(@PathVariable("pid") Long pid, @PathVariable("count") Integer count){
        log.info("更新商品库存传递的参数为: 商品id:{}, 购买数量:{} ", pid, count);
        int updateCount = productService.updateProductStockById(count, pid);
        Result<Integer> result = new Result<>(HttpCode.SUCCESS, "执行成功", updateCount);
        return result;
    }
}
