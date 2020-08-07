package com.example.mesarticles.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.mesarticles.R;
import com.example.mesarticles.bo.Article;

import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;


public class AfficherArticleActivity extends AppCompatActivity {
    private Article article = null;
    Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_article);
        Intent intent = getIntent();
        article = intent.getParcelableExtra("article");
    }

    @Override
    protected void onResume() {
        super.onResume();

        TextView tvNom = findViewById(R.id.tv_nom_article);
        tvNom.setText(article.getNom());




        TextView tvPrix = findViewById(R.id.tv_Prix);
        NumberFormat formatter = new DecimalFormat("0.00");
        String formattedPrix = formatter.format(article.getPrix());
        tvPrix.setText(String.valueOf(formattedPrix)+"€");

        TextView tvDescription = findViewById(R.id.tv_description);
        tvDescription.setText(article.getDescription());

        RatingBar rbNote = findViewById(R.id.rb_note);
        rbNote.setRating(article.getNote());

        TextView tvUrl = findViewById(R.id.tv_url);
        tvUrl.setText(article.getUrl());

        CheckBox chkAcquis = findViewById(R.id.chk_acquis);
        chkAcquis.setChecked(article.isAcquis());

        File imgFile = new  File(article.getPhoto());
        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

        ImageView myImage = (ImageView) findViewById(R.id.img_photo);

        myImage.setImageBitmap(myBitmap);

    }

    public void onClickEdit(View view) {

        Intent intent = new Intent(context, EditArticleActivity.class);
        intent.putExtra("article", article);
        startActivity(intent);
    }

    public void onClickRetour(View view) {
        Intent intent = new Intent(context, AfficherListeArticlesActivity.class);
        startActivity(intent);
    }
}