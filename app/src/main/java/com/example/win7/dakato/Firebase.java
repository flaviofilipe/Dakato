package com.example.win7.dakato;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;


public class Firebase extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
