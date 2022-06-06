package io.plus.shop.order.handler;

import lombok.extern.slf4j.Slf4j;

/**
 * Description: 定义异常时调用的方法
 *
 * @author Super_light
 * @date 6/2/22 5:11 PM
 */
@Slf4j
public class MyFallback {

    public static String fallback(Throwable e){
        log.error("异常了:{}", e);
        return "异常了";
    }
}

