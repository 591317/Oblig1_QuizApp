package com.example.oblig1_quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //Buttons
    Button galleryBtn;
    Button quizBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // accessing the buttons in the MainActivity
        galleryBtn = findViewById(R.id.buttonGallery);
        quizBtn = findViewById(R.id.buttonQuiz);

        galleryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // add what the button should do when clicked
                openGallery(view);

            }
        });

        quizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // add what the button should do when clicked
                openQuiz(view);
            }
        });


    }

    public void openGallery(View v){
        Intent intent = new Intent(MainActivity.this, GalleryActivity.class);
        startActivity(intent);
    }

    public void openQuiz(View view){
        Intent intent = new Intent(MainActivity.this, QuizGame.class);
        startActivity(intent);
    }
}