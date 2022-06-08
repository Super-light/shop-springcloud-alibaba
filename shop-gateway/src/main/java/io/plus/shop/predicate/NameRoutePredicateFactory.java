package io.plus.shop.predicate;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Description: 自定义断言功能
 *
 * @author Super_light
 * @date 6/7/22 5:27 PM
 */
@Component
public class NameRoutePredicateFactory extends AbstractRoutePredicateFactory<NameRoutePredicateConfig> {

    public NameRoutePredicateFactory() {
        super(NameRoutePredicateConfig.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(NameRoutePredicateConfig config) {
        return (serverWebExchange)->{
            String name = serverWebExchange.getRequest().getQueryParams().getFirst("name");
            if (StringUtils.isEmpty(name)){
                name = "";
            }
            return name.equals(config.getName());
        };
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("name");
    }
}

