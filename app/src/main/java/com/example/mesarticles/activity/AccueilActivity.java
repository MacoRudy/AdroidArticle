package com.example.mesarticles.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mesarticles.R;
import com.facebook.stetho.Stetho;

public class AccueilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        Stetho.initializeWithDefaults(this);
    }

    public void onClickAjout(View view) {
        Intent intent = new Intent(this, InsertArticleActivity.class);
        startActivity(intent);
    }

    public void onClickAfficher(View view) {
        Intent intent = new Intent(this, AfficherListeArticlesActivity.class);
        startActivity(intent);
    }
}