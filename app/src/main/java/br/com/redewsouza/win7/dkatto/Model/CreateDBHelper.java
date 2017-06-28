package br.com.redewsouza.win7.dkatto.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by WIN7 on 31/05/2017.
 */

public class CreateDBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 6;
    private static final String DATABASE_NAME = "dkato.db";

    public CreateDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createSqlPedidos = "CREATE TABLE " + PedidoContract.PedidoEntry.TABLE_NAME +
                "(" +
                PedidoContract.PedidoEntry._ID              + " INTEGER PRIMARY KEY,"   +
                PedidoContract.PedidoEntry.COLUMS_CPF       + " TEXT NOT NULL, "        +
                PedidoContract.PedidoEntry.COLUMS_EMISSAO   + " TEXT NOT NULL, "        +
                PedidoContract.PedidoEntry.COLUMS_TOTAL   + " REAL "        +
                "); ";

                String createSqlVerPedidos = " CREATE TABLE " + VerPedidosContract.VerPedidosEntry.TABLE_NAME +
                "(" +
                VerPedidosContract.VerPedidosEntry._VP_ID               + " INTEGER PRIMARY KEY," +
                VerPedidosContract.VerPedidosEntry.COLUMS_VP_PEDIDO_ID  + " TEXT NOT NULL, " +
                VerPedidosContract.VerPedidosEntry.COLUMS_VP_ITEMCATALOGO_ID + " TEXT NOT NULL, " +
                VerPedidosContract.VerPedidosEntry.COLUMS_VP_PP         + " TEXT, " +
                VerPedidosContract.VerPedidosEntry.COLUMS_VP_P          + " TEXT, " +
                VerPedidosContract.VerPedidosEntry.COLUMS_VP_M          + " TEXT, " +
                VerPedidosContract.VerPedidosEntry.COLUMS_VP_G          + " TEXT, " +
                VerPedidosContract.VerPedidosEntry.COLUMS_VP_GG         + " TEXT, " +
                VerPedidosContract.VerPedidosEntry.COLUMS_VP_OBS        + " TEXT, " +
                VerPedidosContract.VerPedidosEntry.COLUMS_VP_PRECO        + " REAL" + "); "
                ;
        Log.i("sql",createSqlVerPedidos);

        db.execSQL(createSqlPedidos);
        db.execSQL(createSqlVerPedidos);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(PedidoContract.PedidoEntry.SCRIPT_DEL_TABELA);
        db.execSQL(VerPedidosContract.VerPedidosEntry.SCRIPT_DEL_TABELA);
        onCreate(db);
    }
}
