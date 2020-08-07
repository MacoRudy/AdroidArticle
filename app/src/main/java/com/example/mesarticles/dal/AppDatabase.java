package com.example.mesarticles.dal;

        import android.content.Context;
        import android.os.AsyncTask;

        import androidx.annotation.NonNull;
        import androidx.room.Database;
        import androidx.room.Room;
        import androidx.room.RoomDatabase;
        import androidx.sqlite.db.SupportSQLiteDatabase;

        import com.example.mesarticles.bo.Article;

@Database(entities = {Article.class}, exportSchema = false, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    /**
     * Permet de fournir une instance de la DAO aux couches superieures
     *
     * @return
     */
    public abstract ArticleDAO getArticleDAO();

    private static AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "mesEnvies.db")
                    // creation de la bdd
                    .addCallback(roomFixture)
                    // pour ne pas a avoir de faire Async pour acceder a la bdd
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    private static Callback roomFixture = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            new AsyncTask<AppDatabase, Void, Void>() {

                @Override
                protected Void doInBackground(AppDatabase... appDatabases) {
                    ArticleDAO dao = INSTANCE.getArticleDAO();
                   // dao.insert(articleAAjouter);
                    return null;
                }


            }.execute(INSTANCE);
        }
    };
}



