package com.github.anhphi257.facebook.messenger.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by phiha on 22/11/2018.
 */
public class FacebookOutcomeMessage {

    public static class Recipient {
        @SerializedName("id")
        private String userID;

        public Recipient() {

        }

        public Recipient(String userID) {
            this.userID = userID;
        }

        public String getUserID() {
            return userID;
        }

        public void setUserID(String userID) {
            this.userID = userID;
        }
    }
}
