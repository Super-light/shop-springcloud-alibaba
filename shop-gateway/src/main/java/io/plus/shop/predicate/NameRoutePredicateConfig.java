package io.plus.shop.predicate;

import lombok.Data;

import java.io.Serializable;

/**
 * Description: 接收配置文件中的参数
 *
 * @author Super_light
 * @date 6/7/22 5:18 PM
 */
@Data
public class NameRoutePredicateConfig implements Serializable {
    private static final long serialVersionUID = -3289515863427972825L;
    private String name;
}

