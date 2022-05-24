package io.plus.shop.order.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import io.plus.shop.order.service.api.SentinelService;
import org.springframework.stereotype.Service;

/**
 * Description:
 *
 * @author Super_light
 * @date 5/23/22 11:56 AM
 */
@Service
public class SentinelServiceImpl implements SentinelService {
    @Override
    @SentinelResource("sendMessage")
    public void sendMessage() {
        System.out.println("测试Sentinel的链路流控模式");
    }
}

