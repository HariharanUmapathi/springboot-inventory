package com.drivers.application.services;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;

@Component
public class DriveService {
    // Default Constructor Exception Handling
    DriveService() throws GeneralSecurityException, IOException {

    }
    /**
     * Application name.
     */
    private static final String APPLICATION_NAME = "YourApplicationName";
    /**
     * Global instance of the JSON factory.
     */
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE_FILE);
    private static final String CREDENTIALS_FILE_PATH = "./credentials.json";
    final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
    private static final String SERVICE_ACCOUNT_EMAIL = "yourserviceaccountmail@projectid.iam.gserviceaccount.com";

    
    //Creating Drive Service Instance with Service Account 

    public static Drive getInstance() throws IOException, GeneralSecurityException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        FileInputStream credentialJson = new FileInputStream(CREDENTIALS_FILE_PATH);
        GoogleCredentials credentials = GoogleCredentials
                .fromStream(credentialJson)
                .createScoped(SCOPES)
                .createDelegated(SERVICE_ACCOUNT_EMAIL);
        credentials.refreshIfExpired();
        HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(credentials);
        Drive service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, requestInitializer)
                .setApplicationName(APPLICATION_NAME)
                .build();
        return service;

    }

}
