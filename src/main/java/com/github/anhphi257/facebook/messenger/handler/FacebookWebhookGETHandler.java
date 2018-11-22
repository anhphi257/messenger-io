package com.github.anhphi257.facebook.messenger.handler;

import com.github.anhphi257.facebook.messenger.Constant;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;


/**
 * Created by phiha on 22/11/2018.
 * Verify GET request from Facebook
 */
public class FacebookWebhookGETHandler implements Handler<RoutingContext> {

    public void handle(RoutingContext context) {
        HttpServerResponse response = context.response();
        HttpServerRequest request = context.request();
        String token = request.getParam("hub.verify_token");
        String challenge = request.getParam("hub.challenge");
        String mode = request.getParam("hub.mode");
        if (mode.equals("subscribe") && token.equals(Constant.Facebook.VERIFY_TOKEN)) {
            response.putHeader("content-type", "application/text");
            response.putHeader("Access-Control-Allow-Origin", "*");
            response.setStatusCode(200);
            response.end(challenge);
        } else {
            response.putHeader("content-type", "application/text");
            response.putHeader("Access-Control-Allow-Origin", "*");
            response.setStatusCode(403);
            response.end();
        }
    }
}
