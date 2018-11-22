package com.github.anhphi257.facebook.messenger;

import com.github.anhphi257.facebook.messenger.handler.FacebookWebhookGETHandler;
import com.github.anhphi257.facebook.messenger.handler.FacebookWebhookPOSTHandler;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;

public class FacebookWebhookVerticle extends AbstractVerticle {


    @Override
    public void start() throws Exception {
        Router router = Router.router(vertx);
        router.post(Config.Webhook.ROUTE).handler(new FacebookWebhookGETHandler());
        router.post(Config.Webhook.ROUTE).handler(new FacebookWebhookPOSTHandler());
        vertx.createHttpServer().requestHandler(router::accept).listen(Config.Webhook.PORT);
    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        DeploymentOptions deploymentOptions = new DeploymentOptions();
        deploymentOptions.setWorker(true);
        vertx.deployVerticle(new FacebookWebhookVerticle(), deploymentOptions);
    }
}
