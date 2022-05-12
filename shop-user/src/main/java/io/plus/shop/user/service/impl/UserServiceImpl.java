package io.plus.shop.user.service.impl;

import io.plus.shop.bean.User;
import io.plus.shop.user.mapper.UserMapper;
import io.plus.shop.user.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description:
 *
 * @author Super_light
 * @date 5/11/22 10:46 AM
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(Long userId) {
        return userMapper.selectById(userId);
    }
}
