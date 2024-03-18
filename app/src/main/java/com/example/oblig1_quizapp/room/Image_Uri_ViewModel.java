package com.example.oblig1_quizapp.room;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class Image_Uri_ViewModel extends AndroidViewModel {

    private Image_Uri_Repository mImageUriRepository;

    private final LiveData<List<ImageAndUri_Entity>> mAllImageUris;


    public Image_Uri_ViewModel(Application application) {
        super(application);
        mImageUriRepository = new Image_Uri_Repository(application);
        mAllImageUris = mImageUriRepository.getAllImageUris();
    }

    public LiveData<List<ImageAndUri_Entity>> getAllImageUris(){
        return mAllImageUris;
    }

    public void insert (ImageAndUri_Entity imageAndUri){
        mImageUriRepository.insert(imageAndUri);
    }

    public void deleteImage(ImageAndUri_Entity imageAndUri){
        mImageUriRepository.deleteImage(imageAndUri);
    }
}
