package io.plus.shop.user.service.api;

import io.plus.shop.bean.User;

/**
 * Description:
 *
 * @author Super_light
 * @date 5/11/22 10:46 AM
 */
public interface UserService {

    /**
     * 根据id获取用户信息
     */
    User getUserById(Long userId);

    void asyncMethod();
}
