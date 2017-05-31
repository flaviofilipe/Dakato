package com.example.win7.dakato.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by WIN7 on 31/05/2017.
 */

public class CreateDBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "dakato.db";

    public CreateDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createSql = "CREATE TABLE " + PedidoContract.PedidoEntry.TABLE_NAME +
                "(" +
                PedidoContract.PedidoEntry._ID + " INTEGER PRIMARY KEY," +
                PedidoContract.PedidoEntry.COLUMS_CPF + " TEXT NOT NULL, " +
                PedidoContract.PedidoEntry.COLUMS_EMISSAO + " TEXT NOT NULL, " +
                PedidoContract.PedidoEntry.COLUMS_STATUS + " TEXT NOT NULL " +
                "); "

                +"CREATE TABLE " +
                VerPedidosContract.VerPedidosEntry.TABLE_NAME +
                "(" +
                VerPedidosContract.VerPedidosEntry._VP_ID + " INTEGER PRIMARY KEY," +
                VerPedidosContract.VerPedidosEntry.COLUMS_VP_REFERENCIA + " TEXT NOT NULL, " +
                VerPedidosContract.VerPedidosEntry.COLUMS_VP_NOME + " TEXT NOT NULL, " +
                VerPedidosContract.VerPedidosEntry.COLUMS_VP_PP + " TEXT NOT NULL, " +
                VerPedidosContract.VerPedidosEntry.COLUMS_VP_P + " TEXT NOT NULL, " +
                VerPedidosContract.VerPedidosEntry.COLUMS_VP_M + " TEXT NOT NULL, " +
                VerPedidosContract.VerPedidosEntry.COLUMS_VP_G + " TEXT NOT NULL, " +
                VerPedidosContract.VerPedidosEntry.COLUMS_VP_GG + " TEXT NOT NULL, " +
                VerPedidosContract.VerPedidosEntry.COLUMS_VP_GG + " TEXT" + ");"
                ;
        db.execSQL(createSql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop = "DROP TABLE IF EXISTS" + PedidoContract.PedidoEntry.TABLE_NAME+";" +
                "DROP TABLE IF EXISTS" + VerPedidosContract.VerPedidosEntry.TABLE_NAME+";";
        db.execSQL("DROP TABLE IF EXISTS" + PedidoContract.PedidoEntry.TABLE_NAME);
        onCreate(db);
    }
}
