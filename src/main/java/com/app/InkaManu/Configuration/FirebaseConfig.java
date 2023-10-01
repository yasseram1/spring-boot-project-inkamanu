package com.app.InkaManu.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Configuration
public class FirebaseConfig {

        @Bean
        public void firebaseStorage() throws IOException {
                FileInputStream serviceAccount = new FileInputStream(
                                "./credentials.json");

                FirebaseOptions options = new FirebaseOptions.Builder()
                                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                                .setStorageBucket("inkamanu-springboot.appspot.com")
                                .build();

                FirebaseApp.initializeApp(options);
        }

}
