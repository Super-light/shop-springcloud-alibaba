package io.plus.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Description:
 *
 * @author Super_light
 * @date 5/11/22 5:34 PM
 */
@Configuration
public class LoadBalanceConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
