package com.example.oblig1_quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class QuizGame extends AppCompatActivity {

    // list of Uri a variable that is used for saveing the content that is sent form gallery intent
    List<Uri> selectedImageUris = new ArrayList<>();

    // ImageView for picture displayed
    ImageView imageView;

    //TextView variable
    TextView correctAnswer;
    TextView attempts;

    // buttons for the questions names
    Button buttonA, buttonB, buttonC;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_game);

        // retrieving the List of Uri form the Gallery send by intent
        selectedImageUris = getIntent().getParcelableArrayListExtra("selectedImageUris");

        //accessing the ImageView in the QuizGame
        imageView = findViewById(R.id.imageViewQuiz);

        //accessing the TextViews in the QuizGame
        correctAnswer = findViewById(R.id.correctAnswer);
        attempts = findViewById(R.id.attempts);

        // accessing the buttons with there id
        buttonA = findViewById(R.id.answerA);
        buttonB = findViewById(R.id.answerB);
        buttonC = findViewById(R.id.answerC);

        if (!selectedImageUris.isEmpty()){
            // make a list where we have control of the random int values so we can shuffle the values
            List<Uri> randomUriList = new ArrayList<>();
            // generate a random-number so we can get a random Uri and display that image
            Random random = new Random();
            int randomIndexA = random.nextInt(selectedImageUris.size()); // choosing a random int from the size range of the list
            int randomIndexB, randomIndexC;


            //make sure that the random number are not equal
           do{
               randomIndexB = random.nextInt(selectedImageUris.size());
           } while (randomIndexB == randomIndexA); // if they are equal retrieve a new random int from the list.

           do {
               randomIndexC = random.nextInt(selectedImageUris.size());
           } while (randomIndexC == randomIndexA || randomIndexC == randomIndexB); // if they are equal retrieve a new random int from the list.

            // get the random Uri from the selectedImageUris list
            Uri randomUriA = selectedImageUris.get(randomIndexA);
            Uri randomUriB = selectedImageUris.get(randomIndexB);
            Uri randomUriC = selectedImageUris.get(randomIndexC);

            // adding the randomIndexes to the new list so we can shuffle them so the buttons has different answer all the time
            randomUriList.add(randomUriA);
            randomUriList.add(randomUriB);
            randomUriList.add(randomUriC);

            // set the random Uri in the imageView
            imageView.setImageURI(randomUriA);

            // us Collection to shuffle the list to make the correct answer appear a different place each time
            Collections.shuffle(randomUriList);

            //set the button values from the indexes of the shuffled random-list
            buttonA.setText(FileUtils.getFileNameFromUri(randomUriList.get(0)));
            buttonB.setText(FileUtils.getFileNameFromUri(randomUriList.get(1)));
            buttonC.setText(FileUtils.getFileNameFromUri(randomUriList.get(2)));


        }




    }
}