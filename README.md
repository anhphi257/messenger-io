1. Thiết lập Webhook
    Gồm 2 Handler nhận các POST và GET request từ Facebook (Chung 1 route path).
    a. GET Handler (com.github.anhphi257.facebook.messenger.handler.FacebookWebhookGETHandler)
        Xác minh webhook của mình với Facebook theo VERIFY_TOKEN đã đăng ký:
        - Facebook gửi bản tin với headers:
            hub.verify_token
            hub.challenge
            hub.mode
        - Nếu trường hub.verify_token chính xác phản hồi lại hub.challenge với mã 200
    b. POST Handler (com.github.anhphi257.facebook.messenger.handler.FacebookWebhookPOSTHandler)
        - Nhận các bản tin JSON khi người dùng nhắn đến Page theo định dạng tại https://developers.facebook.com/docs/messenger-platform/reference/webhook-events/ 
        - Để xác minh đúng bản tin này xuất phát từ Facebook, tính toán HMAC_SHA1 của JSON theo SECRET_KEY và so sánh với giá trị của trường X-Hub-Signature tại header.
        - Các bản tin được Producer gửi tới Kafka topic, chia partition theo key là user id.
        
2. Phản hồi
    - Các consumer đọc bản tin từ Queue và xử lý
    - Để phản hồi gửi các bản tin theo định dạng JSON theo định dạng tại https://developers.facebook.com/docs/messenger-platform/send-messages/
    tới "https://graph.facebook.com/v2.6/me/messages?access_token=<ACCESS_TOKEN>";