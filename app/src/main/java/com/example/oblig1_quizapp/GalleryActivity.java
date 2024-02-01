package com.example.oblig1_quizapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Instrumentation;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GalleryActivity extends AppCompatActivity {

    // ImageView variables to store the different Views in.
    ImageView imageView, imageView2, imageView3, imageView4;

    //Buttons
    Button chooseBtn;

    //resultLauncher
    ActivityResultLauncher<Intent> resultLauncher;

    // List of images Uris
    List<Uri> selectedImageUris = new ArrayList<>();

    // variable to keep control on which imageView counter we are in.
    int imageViewCounter = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        // getting the button and imageView content form the View.
        chooseBtn = findViewById(R.id.buttonChoose);

        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);

        // code before SAF
        /*registerResult();*/

        // code before SAF
     /*   resultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result){
                        try {
                            Uri imgUri = result.getData().getData();
                            registerResult(imgUri); // husk å endre registerResult parameter til tom.
                        }catch (Exception e){
                            Toast.makeText(GalleryActivity.this, "No Image selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );*/

        // Initialize ActivityResultLauncher for SAF picker
        resultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result){
                        if (result.getResultCode() == RESULT_OK){
                            Intent data = result.getData();
                            if (data != null){
                                registerResult(data);
                            }
                        }
                    }
                }
        );

        // listener for the choose-btn when its clicked
        chooseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImage();

            }
        });
    }


    // method for picking the image and passing the intent
    private void pickImage(){
        // code before SAF
        /*Intent intent = new Intent(MediaStore.ACTION_PICK_IMAGES);
        resultLauncher.launch(intent);*/

        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        resultLauncher.launch(intent);

    }

    // register the result. how do we know what to use of the different "new ActivityResultContracts.StartActivityForResult()"
    // why do we need a resultLauncher? is this just to make it easier to pass the intent or something?
    // this code is before the use of SAF. whats the difference

   /* private void registerResult(){
        resultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result){

                        try {
                            Uri imgUri = result.getData().getData();
                            setImageInNextView(imgUri);
                        }catch (Exception e){
                            Toast.makeText(GalleryActivity.this, "No Image selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }*/

    private void registerResult(Intent data){
        Uri imageUri = data.getData();
        if (imageUri != null){
            try {
                //save images to a list or device storage ?
                // add method here maybe

                //display image in the correct imageView
                setImageInNextView(imageUri);

            }catch (Exception e){
                Toast.makeText(GalleryActivity.this, "No Image selected", Toast.LENGTH_SHORT).show();
            }
        }

    }


    private void setImageInNextView (Uri imageUri){
        // use the counter to keep track on which ImageView that is going to be filled
        switch (imageViewCounter){
            case 1:
                imageView.setImageURI(imageUri);
                break;
            case 2:
                imageView2.setImageURI(imageUri);
                break;
            case 3:
                imageView3.setImageURI(imageUri);
                break;
            case 4:
                imageView4.setImageURI(imageUri);
                break;
        }

        imageViewCounter ++; // increment the counter for each click

        // reset when the counter is greater then 4, so we start from the first imageViewer again
        if (imageViewCounter > 4){
            imageViewCounter = 1;
        }
    }

}