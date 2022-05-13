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
 * @date 5/11/22 10:48 AM
 */
@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
@MapperScan(value = { "io.plus.shop.user.mapper" })
@EnableDiscoveryClient
public class UserStarter {

    public static void main(String[] args){
        SpringApplication.run(UserStarter.class, args);
    }
}
