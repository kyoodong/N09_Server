package kr.co.m_n09.popup;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.BatchResponse;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.MulticastMessage;
import com.google.firebase.messaging.Notification;
import kr.co.m_n09.daos.TokenDao;
import kr.co.m_n09.vos.Push;
import kr.co.m_n09.vos.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
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
                .setNotification(
                        new Notification(
                                push.getTitle(),
                                push.getContent()
                        )
                )
                .addAllTokens(tokenStringList)
                .build();

        BatchResponse response = FirebaseMessaging.getInstance(firebaseApp).sendMulticast(message);
        System.out.println(response);
    }
}
