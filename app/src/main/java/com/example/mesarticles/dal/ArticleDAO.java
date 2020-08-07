package com.example.mesarticles.dal;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mesarticles.bo.Article;

import java.util.List;

@Dao
public interface ArticleDAO {

    @Insert
    void insert(Article article);

    @Query("SELECT * FROM Article")
    LiveData<List<Article>> get();

    @Query("SELECT * FROM Article WHERE id=:id")
    Article get(int id);

    @Update
    void update(Article article);

    @Delete
    void delete(Article article);

    @Query("DELETE FROM article")
    void delete();

}
