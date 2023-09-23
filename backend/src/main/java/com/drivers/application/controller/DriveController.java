package com.drivers.application.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.drivers.application.services.DriveService;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

@RestController
public class DriveController {
    @Autowired
    DriveService service;

    public void printFiles(List<File> files) {
        if (files == null || files.isEmpty()) {
            System.out.println("No files found.");
        } else {
            System.out.println("Files:");
            int i = 0;
            for (File file : files) {
                System.out.printf("%d %s (%s)\n", i++, file.getName(), file.getId());
            }
        }
    }

    @GetMapping("/drive/getAllFiles")
    public List<File> getAllFiles() {
        List<File> files = new ArrayList<File>();
        try {
            Drive service = DriveService.getInstance();
            FileList result = service.files().list()
                    .setPageSize(1000)
                    .setFields("nextPageToken, files(id, name)")
                    .execute();
            files = result.getFiles();
            return files;
        } catch (IOException e) {
            return files;
        } catch (GeneralSecurityException e) {
            return files;
        }
    }

    @GetMapping("/drive/getBookFolders")
    public List<File> getBookFolders() {
        List<File> files = new ArrayList<File>();
        try {
            Drive service = DriveService.getInstance();
            FileList result = service.files().list()
                    .setQ("mimeType='application/vnd.google-apps.folder'")
                    .setPageSize(1000)
                    .setFields("nextPageToken, files(id, name,webViewLink)")
                    .execute();
            files = result.getFiles();

            return files;
        } catch (IOException e) {
            e.printStackTrace();
            return files;
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return files;
        }
    }

    @GetMapping("/drive/getPdfFiles/{folderid}")
    public List<File> getPdfFiles(@PathVariable String folderid) {

        List<File> files = new ArrayList<File>();
        try {
            Drive service = DriveService.getInstance();
            FileList result = service.files().list()
                    .setQ("mimeType='application/pdf' and parents in '"+folderid+"'")
                    .setPageSize(1000)
                    .setFields("nextPageToken, files(id, name,webViewLink)")
                    .execute();
            files = result.getFiles();

            return files;
        } catch (IOException e) {
            e.printStackTrace();
            return files;
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return files;
        }
    }
}
