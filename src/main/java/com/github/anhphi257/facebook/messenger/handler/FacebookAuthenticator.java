package com.github.anhphi257.facebook.messenger.handler;

import com.github.anhphi257.facebook.messenger.Config;
import com.github.anhphi257.facebook.messenger.utils.CryptoUtils;
import io.vertx.core.MultiMap;

import java.util.Objects;

/**
 * Created by phiha on 22/11/2018.
 */
public class FacebookAuthenticator   {


    public static boolean authenticate(String data, MultiMap headers) {
        String signature = headers.get("X-Hub-Signature");
        String hmac = CryptoUtils.hmacSha1(Config.Facebook.SECRET_KEY, data);
        return Objects.equals("sha1=" + hmac, signature);
    }
}
