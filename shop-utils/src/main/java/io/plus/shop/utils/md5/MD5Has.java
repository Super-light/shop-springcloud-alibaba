package io.plus.shop.utils.md5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Description:
 *
 * @author Super_light
 * @date 4/22/22 3:22 PM
 */
public class MD5Has {
    private static final char[] DIGITS = {'0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String md5(String content) {
        byte[] data = getMD5Digest().digest(content.getBytes());
        char[] chars = encodeHex(data);
        return new String(chars);
    }

    private static MessageDigest getMD5Digest() {
        try {
            MessageDigest md5MessageDigest = MessageDigest.getInstance("MD5");
            md5MessageDigest.reset();
            return md5MessageDigest;
        } catch (NoSuchAlgorithmException nsaex) {
            throw new RuntimeException(
                    "Could not access MD5 algorithm, fatal error");
        }
    }

    private static char[] encodeHex(byte[] data) {
        int l = data.length;
        char[] out = new char[l << 1];
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = DIGITS[(0xF0 & data[i]) >>> 4];
            out[j++] = DIGITS[0x0F & data[i]];
        }
        return out;
    }
}
