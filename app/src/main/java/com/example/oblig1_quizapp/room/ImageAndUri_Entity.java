package com.example.oblig1_quizapp.room;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "imageAnduri_table")
public class ImageAndUri_Entity {

    @PrimaryKey(autoGenerate = true)
    @NotNull
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "uri")
    private String uri;

    @ColumnInfo(name = "name")
    private String uri_name;



    public ImageAndUri_Entity(String uri, String uri_name){
        this.uri = uri;
        this.uri_name = uri_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getUri_name() {
        return uri_name;
    }

    public void setUri_name(String uri_name) {
        this.uri_name = uri_name;
    }

}
