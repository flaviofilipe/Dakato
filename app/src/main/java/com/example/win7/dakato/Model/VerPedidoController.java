package com.example.win7.dakato.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by WIN7 on 05/06/2017.
 */

public class VerPedidoController {

    private SQLiteDatabase db;
    private CreateDBHelper banco;

    public VerPedidoController(Context context){
        banco = new CreateDBHelper(context);
    }


    public String inserePedido(String pedido_id, String itemcatalogo_id, String pp, String p, String m, String g, String gg, String obs, Double preco){
        ContentValues valores = new ContentValues();
        long resultado;

        db = banco.getWritableDatabase();
        valores.put(VerPedidosContract.VerPedidosEntry.COLUMS_VP_PEDIDO_ID, pedido_id);
        valores.put(VerPedidosContract.VerPedidosEntry.COLUMS_VP_ITEMCATALOGO_ID, itemcatalogo_id);
        valores.put(VerPedidosContract.VerPedidosEntry.COLUMS_VP_PP, pp);
        valores.put(VerPedidosContract.VerPedidosEntry.COLUMS_VP_P, p);
        valores.put(VerPedidosContract.VerPedidosEntry.COLUMS_VP_M, m);
        valores.put(VerPedidosContract.VerPedidosEntry.COLUMS_VP_G, g);
        valores.put(VerPedidosContract.VerPedidosEntry.COLUMS_VP_GG, gg);
        valores.put(VerPedidosContract.VerPedidosEntry.COLUMS_VP_OBS, obs);
        valores.put(VerPedidosContract.VerPedidosEntry.COLUMS_VP_PRECO, preco);

        resultado = db.insert(VerPedidosContract.VerPedidosEntry.TABLE_NAME, null, valores);
        db.close();

        if (resultado ==-1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";
    }


    public Cursor carregaDados(){
        Cursor cursor;
        String[] campos =  {
                VerPedidosContract.VerPedidosEntry._ID,
                VerPedidosContract.VerPedidosEntry.COLUMS_VP_PEDIDO_ID,
                VerPedidosContract.VerPedidosEntry.COLUMS_VP_ITEMCATALOGO_ID,
                VerPedidosContract.VerPedidosEntry.COLUMS_VP_PP,
                VerPedidosContract.VerPedidosEntry.COLUMS_VP_P,
                VerPedidosContract.VerPedidosEntry.COLUMS_VP_M,
                VerPedidosContract.VerPedidosEntry.COLUMS_VP_G,
                VerPedidosContract.VerPedidosEntry.COLUMS_VP_GG,
                VerPedidosContract.VerPedidosEntry.COLUMS_VP_OBS,
                VerPedidosContract.VerPedidosEntry.COLUMS_VP_PRECO
        };
        db = banco.getReadableDatabase();
        cursor = db.query(VerPedidosContract.VerPedidosEntry.TABLE_NAME, campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor carregaDadoById(int id){
        Cursor cursor;
        String[] campos =  {
                VerPedidosContract.VerPedidosEntry._ID,
                VerPedidosContract.VerPedidosEntry.COLUMS_VP_PEDIDO_ID,
                VerPedidosContract.VerPedidosEntry.COLUMS_VP_ITEMCATALOGO_ID,
                VerPedidosContract.VerPedidosEntry.COLUMS_VP_PP,
                VerPedidosContract.VerPedidosEntry.COLUMS_VP_P,
                VerPedidosContract.VerPedidosEntry.COLUMS_VP_M,
                VerPedidosContract.VerPedidosEntry.COLUMS_VP_G,
                VerPedidosContract.VerPedidosEntry.COLUMS_VP_GG,
                VerPedidosContract.VerPedidosEntry.COLUMS_VP_OBS,
                VerPedidosContract.VerPedidosEntry.COLUMS_VP_PRECO
        };
        String where = VerPedidosContract.VerPedidosEntry._ID + "=" + id;
        db = banco.getReadableDatabase();
        cursor = db.query(VerPedidosContract.VerPedidosEntry.TABLE_NAME, campos, where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
    public Cursor carregaDadoByPedidos(String id){
        Cursor cursor;
        String[] campos =  {
                VerPedidosContract.VerPedidosEntry._ID,
                VerPedidosContract.VerPedidosEntry.COLUMS_VP_PEDIDO_ID,
                VerPedidosContract.VerPedidosEntry.COLUMS_VP_ITEMCATALOGO_ID,
                VerPedidosContract.VerPedidosEntry.COLUMS_VP_PP,
                VerPedidosContract.VerPedidosEntry.COLUMS_VP_P,
                VerPedidosContract.VerPedidosEntry.COLUMS_VP_M,
                VerPedidosContract.VerPedidosEntry.COLUMS_VP_G,
                VerPedidosContract.VerPedidosEntry.COLUMS_VP_GG,
                VerPedidosContract.VerPedidosEntry.COLUMS_VP_OBS,
                VerPedidosContract.VerPedidosEntry.COLUMS_VP_PRECO
        };

        String where = VerPedidosContract.VerPedidosEntry.COLUMS_VP_PEDIDO_ID + "=" + id;
        db = banco.getReadableDatabase();
        cursor = db.query(VerPedidosContract.VerPedidosEntry.TABLE_NAME,campos, where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public void excluiVerPedidoById(String id){
        String where = VerPedidosContract.VerPedidosEntry._ID + "=" + id;
        db = banco.getReadableDatabase();
        db.delete(VerPedidosContract.VerPedidosEntry.TABLE_NAME,where,null);
        db.close();
    }
    public void excluiVerPedidoByPedido(String id){
        String where = VerPedidosContract.VerPedidosEntry.COLUMS_VP_PEDIDO_ID + "=" + id;
        db = banco.getReadableDatabase();
        db.delete(VerPedidosContract.VerPedidosEntry.TABLE_NAME,where,null);
        db.close();
    }


    public Cursor carregaTotal(String id){
        Cursor cursor = db.rawQuery("SELECT SUM("
                + (VerPedidosContract.VerPedidosEntry.COLUMS_VP_PRECO)
                + ") FROM " + (VerPedidosContract.VerPedidosEntry.TABLE_NAME), null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public String updateTotal(String id, Double total){
        ContentValues valores = new ContentValues();
        long resultado;

        db = banco.getWritableDatabase();
        valores.put(PedidoContract.PedidoEntry.COLUMS_TOTAL, total);
        resultado = db.update(PedidoContract.PedidoEntry.TABLE_NAME,valores,PedidoContract.PedidoEntry._ID+" = "+id,null);
        if (resultado ==-1)
            return "Erro ao atualizar registro";
        else
            return "Registro atualizado com sucesso";

    }

}
