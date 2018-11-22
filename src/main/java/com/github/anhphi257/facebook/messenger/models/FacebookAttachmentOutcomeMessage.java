package com.github.anhphi257.facebook.messenger.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by phiha on 19/08/2017.
 */
public class FacebookAttachmentOutcomeMessage extends FacebookOutcomeMessage {
    @SerializedName("message")
    private Message message;

    public FacebookAttachmentOutcomeMessage() {
        super();
        message = new Message();
    }


    static class Message {
        @SerializedName("attachment")
        private Attachment attachment;

        public Message() {
            attachment = new Attachment();
        }

    }

    static class Attachment {
        @SerializedName("type")
        private String type;
        @SerializedName("payload")
        private Payload payload;

        public Attachment() {
            type = "template";
            payload = new Payload();
        }


        static class Payload {

            @SerializedName("template_type")
            private String templateType;
            @SerializedName("elements")
            private List<Element> elements;

            @Expose
            private static int LIMIT_ELEMENTS = 10;

            public Payload() {
                templateType = "generic";
                elements = new ArrayList<>();
            }

            public void addElement(String title, String imgURL, String subtitle, List<Button> buttons) {
                Element element = new Element();
                element.setTitle(title);
                element.setSubtitle(subtitle);
                element.setImageURL(imgURL);
                element.setButtons(buttons);
                if (elements.size() < LIMIT_ELEMENTS)
                    elements.add(element);
            }

            static class Element {
                @SerializedName("title")
                private String title;
                @SerializedName("image_url")
                private String imageURL;
                @SerializedName("subtitle")
                private String subtitle;
                @SerializedName("buttons")
                private List<Button> buttons;

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getImageURL() {
                    return imageURL;
                }

                public void setImageURL(String imageURL) {
                    this.imageURL = imageURL;
                }

                public String getSubtitle() {
                    return subtitle;
                }

                public void setSubtitle(String subtitle) {
                    this.subtitle = subtitle;
                }

                public List<Button> getButtons() {
                    return buttons;
                }

                public void setButtons(List<Button> buttons) {
                    this.buttons = buttons;
                }

            }

            public class Button {
                @SerializedName("type")
                private String type = "postback";
                @SerializedName("title")
                private String title;
                @SerializedName("payload")
                private String payload;

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

}
