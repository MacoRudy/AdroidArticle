package com.example.mesarticles.repository;

import androidx.lifecycle.LiveData;

import com.example.mesarticles.bo.Article;

import java.util.List;

/**
 * Interface permettant de mettre en place le design pattern DAO.
 */
public interface IArticleRepository {

    void insert(Article article);

    LiveData<List<Article>> get();

    Article get(int id);

    void update(Article article);

    void delete(Article article);

    void delete();








}
