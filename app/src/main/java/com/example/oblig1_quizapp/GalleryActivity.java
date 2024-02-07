package com.example.oblig1_quizapp;


import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GalleryActivity extends AppCompatActivity {

    // ImageView variables to store the different Views in.
    ImageView imageView, imageView2, imageView3, imageView4;
    GridView gridView;

    //Buttons
    Button chooseBtn;
    Button playBtn;
    Button sortBtn;

    //resultLauncher
    ActivityResultLauncher<String> resultLauncher;

    // make a ActivityResultContract since startActivityResult() is depricated
    /*ActivityResultContracts.StartActivityForResult contract = new ActivityResultContracts.StartActivityForResult();*/


    // List of images Uris
    List<Uri> selectedImageUris = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        // getting the button and imageView content form the View.
        chooseBtn = findViewById(R.id.buttonChoose);
        playBtn = findViewById(R.id.playBtn);
        sortBtn = findViewById(R.id.sortBtn);

        //accsess the grindView by id
        gridView = findViewById(R.id.gridView);

        //add the Uris that is going to be showed by default to the list of Uris
        selectedImageUris.add(Uri.parse("android.resource://com.example.oblig1_quizapp/drawable/polarbear"));
        selectedImageUris.add(Uri.parse("android.resource://com.example.oblig1_quizapp/drawable/brownbear"));
        selectedImageUris.add(Uri.parse("android.resource://com.example.oblig1_quizapp/drawable/blackbear"));

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
                Log.d("info",getItem(position).toString());

                String imageName = FileUtils.getFileNameFromUri(getItem(position));
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

        // set a item click listener on the GridView we made, so we can remove the image that is clicked
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Uri clicedkUri = selectedImageUris.get(position);
                selectedImageUris.remove(clicedkUri);

                adapter.notifyDataSetChanged();
            }
        });

        // listener for the back-btn when its clicked
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remeber to send the list of URIs to the Quiz activity with the intent to share the recourse
                // *****************************************************************************

            Intent quizListIntent = new Intent(GalleryActivity.this,QuizGame.class);
                quizListIntent.putParcelableArrayListExtra("selectedImageUris", new ArrayList<Uri>(selectedImageUris));
                startActivity(quizListIntent);

            }
        });

        // listener for the sort-btn to be clicked and try to sort the picture list / Uri list by names
        sortBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sortImageUriList(selectedImageUris); // use the sort method to sort the list

                adapter.notifyDataSetChanged(); // update the adapter to change the view to the sorted list


            }
        });
    }

    // retrieve the name of the Uri so it can be displayed under the Image

 /*   private String getFileNameFromUri(Uri uri) {
        String fileName = null;
        if (uri != null) {
            Cursor cursor = getContentResolver().query(uri,null,null,null,null);
            if (cursor != null) {
                if(cursor.moveToFirst()){
                    int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);

                    if(nameIndex != -1){
                        fileName = cursor.getString(nameIndex);
                    }
                }
                cursor.close();
                
            }
            *//*String path = uri.getPath();*//*
           *//* if (path != null) {
                fileName = new File(path).getName();
            }*//*
        }
        return fileName != null ? fileName : ""; // return filName if it != null or fileName is not a empty String
    }*/

    private String getFileNameFromUri(Uri uri) {
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


    // method for picking the image and passing the intent, using the "SAF" ACTION_OPEN_DOCUMENT
    private void pickImage(){

        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");

        resultLauncher.launch("image/*");
        /*startActivityForResult(intent,PICK_PDF_FILE);*/ // deprecated method need to use contracts

    }

    // sort the list of Uri that we have, and compare the items with the use of Comparator
    private void sortImageUriList(List<Uri> selectedImageUris){
        Comparator<Uri> uriComparator = new Comparator<Uri>() {
            @Override
            public int compare(Uri item1, Uri item2) {
                return item1.toString().compareTo(item2.toString()) ; // make it toString() so it compare the names
            }
        };

        selectedImageUris.sort(uriComparator);
    }

}