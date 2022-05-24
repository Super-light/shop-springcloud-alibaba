package io.plus.shop.order.controller;

import io.plus.shop.order.service.api.SentinelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @author Super_light
 * @date 5/23/22 11:58 AM
 */
@Slf4j
@RestController
public class SentinelController {

    @Autowired
    private SentinelService sentinelService;

    @GetMapping(value = "/request_sentinel1")
    public String requestSentinel1(){
        log.info("测试Sentinel1");
        sentinelService.sendMessage();
        return "sentinel1";
    }
    @GetMapping(value = "/request_sentinel2")
    public String requestSentinel2(){
        log.info("测试Sentinel2");
        sentinelService.sendMessage();
        return "sentinel2";
    }
}

