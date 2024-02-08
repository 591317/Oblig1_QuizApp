package com.example.oblig1_quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class QuizGame extends AppCompatActivity {

    // Counters for the Correct answer and Attempts tried
    int correctAnswerCount = 0;
    int attemptsCount = 0;

    int previousImage = -1;

    // list of Uri a variable that is used for saving the content that is sent form gallery intent
    List<Uri> selectedImageUris = new ArrayList<>();

    // The randomUri that is put in the ImageView and shared outside the updateUi method
    Uri randomUriA;

    // ImageView for picture displayed
    ImageView imageView;

    //TextView variable
    TextView correctAnswer;
    TextView attempts;

    // buttons for the questions names
    Button buttonA, buttonB, buttonC, leaveGameBtn;



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
        leaveGameBtn = findViewById(R.id.leaveGameBtn);

        // calling the updateUI method to display the images from the Uri list
        updateUI(selectedImageUris);


            // add a on click listener to the buttons to check if the text content of the button is correct and then change the color
            buttonA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // retrieving the text value of the button and convert it to a string
                   String buttonTextValue = buttonA.getText().toString();

                    // check if the value is equal to the picture name
                    if (buttonTextValue.equals(FileUtils.getFileNameFromUri(randomUriA))){
                        buttonA.setBackgroundColor(Color.GREEN);
                        correctAnswerCount ++;
                        attemptsCount ++;

                        // set the values of the counters to the textViews to display the number of correct answers and attempts
                        correctAnswer.setText("Correct Answer: " + correctAnswerCount);
                        attempts.setText("Attempts: " + attemptsCount);

                        updateUI(selectedImageUris);
                    }else{
                        buttonA.setBackgroundColor(Color.RED);
                        attemptsCount ++;

                        // set the values of the counters to the textViews to display the number of correct answers and attempts
                        attempts.setText("Attempts: " + attemptsCount);
                    }



                }
            });

        // add a on click listener to the buttons to check if the text content of the button is correct and then change the color
            buttonB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // retrieving the text value of the button and convert it to a string
                    String buttonTextValue = buttonB.getText().toString();

                    // check if the value is equal to the picture name
                    if (buttonTextValue.equals(FileUtils.getFileNameFromUri(randomUriA))){
                        buttonB.setBackgroundColor(Color.GREEN);
                        correctAnswerCount ++;
                        attemptsCount ++;

                        // set the values of the counters to the textViews to display the number of correct answers and attempts
                        correctAnswer.setText("Correct Answer: " + correctAnswerCount);
                        attempts.setText("Attempts: " + attemptsCount);

                        updateUI(selectedImageUris);
                    }else{
                        buttonB.setBackgroundColor(Color.RED);
                        attemptsCount ++;

                        // set the values of the counters to the textViews to display the number of correct answers and attempts
                        attempts.setText("Attempts: " + attemptsCount);
                    }



                }
            });

        // add a on click listener to the buttons to check if the text content of the button is correct and then change the color
            buttonC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // retrieving the text value of the button and convert it to a string
                    String buttonTextValue = buttonC.getText().toString();

                    // check if the value is equal to the picture name
                    if (buttonTextValue.equals(FileUtils.getFileNameFromUri(randomUriA))){
                        buttonC.setBackgroundColor(Color.GREEN);
                        correctAnswerCount ++;
                        attemptsCount ++;

                        // set the values of the counters to the textViews to display the number of correct answers and attempts
                        correctAnswer.setText("Correct Answer: " + correctAnswerCount);
                        attempts.setText("Attempts: " + attemptsCount);

                        updateUI(selectedImageUris);

                    }else{
                        buttonC.setBackgroundColor(Color.RED);
                        attemptsCount ++;

                        // set the values of the counters to the textViews to display the number of correct answers and attempts
                        attempts.setText("Attempts: " + attemptsCount);
                    }



                }
            });

            // add a leave game button so we can exit the loop of questions
            leaveGameBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(QuizGame.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });




        }

    // method to call for updating the ImageView and the buttons when correct answers is clicked
    public void updateUI(List<Uri> selectedImageUris){

        if (!selectedImageUris.isEmpty()){

            // make a list where we have control of the random int values so we can shuffle the values
            List<Uri> randomUriList = new ArrayList<>();

            // generate a random-number so we can get a random Uri and display that image
            Random random = new Random();

            int randomIndexA, randomIndexB, randomIndexC;

            do{
                randomIndexA = random.nextInt(selectedImageUris.size()); // choosing a random int from the size range of the list
            } while (randomIndexA == previousImage); // if they are equal retrieve a new random int from the list

            previousImage = randomIndexA;

            //make sure that the random number are not equal
            do{
                randomIndexB = random.nextInt(selectedImageUris.size()); // choosing a random int from the size range of the list
            } while (randomIndexB == randomIndexA); // if they are equal retrieve a new random int from the list.

            do {
                randomIndexC = random.nextInt(selectedImageUris.size()); // choosing a random int from the size range of the list
            } while (randomIndexC == randomIndexA || randomIndexC == randomIndexB); // if they are equal retrieve a new random int from the list.


            // get the random Uri from the selectedImageUris list
            randomUriA = selectedImageUris.get(randomIndexA);
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

            // added a handler with delay so you can se that you have clicked the correct answer before resetting the button colors
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    // reset the button colors when the method is loaded in again
                    resetButtonColor();
                }
            }, 300);
    }
}

// method to reset the button colors when we load in a new image
 public void resetButtonColor(){
        buttonA.setBackgroundColor(Color.rgb(106, 13, 174));
        buttonB.setBackgroundColor(Color.rgb(106, 13, 174));
        buttonC.setBackgroundColor(Color.rgb(106, 13, 174));
 }

}