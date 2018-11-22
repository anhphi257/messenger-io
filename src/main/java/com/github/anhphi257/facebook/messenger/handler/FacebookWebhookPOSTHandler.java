package com.github.anhphi257.facebook.messenger.handler;

import com.github.anhphi257.facebook.messenger.Config;
import com.github.anhphi257.facebook.messenger.models.FacebookIncomeMessage;
import com.github.anhphi257.facebook.messenger.mq.MessageProducer;
import com.github.anhphi257.facebook.messenger.utils.GsonUtils;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;

import java.nio.charset.Charset;
import java.util.concurrent.ExecutionException;


/**
 * Created by phiha on 22/11/2018.
 */
public class FacebookWebhookPOSTHandler implements Handler<RoutingContext> {
    private MessageProducer producer;

    public void handle(RoutingContext context) {
        HttpServerRequest request = context.request();
        HttpServerResponse response = context.response();

        request.bodyHandler(buffer -> {

            String json = buffer.toString(Charset.forName("UTF-8"));
            MultiMap headers = request.headers();

            if (FacebookAuthenticator.authenticate(json, headers)) {

                FacebookIncomeMessage facebookIncomeMessage = GsonUtils.parse(json, FacebookIncomeMessage.class);
                String userId = facebookIncomeMessage.getEntries().get(0).getMessagings().get(0).getSender().getId();
                try {
                    producer.send(Config.MessageQueue.TOPIC, userId, json);
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
                response.setStatusCode(200);
                response.end();
            } else {
                response.setStatusCode(403);
                response.end();
            }
        });
    }
}
