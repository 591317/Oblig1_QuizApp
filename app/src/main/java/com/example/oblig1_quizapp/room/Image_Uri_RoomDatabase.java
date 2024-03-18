package com.example.oblig1_quizapp.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ImageAndUri_Entity.class}, version = 1, exportSchema = false)
/*@TypeConverters({Converters.class})*/
public abstract class Image_Uri_RoomDatabase extends RoomDatabase {

    public abstract Image_Uri_Dao Image_Uri_Dao();

    // make a instance of the database, so that we can check if we have one before asking for data
    private static volatile Image_Uri_RoomDatabase INSTANCE;

    // is used when we have multiple clients that is asking to do different operations. use this to limit number of operation allowed to do at once
   /* private static final int NUMBER_OF_THREADS = 4;*/

   /* static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);*/

    // check that if the instance is null, if so we create a database instance.
    static Image_Uri_RoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (Image_Uri_RoomDatabase.class) {
                // have this check for thread safety so that not multi threads create a database at the same time
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    Image_Uri_RoomDatabase.class, "imageAnduri_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
