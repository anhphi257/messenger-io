package com.github.anhphi257.facebook.messenger.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by phiha on 19/08/2017.
 */
public class FacebookTextOutcomeMessage extends FacebookOutcomeMessage {

    @SerializedName("message")
    protected Message message;

    public void disableQuickReply() {
        message.setReplies(null);
    }



    public void setText(String text) {
        this.message.setText(text);
    }

    public void addReply(String contentType, String title, String payload) {
        Message.Reply reply = new Message.Reply(contentType, title, payload);
        this.message.addReply(reply);
    }


    static class Message {
        private static int LIMIT_REPLIES = 11;
        @SerializedName("text")
        protected String text;
        @SerializedName("quick_replies")
        protected List<Reply> replies;

        public Message() {
        }

        public Message(String text, List<Reply> replies) {
            this.text = text;
            this.replies = replies;
        }

        public void setReplies(List<Reply> replies) {
            this.replies = replies;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public List<Reply> getReplies() {
            return replies;
        }


        public void addReply(Reply reply) {
            if (replies == null) {
                replies = new ArrayList<>();
            }
            if (replies.size() < LIMIT_REPLIES) {
                replies.add(reply);
            }
        }

        public static class Reply {
            @SerializedName("content_type")
            protected String type;
            @SerializedName("title")
            protected String title;
            @SerializedName("payload")
            protected String payload;

            public Reply(String type, String title, String payload) {
                this.type = type;
                this.title = title;
                this.payload = payload;
            }

            public Reply() {
                this.type = "text";
            }


            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getPayload() {
                return payload;
            }

            public void setPayload(String payload) {
                this.payload = payload;
            }
        }
    }


}
