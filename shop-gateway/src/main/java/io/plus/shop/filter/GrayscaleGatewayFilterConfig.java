package io.plus.shop.filter;

import lombok.Data;

import java.io.Serializable;

/**
 * Description: 接收配置参数
 *
 * @author Super_light
 * @date 6/8/22 3:31 PM
 */
@Data
public class GrayscaleGatewayFilterConfig implements Serializable {
    private static final long serialVersionUID = 983019309000445082L;
    private boolean grayscale;
}

