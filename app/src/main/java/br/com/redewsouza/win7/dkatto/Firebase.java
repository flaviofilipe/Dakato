package br.com.redewsouza.win7.dkatto;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;


public class Firebase extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}