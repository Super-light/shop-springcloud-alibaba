package io.plus.shop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Description:
 *
 * @author Super_light
 * @date 5/11/22 5:02 PM
 */
@SpringBootApplication
@MapperScan(value = { "io.plus.shop.product.mapper" })
@EnableTransactionManagement(proxyTargetClass = true)
@EnableDiscoveryClient
public class ProductStarter {

    public static void main(String[] args){
        SpringApplication.run(ProductStarter.class, args);
    }
}
