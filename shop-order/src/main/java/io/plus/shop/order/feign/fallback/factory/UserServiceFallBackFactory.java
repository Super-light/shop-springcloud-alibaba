package io.plus.shop.order.feign.fallback.factory;

import feign.hystrix.FallbackFactory;
import io.plus.shop.bean.User;
import io.plus.shop.order.feign.UserService;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author Super_light
 * @date 5/19/22 6:33 PM
 */
@Component
public class UserServiceFallBackFactory implements FallbackFactory<UserService> {
    @Override
    public UserService create(Throwable cause) {
        return new UserService() {
            @Override
            public User getUser(Long uid) {
                User user = new User();
                user.setId(-1L);
                return user;
            }
        };
    }
}
