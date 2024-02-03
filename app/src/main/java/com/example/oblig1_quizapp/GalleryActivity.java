package com.example.oblig1_quizapp;


import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GalleryActivity extends AppCompatActivity {

    // ImageView variables to store the different Views in.
    ImageView imageView, imageView2, imageView3, imageView4;
    GridView gridView;

    //Buttons
    Button chooseBtn;

    //resultLauncher
    ActivityResultLauncher<String> resultLauncher;

    // make a ActivityResultContract since startActivityResult() is depricated
    /*ActivityResultContracts.StartActivityForResult contract = new ActivityResultContracts.StartActivityForResult();*/


    // List of images Uris
    List<Uri> selectedImageUris = new ArrayList<>();

    // variable to keep control on which imageView counter we are in.
    private static int imageViewCounter = 1;

    private static final int PICK_PDF_FILE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        // getting the button and imageView content form the View.
        chooseBtn = findViewById(R.id.buttonChoose);

      /*  imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);*/


        gridView = findViewById(R.id.gridView);

        //add the Uris that is going to be showed by default to the list of Uris
        selectedImageUris.add(Uri.parse("android.resource://com.example.oblig1_quizapp/drawable/blackbear"));
        selectedImageUris.add(Uri.parse("android.resource://com.example.oblig1_quizapp/drawable/brownbear"));
        selectedImageUris.add(Uri.parse("android.resource://com.example.oblig1_quizapp/drawable/polarbear"));

        // Use a ArrayAdapter that use the list of Uris to display the content how we designed it in grind_iteam.
        ArrayAdapter<Uri> adapter = new ArrayAdapter<Uri>(this,R.layout.grid_iteam, selectedImageUris){
            @Override
            public View getView (int position, View convertView, ViewGroup parent){
                if (convertView == null){
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.grid_iteam, parent, false);

                }

                ImageView imageView = convertView.findViewById(R.id.animalImageView);
                TextView textView = convertView.findViewById(R.id.animalText);

                imageView.setImageURI(getItem(position));

                String imageName = getFileNameFromUri(getItem(position));
                textView.setText(imageName);

                return convertView;
            }
        };


        // make the gridView use the adapter
        gridView.setAdapter(adapter);


        // capture the result for the Activity of choosing a picture, the ACTION_OPEN_DOCUMENT
        resultLauncher = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        if (result != null){
                            //add the selected Uri to the Uri-image list
                            selectedImageUris.add(result);

                            // update the adapter to reflect changes
                            adapter.notifyDataSetChanged();
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

    // retrieve the name of the Uri so it can be displayed under the Image
    private String getFileNameFromUri(Uri uri) {
        String fileName = null;
        if (uri != null) {
            String path = uri.getPath();
            if (path != null) {
                fileName = new File(path).getName();
            }
        }
        return fileName != null ? fileName : ""; // return filName if it != null or fileName is not a empty String
    }


    // method for picking the image and passing the intent, using the "SAF" ACTION_OPEN_DOCUMENT
    private void pickImage(){

        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        resultLauncher.launch("image/*");
        /*startActivityForResult(intent,PICK_PDF_FILE);*/ // deprecated method need to use contracts

    }

}