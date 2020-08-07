package com.example.mesarticles.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.example.mesarticles.R;
import com.example.mesarticles.bo.Article;
import com.example.mesarticles.repository.ArticleBDDRepository;
import com.example.mesarticles.repository.IArticleRepository;
import com.example.mesarticles.view_model.ArticleViewModel;
import com.facebook.stetho.Stetho;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class InsertArticleActivity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_TAKE_PHOTO = 1;
    String currentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_article);
        Stetho.initializeWithDefaults(this);
    }


    public void onClickAjout(View view) {

        ArticleViewModel viewModel = ViewModelProviders.of(this).get(ArticleViewModel.class);
        // LiveData<List<Article>> observateur = viewModel.get();


        EditText EtNom = findViewById(R.id.nom_article);
        String nom = EtNom.getText().toString();

        EditText EtPrix = findViewById(R.id.prix_article);
        float prix = Float.parseFloat(EtPrix.getText().toString());

        EditText EtDescription = findViewById(R.id.description_article);
        String description = EtDescription.getText().toString();

        RatingBar RbNote = findViewById(R.id.note_article);
        float note = RbNote.getRating();

        EditText EtUrl = findViewById(R.id.url_article);
        String url = EtUrl.getText().toString();

        CheckBox ChkAcquis = findViewById(R.id.acquis_article);
        boolean isAcquis = ChkAcquis.isChecked();

        Article article = new Article(nom, prix, note, description, isAcquis, url, currentPhotoPath);

        viewModel.insert(article);

        Intent intent = new Intent(this, AccueilActivity.class);
        startActivity(intent);

    }

    public void onClickPhoto(View view) {
        Log.i("rudy","je suis dans le onClickPhoto");
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // S'assure que la camera soit présente
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Crée le path ou l'image va etre stockée
            Log.i("rudy","je suis dans le if du onClick");
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                Log.i("erreur", "pb photo");
            }
            // Continue si le fichier a été créé
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,"com.example.android.fileprovider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private File createImageFile() throws IOException {
        // crée le path de l'image
        Log.i("rudy","je suis dans le createImageFile");
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Path qu'aura la photo a mettre en BDD
        currentPhotoPath = image.getAbsolutePath();
        Log.i("rudy",currentPhotoPath);
        return image;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        File imgFile = new  File(currentPhotoPath);
        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
        ImageView myImage = (ImageView) findViewById(R.id.img_photo);
        myImage.setImageBitmap(myBitmap);
        onResume();

Log.i("rudy","je suis dans le onActivityResult");
    }



}