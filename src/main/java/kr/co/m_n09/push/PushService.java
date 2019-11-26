package kr.co.m_n09.push;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.*;
import kr.co.m_n09.daos.TokenDao;
import kr.co.m_n09.vos.Push;
import kr.co.m_n09.vos.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PushService {

    @Autowired
    private TokenDao tokenDao;

    private static final String SERVER_KEY = "AAAA3mbFxlM:APA91bH7ePjf9A3xL1Ccj3lOWwmxca7Buq0cC-ysImaxeMEDy8Nn0NQpEeqC5ceeB3c36EUMNRXr-VPaw--xBem8UUTO2ih_OSzw33Hyk3i1GNlCfcKHBDJHd5fKbryEi5rISxQSVvrQ";

    private FirebaseApp firebaseApp;

    public PushService() {
        authExplicit(getClass().getClassLoader().getResource("mn09-f13c1-firebase-adminsdk-o62a0-b88b413be0.json").getFile());
    }

    void authExplicit(String jsonPath) {
        try {
            FileInputStream serviceAccount =
                    new FileInputStream(jsonPath);

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                .setDatabaseUrl("https://mn09-f13c1.firebaseio.com")
                    .build();

            try {
                firebaseApp = FirebaseApp.getInstance();
            } catch (IllegalStateException e) {
                firebaseApp = FirebaseApp.initializeApp(options);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void push(Push push) throws Exception {
        List<Token> tokenList = tokenDao.selectAllUsersByServiceId(push.getServiceId());
        List<String> tokenStringList = new ArrayList<String>();
        for (Token token : tokenList) {
            tokenStringList.add(token.getToken());
        }

        MulticastMessage message = MulticastMessage.builder()
                .putData("url", push.getUrl())
                .putData("title", push.getTitle())
                .putData("content", push.getContent())
                .putData("imageUrl", push.getImageUrl())
                .setApnsConfig(ApnsConfig.builder()
                        .setAps(Aps.builder()
                                .setContentAvailable(true)
                                .setMutableContent(true)
                                .setAlert(
                                        ApsAlert.builder()
                                                .setTitle(push.getTitle())
                                                .setBody(push.getContent())
                                                .build()
                                )
                                .build())
                        .setFcmOptions(ApnsFcmOptions.builder()
                                .setImage(push.getImageUrl()).build())
                        .build())
                .addAllTokens(tokenStringList)
                .build();

        BatchResponse response = FirebaseMessaging.getInstance(firebaseApp).sendMulticast(message);
        System.out.println("Push Success " +response.getSuccessCount() + "/" + tokenList.size());
        System.out.println("Image " + push.getImageUrl());
    }

    public void push(Push push, String token) throws Exception {
        Message message = Message.builder()
                .putData("url", push.getUrl())
                .putData("title", push.getTitle())
                .putData("content", push.getContent())
                .putData("imageUrl", push.getImageUrl())
                .setToken(token)
                .setApnsConfig(ApnsConfig.builder()
                        .setAps(Aps.builder()
                                .setContentAvailable(true)
                                .setMutableContent(true)
                                .setAlert(
                                        ApsAlert.builder()
                                                .setTitle(push.getTitle())
                                                .setBody(push.getContent())
                                                .build()
                                )
                                .build())
                        .setFcmOptions(ApnsFcmOptions.builder()
                                .setImage(push.getImageUrl()).build())
                        .build())
                .build();

        FirebaseMessaging.getInstance(firebaseApp).send(message);
        System.out.println("Push Success ");
        System.out.println("Image " + push.getImageUrl());
    }
}
