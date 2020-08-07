package com.example.mesarticles.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;

import com.example.mesarticles.R;
import com.example.mesarticles.bo.Article;
import com.example.mesarticles.repository.ArticleBDDRepository;
import com.example.mesarticles.repository.IArticleRepository;
import com.example.mesarticles.view_model.ArticleViewModel;
import com.facebook.stetho.Stetho;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(this);
        setContentView(R.layout.activity_main);

        IArticleRepository repoArticle = new ArticleBDDRepository(this);

        ArticleViewModel viewModel = ViewModelProviders.of(this).get(ArticleViewModel.class);
        LiveData<List<Article>> observateur = viewModel.get();

        observateur.observe(this, new Observer<List<Article>>() {
            @Override
            public void onChanged(List<Article> articles) {
                for(Article article : articles) {
                    Log.i("mesEnvies","Article :" + article);
                }
            }
        });
        Article pomme = repoArticle.get(3);
        repoArticle.delete(pomme);

    }
}