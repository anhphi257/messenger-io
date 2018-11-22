package com.github.anhphi257.facebook.messenger.utils;

import com.google.gson.Gson;

/**
 * Created by phiha on 01/10/2018.
 */
public class GsonUtils {

    public static <T> T parse(String json, Class<T> classOfT) {
        Gson gson = GsonProvider.getGson();
        return gson.fromJson(json, classOfT);
    }

    public static String toJson(Object object) {
        Gson gson = GsonProvider.getGson();
        return gson.toJson(object);
    }
}
