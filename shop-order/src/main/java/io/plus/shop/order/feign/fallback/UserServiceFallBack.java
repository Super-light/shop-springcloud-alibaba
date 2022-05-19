package io.plus.shop.order.feign.fallback;

import io.plus.shop.bean.User;
import io.plus.shop.order.feign.UserService;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author Super_light
 * @date 5/19/22 2:36 PM
 */
@Component
public class UserServiceFallBack implements UserService {
    @Override
    public User getUser(Long uid) {
        User user = new User();
        user.setId(-1L);
        return user;
    }
}
