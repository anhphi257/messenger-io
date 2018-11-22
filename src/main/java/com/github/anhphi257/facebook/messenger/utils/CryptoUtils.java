package com.github.anhphi257.facebook.messenger.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.MessageDigest;


/**
 * Created by phiha on 21/10/2017.
 */
public class CryptoUtils {

    public static String hmacSha1(String key, String data) {
        String HMAC_SHA1_ALGORITHM = "HmacSHA1";
        String result = null;

        try {
            Key signingKey = new SecretKeySpec(key.getBytes(), HMAC_SHA1_ALGORITHM);
            Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(data.getBytes());
            result = byteArrayToHexString(rawHmac);
        } catch (Exception e) {
        }
        return result;
    }

    public static String md5(String data) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(data.getBytes("utf-8"));
            return byteArrayToHexString(m.digest());
        } catch (Exception e) {
            return null;
        }
    }

    public static String base64Decode(String s) {
        return StringUtils.newStringUtf8(Base64.decodeBase64(s));
    }

    public static String base64Encode(String s) {
        return Base64.encodeBase64String(StringUtils.getBytesUtf8(s));
    }

    public static String byteArrayToHexString(byte[] b) {
        String result = "";
        for (int i = 0; i < b.length; i++) {
            result +=
                    Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
        }
        return result;
    }

}
