package io.plus.shop.user.service.impl;

import io.plus.shop.bean.User;
import io.plus.shop.user.mapper.UserMapper;
import io.plus.shop.user.service.api.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Description:
 *
 * @author Super_light
 * @date 5/11/22 10:46 AM
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(Long userId) {
        return userMapper.selectById(userId);
    }

    /*@Async注解开启任务*/
    @Async
    @Override
    public void asyncMethod() {
        log.info("执行了异步任务...");
    }

}
