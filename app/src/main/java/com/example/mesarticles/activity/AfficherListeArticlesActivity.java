package com.example.mesarticles.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mesarticles.R;
import com.example.mesarticles.activity.adapter.ArticleAdapter;
import com.example.mesarticles.bo.Article;
import com.example.mesarticles.view_model.ArticleViewModel;

import java.util.List;

public class AfficherListeArticlesActivity extends AppCompatActivity {
    Context context = this;
    ArticleViewModel viewModel = null;
    ListView listeArticles = null;
    int position = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_liste_articles);
        listeArticles = findViewById(R.id.liste_articles);
        viewModel = ViewModelProviders.of(this).get(ArticleViewModel.class);

        listeArticles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Article article = (Article) listeArticles.getAdapter().getItem(i);
                Intent intent = new Intent(context, AfficherArticleActivity.class);
                intent.putExtra("article", article);
                startActivity(intent);
            }
        });

        listeArticles.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                position=i;
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                Article article = (Article) listeArticles.getAdapter().getItem(position);
                                viewModel.delete(article);
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Supprimer?").setPositiveButton("Oui", dialogClickListener)
                        .setNegativeButton("Annuler", dialogClickListener).show();
                return false;
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        ArticleViewModel viewModel = ViewModelProviders.of(this).get(ArticleViewModel.class);
        LiveData<List<Article>> observateur = viewModel.get();

        observateur.observe(this, new Observer<List<Article>>() {
            @Override
            public void onChanged(List<Article> articles) {
                ArticleAdapter adapter = new ArticleAdapter(AfficherListeArticlesActivity.this, R.layout.style_ligne_utilisateur, articles);
                listeArticles.setAdapter(adapter);
            }
        });
    }

    public void onClickAjout(View view) {
        Intent intent = new Intent(this, InsertArticleActivity.class);
        startActivity(intent);
    }
}


