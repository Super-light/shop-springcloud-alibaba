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
 * @date 5/11/22 5:26 PM
 */
@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
@MapperScan(value = { "io.plus.shop.mapper" })
@EnableDiscoveryClient
public class OrderStarter {
    public static void main(String[] args){
        SpringApplication.run(OrderStarter.class, args);
    }
}
