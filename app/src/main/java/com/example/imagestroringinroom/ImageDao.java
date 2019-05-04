package com.example.imagestroringinroom;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ImageDao {

    @Query("Select * from imageinfo")
    LiveData<List<ImageInfo>> getAllFavs();

    @Insert
    void addToFavs(ImageInfo param);

    @Delete
    void delFromFav(ImageInfo param);
}
