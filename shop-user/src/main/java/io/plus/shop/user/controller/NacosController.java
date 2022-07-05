package io.plus.shop.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @author Super_light
 * @date 7/4/22 6:59 PM
 */
@Slf4j
@RestController
public class NacosController {
    @Autowired
    private ConfigurableApplicationContext context;

    @Value("${author.name}")
    private String authorName1;

    @GetMapping("/nacos/test")
    public String nacosTest(){
        String authorName = context.getEnvironment().getProperty("author.name");
        log.info("获取到的作者姓名为：{}", authorName);
        return authorName;
    }

    @GetMapping("/nacos/name")
    public String nacosName(){
        log.info("获取到的作者姓名为：{}", authorName1);
        return authorName1;
    }

}
