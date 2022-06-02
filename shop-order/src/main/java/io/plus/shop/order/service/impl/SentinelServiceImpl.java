package io.plus.shop.order.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import io.plus.shop.order.service.api.SentinelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Description:
 *
 * @author Super_light
 * @date 5/23/22 11:56 AM
 */
@Service
@Slf4j
public class SentinelServiceImpl implements SentinelService {
    @Override
    @SentinelResource("sendMessage")
    public void sendMessage() {
        System.out.println("测试Sentinel的链路流控模式");
    }

    private int count = 0;
    @Override
    @SentinelResource(
            value = "sendMessage2",
            blockHandler = "blockHandler",
            fallback = "fallback")
    public String sendMessage2() {
        count ++;
        //25%的异常率
        if (count % 4 == 0){
            throw new RuntimeException("25%的异常率");
        }
        return "sendMessage2";
    }

    public String blockHandler(BlockException e){
        log.error("限流了:{}", e);
        return "限流了";
    }

    public String fallback(Throwable e){
        log.error("异常了:{}", e);
        return "异常了";
    }

}

