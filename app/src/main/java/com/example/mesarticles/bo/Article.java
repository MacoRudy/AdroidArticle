package com.example.mesarticles.bo;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Classe representant un article
 */
@Entity
public class Article implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nom;
    private float prix;
    private float note;
    private String description;
    private boolean isAcquis;
    private String url;
    private String photo;



    public Article(String nom, float prix, float note, String description, boolean isAcquis, String url, String photo) {
        this.nom = nom;
        this.prix = prix;
        this.note = note;
        this.description = description;
        this.isAcquis = isAcquis;
        this.url = url;
        this.photo = photo;
    }

    protected Article(Parcel in) {
        id = in.readInt();
        nom = in.readString();
        prix = in.readFloat();
        note = in.readFloat();
        description = in.readString();
        isAcquis = in.readByte() != 0;
        url = in.readString();
        photo = in.readString();
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public float getNote() {
        return note;
    }

    public void setNote(Float note) {
        this.note = note;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAcquis() {
        return isAcquis;
    }

    public void setAcquis(boolean acquis) {
        isAcquis = acquis;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(nom);
        parcel.writeFloat(prix);
        parcel.writeFloat(note);
        parcel.writeString(description);
        parcel.writeByte((byte) (isAcquis ? 1 : 0));
        parcel.writeString(url);
        parcel.writeString(photo);
    }
}
