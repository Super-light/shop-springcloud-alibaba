package io.plus.shop.params;

import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * Description:
 *
 * @author Super_light
 * @date 5/11/22 5:31 PM
 */
@Data
public class OrderParams {
    public Integer count;
    private Long userId;
    private Long productId;

    public Boolean isEmpty() {
        if (count <= 0)
            return true;
        if (StringUtils.isEmpty(userId.toString()))
            return true;
        if (StringUtils.isEmpty(productId.toString()))
            return true;
        return false;
    }
}
