package com.github.anhphi257.facebook.messenger.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

/**
 * Created by datluyen on 20/05/2017.
 */
public class GsonProvider {

    private static Gson gson =  new GsonBuilder().create();
    private static JsonParser jsonParser = new JsonParser();

    public static Gson getGson(){
        return gson;
    }

    public static JsonParser getJsonParser(){
        return jsonParser;
    }

}
