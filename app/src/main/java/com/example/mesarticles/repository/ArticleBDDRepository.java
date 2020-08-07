package com.example.mesarticles.repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.mesarticles.bo.Article;
import com.example.mesarticles.dal.AppDatabase;
import com.example.mesarticles.dal.ArticleDAO;

import java.util.List;

public class ArticleBDDRepository implements IArticleRepository {

    private ArticleDAO articleDAO;

    public ArticleBDDRepository(Context context) {
        AppDatabase maBDD = AppDatabase.getInstance(context);

        articleDAO = maBDD.getArticleDAO();
    }

    @Override
    public void insert(final Article article) {
        new AsyncTask<Article, Void, Void>() {
            @Override
            protected Void doInBackground(Article... articles) {
                articleDAO.insert(articles[0]);
                return null;
            }
        }.execute(article);
    }

    @Override
    public LiveData<List<Article>> get() {
        return articleDAO.get();
    }

    @Override
    public Article get(int id) {
        return articleDAO.get(id);
    }

    @Override
    public void update(Article article) {
        new AsyncTask<Article, Void, Void>() {
            @Override
            protected Void doInBackground(Article... articles) {
                articleDAO.update(articles[0]);
                return null;
            }
        }.execute(article);
    }

    @Override
    public void delete(Article article) {
         new AsyncTask<Article, Void, Void>() {
            @Override
            protected Void doInBackground(Article... articles) {
                articleDAO.delete(articles[0]);
                return null;
            }
        }.execute(article);
    }

    @Override
    public void delete() {
        new AsyncTask<Void,Void,Void>()
        {
        @Override
        protected Void doInBackground(Void... voids) {
            //Insertion dans la base de données.
            articleDAO.delete();
            return null;
        }
    }.execute();
    }
}