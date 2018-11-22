package com.github.anhphi257.facebook.messenger;

/**
 * Created by phiha on 22/11/2018.
 */
public interface Constant {

    interface Facebook {

        String ACCESS_TOKEN = "PAGE_ACCESS_TOKEN";
        String VERIFY_TOKEN = "VERIFY_TOKEN";
        String SECRET_KEY = "SECRET_KEY";
        String CALLBACK_URL = "https://graph.facebook.com/v2.6/me/messages?access_token=" + ACCESS_TOKEN;
        String ROUTE = "/PATH/TO/YOUR/API";
    }

    interface MessageQueue {
        String TOPIC = "TOPIC";
        String KAFKA_BROKERS = "BROKER1,BROKER2";

        interface PRODUCER {

            String CLIENT_ID = "CLIENT_ID";
        }

        interface CONSUMER {
            String GROUP_ID = "GROUP_ID";
        }
    }
}
