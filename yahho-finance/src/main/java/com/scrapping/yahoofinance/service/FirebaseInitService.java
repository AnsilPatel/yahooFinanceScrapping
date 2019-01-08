package com.scrapping.yahoofinance.service;

import java.io.FileInputStream;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

@Service
public class FirebaseInitService {
	@PostConstruct
	private void fireBaseInit() throws IOException {
		FileInputStream serviceAccount = new FileInputStream(
				"your fireBase json file.json");
		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.setDatabaseUrl("firebase database url").build();
		FirebaseApp.initializeApp(options);
	}

	public DatabaseReference getDataBaseRef() {
		return FirebaseDatabase.getInstance().getReference();

	}

}
