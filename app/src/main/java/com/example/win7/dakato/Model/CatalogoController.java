package com.example.win7.dakato.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;



public class CatalogoController {
    private SQLiteDatabase db;
    private CreateDBHelper banco;

    public CatalogoController(Context context){
        banco = new CreateDBHelper(context);
    }

    public String insereCatalogo(String nome, String referencia, Double preco, String img, String pp, String p, String m, String g, String gg){
        ContentValues valores = new ContentValues();
        long resultado;

        db = banco.getWritableDatabase();
        valores.put(CatalogoContract.CatalogoEntry.COLUMS_REFERENCIA, referencia);
        valores.put(CatalogoContract.CatalogoEntry.COLUMS_NOME, nome);
        valores.put(CatalogoContract.CatalogoEntry.COLUMS_PRECO, preco);
        valores.put(CatalogoContract.CatalogoEntry.COLUMS_IMG, img);
        valores.put(CatalogoContract.CatalogoEntry.COLUMS_PP, pp);
        valores.put(CatalogoContract.CatalogoEntry.COLUMS_P, p);
        valores.put(CatalogoContract.CatalogoEntry.COLUMS_G, m);
        valores.put(CatalogoContract.CatalogoEntry.COLUMS_M, g);
        valores.put(CatalogoContract.CatalogoEntry.COLUMS_GG, gg);

        resultado = db.insert(CatalogoContract.CatalogoEntry.TABLE_NAME, null, valores);
        db.close();

        if (resultado ==-1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";
    }


    public Cursor carregaDados(){
        Cursor cursor;
        String[] campos =  {
                CatalogoContract.CatalogoEntry._ID,
                CatalogoContract.CatalogoEntry.COLUMS_REFERENCIA,
                CatalogoContract.CatalogoEntry.COLUMS_NOME,
                CatalogoContract.CatalogoEntry.COLUMS_PRECO,
                CatalogoContract.CatalogoEntry.COLUMS_IMG,
                CatalogoContract.CatalogoEntry.COLUMS_PP,
                CatalogoContract.CatalogoEntry.COLUMS_P,
                CatalogoContract.CatalogoEntry.COLUMS_G,
                CatalogoContract.CatalogoEntry.COLUMS_M,
                CatalogoContract.CatalogoEntry.COLUMS_GG
        };
        db = banco.getReadableDatabase();
        cursor = db.query(CatalogoContract.CatalogoEntry.TABLE_NAME, campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor carregaDadoById(int id){
        Cursor cursor;
        String[] campos =  {
                CatalogoContract.CatalogoEntry._ID,
                CatalogoContract.CatalogoEntry.COLUMS_REFERENCIA,
                CatalogoContract.CatalogoEntry.COLUMS_NOME,
                CatalogoContract.CatalogoEntry.COLUMS_PRECO,
                CatalogoContract.CatalogoEntry.COLUMS_IMG,
                CatalogoContract.CatalogoEntry.COLUMS_PP,
                CatalogoContract.CatalogoEntry.COLUMS_P,
                CatalogoContract.CatalogoEntry.COLUMS_G,
                CatalogoContract.CatalogoEntry.COLUMS_M,
                CatalogoContract.CatalogoEntry.COLUMS_GG
        };
        String where = CatalogoContract.CatalogoEntry._ID + "=" + id;
        db = banco.getReadableDatabase();
        cursor = db.query(CatalogoContract.CatalogoEntry._ID, campos, where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }


    /*
    public String excluiCatalogo(Catalogo Catalogo){
        long resultado;
        db = banco.getWritableDatabase();
        resultado = db.delete(CatalogoContract.CatalogoEntry.TABLE_NAME, CatalogoContract.CatalogoEntry._ID+" = ?",
                new String[]{String.valueOf(Catalogo.getId())});
        db.close();
        if (resultado ==-1)
            return "Erro ao excluir registro";
        else
            return "Registro excluido com sucesso";
    }*/

    public void excluiCatalogo(int id){
        String where = CatalogoContract.CatalogoEntry._ID + "=" + id;
        db = banco.getReadableDatabase();
        db.delete(CatalogoContract.CatalogoEntry.TABLE_NAME,where,null);
        db.close();
    }
}

