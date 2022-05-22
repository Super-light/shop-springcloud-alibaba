package io.plus.shop.order.feign;

import io.plus.shop.bean.User;
import io.plus.shop.order.feign.fallback.UserServiceFallBack;
import io.plus.shop.order.feign.fallback.factory.UserServiceFallBackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Description:
 *
 * @author Super_light
 * @date 5/13/22 6:35 PM
 */
@FeignClient(value = "server-user", fallbackFactory = UserServiceFallBackFactory.class)
public interface UserService {
    @GetMapping(value = "/user/get/{uid}")
    User getUser(@PathVariable("uid") Long uid);
}
