package com.example.mesarticles.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.mesarticles.R;
import com.example.mesarticles.bo.Article;
import com.example.mesarticles.view_model.ArticleViewModel;

public class EditArticleActivity extends AppCompatActivity {

    private Article article = null;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_article);
        Intent intent = getIntent();
        article = intent.getParcelableExtra("article");
    }

    @Override
    protected void onResume() {
        super.onResume();

        EditText tvNom = findViewById(R.id.et_nom_article);
        tvNom.setText(article.getNom());

        EditText tvPrix = findViewById(R.id.et_Prix);
        tvPrix.setText(String.valueOf(article.getPrix()));

        EditText tvDescription = findViewById(R.id.et_description);
        tvDescription.setText(article.getDescription());

        RatingBar rbNote = findViewById(R.id.rb_note);
        rbNote.setRating(article.getNote());

        EditText tvUrl = findViewById(R.id.et_url);
        tvUrl.setText(article.getUrl());

        CheckBox chkAcquis = findViewById(R.id.chk_acquis);
        chkAcquis.setChecked(article.isAcquis());
    }

    public void onClickEditer(View view) {

        ArticleViewModel viewModel = ViewModelProviders.of(this).get(ArticleViewModel.class);

        EditText EtNom = findViewById(R.id.et_nom_article);
        String nom = EtNom.getText().toString();
        article.setNom(nom);

        EditText EtPrix = findViewById(R.id.et_Prix);
        float prix = Float.parseFloat(EtPrix.getText().toString());
        article.setPrix(prix);

        EditText EtDescription = findViewById(R.id.et_description);
        String description = EtDescription.getText().toString();
        article.setDescription(description);

        RatingBar RbNote = findViewById(R.id.rb_note);
        float note = RbNote.getRating();
        article.setNote(note);

        EditText EtUrl = findViewById(R.id.et_url);
        String url = EtUrl.getText().toString();
        article.setUrl(url);

        CheckBox ChkAcquis = findViewById(R.id.chk_acquis);
        boolean isAcquis = ChkAcquis.isChecked();
        article.setAcquis(isAcquis);


        viewModel.update(article);

        Intent intent = new Intent(this, AfficherListeArticlesActivity.class);
        startActivity(intent);
    }

    public void onClickRetour(View view) {
        Intent intent = new Intent(this, AfficherListeArticlesActivity.class);
        startActivity(intent);
    }
}