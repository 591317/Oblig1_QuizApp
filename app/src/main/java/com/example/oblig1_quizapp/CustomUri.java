package com.example.oblig1_quizapp;

import android.content.ContentResolver;
import android.net.Uri;

public class CustomUri {

    private String uriName;
    private Uri uri;

    public CustomUri(Uri uri, String uriName){
    this.uri = uri;
    this.uriName = uriName;

    }

    public String getUriName() {
        return uriName;
    }

    public void setUriName(String uriName) {
        this.uriName = uriName;
    }

    public static String retriveName (CustomUri uri){
        ContentResolver contentResolver = getContentResolver();
    }
}
