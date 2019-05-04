package com.example.imagestroringinroom;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class ImageRepository
{
    private ImageDao mMealDao;
    private LiveData<List<ImageInfo>> mAllMeals;

    public ImageRepository(Application application) {
        ImageDatabase database = ImageDatabase.getDatabase(application);
        mMealDao = database.imageDao();
        mAllMeals = mMealDao.getAllFavs();
    }
    public LiveData<List<ImageInfo>> getmAllMeals() {
        return mAllMeals;
    }
    void insert(ImageInfo word) {
        new insertAsyncTask(mMealDao).execute(word);
    }
    void delete(ImageInfo word) {
        new deleteAsyncTask(mMealDao).execute(word);
    }


    private static class insertAsyncTask extends AsyncTask<ImageInfo, Void, Void> {

        private ImageDao mAsyncTaskDao;

        insertAsyncTask(ImageDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final ImageInfo... params) {
            mAsyncTaskDao.addToFavs(params[0]);
            return null;
        }
    }
    private static class deleteAsyncTask extends AsyncTask<ImageInfo, Void, Void> {

        private ImageDao mAsyncTaskDao;

        deleteAsyncTask(ImageDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final ImageInfo... params) {
            mAsyncTaskDao.delFromFav(params[0]);
            return null;
        }
    }
}
