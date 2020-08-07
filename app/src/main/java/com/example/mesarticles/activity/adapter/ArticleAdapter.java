package com.example.mesarticles.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mesarticles.R;
import com.example.mesarticles.bo.Article;

import java.util.List;

public class ArticleAdapter extends ArrayAdapter<Article> {

    public ArticleAdapter(@NonNull Context context, int resource, @NonNull List<Article> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View nouvelleLigne, @NonNull ViewGroup parent) {
        if (nouvelleLigne == null) {
            LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            nouvelleLigne = li.inflate(R.layout.style_ligne_utilisateur, parent, false);
        }

        TextView tvNom = nouvelleLigne.findViewById(R.id.tv_nom_article);
        tvNom.setText(getItem(position).getNom());

        RatingBar rbNote = nouvelleLigne.findViewById(R.id.rbNote);
        rbNote.setRating(getItem(position).getNote());

        return nouvelleLigne;
    }
}
