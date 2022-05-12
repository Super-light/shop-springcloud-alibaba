package io.plus.shop.utils.pwd;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;

/**
 * Description:
 *
 * @author Super_light
 * @date 4/22/22 3:22 PM
 */
public class PasswordUtils {

    public static String base64Encode(String data) {
        if (StringUtils.isEmpty(data)) {
            return "";
        }
        return new String(Base64.encodeBase64(data.getBytes(), true), StandardCharsets.UTF_8);
    }
}
