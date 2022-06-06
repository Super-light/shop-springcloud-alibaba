package io.plus.shop.order.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;

/**
 * Description: 定义被Sentinel限流时调用的方法
 *
 * @author Super_light
 * @date 6/2/22 5:10 PM
 */
@Slf4j
public class MyBlockHandler {

    public static String blockHandler(BlockException e){
        log.error("限流了:{}", e);
        return "限流了";
    }
}

