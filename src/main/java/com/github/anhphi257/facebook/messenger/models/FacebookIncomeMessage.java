package com.github.anhphi257.facebook.messenger.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by phiha on 15/08/2017.
 * Facebook entry json
 */

public class FacebookIncomeMessage {

    @SerializedName("object")
    private String object;
    @SerializedName("entry")
    private List<Entry> entries;

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    public static class Entry {
        @SerializedName("id")
        private String id;
        @SerializedName("time")
        private long time;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public List<Messaging> getMessagings() {
            return messagings;
        }

        public void setMessagings(List<Messaging> messagings) {
            this.messagings = messagings;
        }

        @SerializedName("messaging")
        private List<Messaging> messagings;


        public static class Messaging {

            @SerializedName("sender")
            private Sender sender;
            @SerializedName("recipient")
            private Recipient recipient;
            @SerializedName("message")
            private Message message;
            @SerializedName("timestamp")
            private long timestamp;

            public Sender getSender() {
                return sender;
            }

            public void setSender(Sender sender) {
                this.sender = sender;
            }

            public Recipient getRecipient() {
                return recipient;
            }

            public void setRecipient(Recipient recipient) {
                this.recipient = recipient;
            }

            public Message getMessage() {
                return message;
            }

            public void setMessage(Message message) {
                this.message = message;
            }

            public long getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(long timestamp) {
                this.timestamp = timestamp;
            }

            public Postback getPostback() {
                return postback;
            }

            public void setPostback(Postback postback) {
                this.postback = postback;
            }

            @SerializedName("postback")
            private Postback postback;


            private static class Postback {
                public String getPayload() {
                    return payload;
                }

                public void setPayload(String payload) {
                    this.payload = payload;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                @SerializedName("payload")
                private String payload;
                @SerializedName("title")
                private String title;


                public Postback(String payload, String title) {
                    this.payload = payload;
                    this.title = title;
                }
            }


            public static class Sender {
                @SerializedName("id")
                private String id;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }
            }


            public static class Recipient {
                @SerializedName("id")
                private String id;
            }


            public static class Message {
                @SerializedName("mid")
                private String mid;
                @SerializedName("text")
                private String text;
                @SerializedName("quick_reply")
                private QuickReply quickReply;
                @SerializedName("attachments")
                private List<Attachment> attachments;
                @SerializedName("sticker_id")
                private long stickerId;

                public long getStickerId() {
                    return stickerId;
                }

                public String getMid() {
                    return mid;
                }

                public void setMid(String mid) {
                    this.mid = mid;
                }

                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }

                public QuickReply getQuickReply() {
                    return quickReply;
                }

                public void setQuickReply(QuickReply quickReply) {
                    this.quickReply = quickReply;
                }

                public List<Attachment> getAttachments() {
                    return attachments;
                }

                public void setAttachments(List<Attachment> attachments) {
                    this.attachments = attachments;
                }

                public void setStickerId(long stickerId) {
                    this.stickerId = stickerId;
                }


                public static class Attachment {

                    @SerializedName("type")
                    private String type;
                    @SerializedName("payload")
                    private Payload payload;

                    public String getType() {
                        return type;
                    }

                    public void setType(String type) {
                        this.type = type;
                    }

                    public Payload getPayload() {
                        return payload;
                    }

                    public void setPayload(Payload payload) {
                        this.payload = payload;
                    }

                    public static class Payload {
                        @SerializedName("url")
                        private String url;
                        @SerializedName("sticker_id")
                        private long stickerId;

                        public String getUrl() {
                            return url;
                        }

                        public void setUrl(String url) {
                            this.url = url;
                        }

                        public long getStickerId() {
                            return stickerId;
                        }

                        public void setStickerId(long stickerId) {
                            this.stickerId = stickerId;
                        }
                    }
                }

                public Message(String mid, String text, QuickReply quickReply) {
                    this.mid = mid;
                    this.text = text;
                    this.quickReply = quickReply;
                }


                public static class QuickReply {
                    @SerializedName("payload")
                    private String payload;

                    public String getPayload() {
                        return payload;
                    }

                    public void setPayload(String payload) {
                        this.payload = payload;
                    }


                    public QuickReply(String payload) {
                        this.payload = payload;
                    }

                }
            }
        }

    }

}
