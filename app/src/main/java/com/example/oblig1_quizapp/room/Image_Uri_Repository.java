package com.example.oblig1_quizapp.room;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Image_Uri_Repository {

    private Image_Uri_Dao mImageUriDao;
    private LiveData<List<ImageAndUri_Entity>> mAllImageUris;

    public Image_Uri_Repository(Application application){
        Image_Uri_RoomDatabase db = Image_Uri_RoomDatabase.getDatabase(application);
        mImageUriDao = db.Image_Uri_Dao();
        mAllImageUris = mImageUriDao.getAllUriandNames();
    }

    LiveData<List<ImageAndUri_Entity>> getAllImageUris() {

        return mAllImageUris;
    }

    public void insert(ImageAndUri_Entity imageAndUri) {

        // need to make a new thread to do this database operation since main thread is used to run the app
        new Thread(() -> mImageUriDao.insert(imageAndUri)).start();
    }

    public void deleteImage(ImageAndUri_Entity image){

        // need to make a new thread to do this database operation since main thread is used to run the app
       new Thread(() -> mImageUriDao.deleteImage(image.getId())).start();
    }
}
