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

import java.util.ArrayList;
import java.util.concurrent.*;

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
    public Product getProduct(@PathVariable("pid") Long pid) {
        Product product = productService.getProductById(pid);
        log.info("获取到的商品信息为：{}", JSONObject.toJSONString(product));
        return product;
    }

    @GetMapping(value = "/update_count/{pid}/{count}")
    public Result<Integer> updateCount(@PathVariable("pid") Long pid, @PathVariable("count") Integer count) {
        log.info("更新商品库存传递的参数为: 商品id:{}, 购买数量:{} ", pid, count);
        int updateCount = productService.updateProductStockById(count, pid);
        Result<Integer> result = new Result<>(HttpCode.SUCCESS, "执行成功", updateCount);
        return result;
    }

//    public static void main(String[] args) throws InterruptedException {
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        ArrayList<Future<String>> futureArrayList = new ArrayList<>();
//        System.out.println("公司让你通知大家聚餐 你开车去接人");
//        Future<String> future10 = executorService.submit(() -> {
//            System.out.println("总裁：我在家上大号 我最近拉肚子比较慢 要蹲1个小时才能出来 你等会来接我吧");
//            TimeUnit.SECONDS.sleep(10);
//            System.out.println("总裁：1小时了 我上完大号了。你来接吧");
//            return "总裁上完大号了";
//        });
//        futureArrayList.add(future10);
//        Future<String> future3 = executorService.submit(() -> {
//            System.out.println("研发：我在家上大号 我比较快 要蹲3分钟就可以出来 你等会来接我吧");
//            TimeUnit.SECONDS.sleep(3);
//            System.out.println("研发：3分钟 我上完大号了。你来接吧");
//            return "研发上完大号了";
//        });
//        futureArrayList.add(future3);
//        Future<String> future6 = executorService.submit(() -> {
//            System.out.println("中层管理：我在家上大号  要蹲10分钟就可以出来 你等会来接我吧");
//            TimeUnit.SECONDS.sleep(6);
//            System.out.println("中层管理：10分钟 我上完大号了。你来接吧");
//            return "中层管理上完大号了";
//        });
//        futureArrayList.add(future6);
//        TimeUnit.SECONDS.sleep(1L);
//        System.out.println("都通知完了,等着接吧。");
//        try {
//            for (Future<String> future : futureArrayList) {
//                String returnStr = future.get();
//                System.out.println(returnStr + "，你去接他");
//            }
//            Thread.currentThread().join();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ExecutorCompletionService<String> completionService = new ExecutorCompletionService<>(executorService);
        System.out.println("公司让你通知大家聚餐 你开车去接人");
        completionService.submit(() -> {
            System.out.println("总裁：我在家上大号 我最近拉肚子比较慢 要蹲1个小时才能出来 你等会来接我吧");
            TimeUnit.SECONDS.sleep(10);
            System.out.println("总裁：1小时了 我上完大号了。你来接吧");
            return "总裁上完大号了";
        });
        completionService.submit(() -> {
            System.out.println("研发：我在家上大号 我比较快 要蹲3分钟就可以出来 你等会来接我吧");
            TimeUnit.SECONDS.sleep(3);
            System.out.println("研发：3分钟 我上完大号了。你来接吧");
            return "研发上完大号了";
        });
        completionService.submit(() -> {
            System.out.println("中层管理：我在家上大号  要蹲10分钟就可以出来 你等会来接我吧");
            TimeUnit.SECONDS.sleep(6);
            System.out.println("中层管理：10分钟 我上完大号了。你来接吧");
            return "中层管理上完大号了";
        });
        TimeUnit.SECONDS.sleep(1);
        System.out.println("都通知完了,等着接吧。");
        //提交了3个异步任务）
        for (int i = 0; i < 3; i++) {
            String returnStr = completionService.take().get();
            System.out.println(returnStr + "，你去接他");
        }
        Thread.currentThread().join();
    }

}
