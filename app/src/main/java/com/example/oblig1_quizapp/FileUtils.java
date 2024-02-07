package com.example.oblig1_quizapp;

import android.net.Uri;

import java.io.File;

// made a FileUtils class so i can call the getFileNameFromUri in multipal classes
public class FileUtils {
   public static String getFileNameFromUri(Uri uri) {
        String fileName = null;
        if (uri != null) {
            String path = uri.getPath();
            if (path != null) {
                fileName = new File(path).getName();
            }
            // try to remove the file-name extension
            int extensionIndex = fileName.lastIndexOf(".");
            if (extensionIndex != -1){
                fileName = fileName.substring(0,extensionIndex);
            }
        }
        return fileName != null ? fileName : ""; // return filName if it != null or fileName is not a empty String
    }
}
