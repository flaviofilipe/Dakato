package com.example.win7.dakato.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.win7.dakato.Pedido;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WIN7 on 31/05/2017.
 */

public class PedidoController {
    private SQLiteDatabase db;
    private CreateDBHelper banco;
    VerPedidoController vpExcluir;

    public PedidoController(Context context){
        banco = new CreateDBHelper(context);
        vpExcluir = new VerPedidoController(context);
    }

    public String inserePedido(String emissao, String status, String cpf){
        ContentValues valores = new ContentValues();
        long resultado;

        db = banco.getWritableDatabase();
        valores.put(PedidoContract.PedidoEntry.COLUMS_EMISSAO, emissao);
        valores.put(PedidoContract.PedidoEntry.COLUMS_STATUS, status);
        valores.put(PedidoContract.PedidoEntry.COLUMS_CPF, cpf);

        resultado = db.insert(PedidoContract.PedidoEntry.TABLE_NAME, null, valores);
        db.close();

        if (resultado ==-1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";
    }


    public Cursor carregaDados(){
        Cursor cursor;
        String[] campos =  {
                PedidoContract.PedidoEntry._ID,
                PedidoContract.PedidoEntry.COLUMS_EMISSAO,
                PedidoContract.PedidoEntry.COLUMS_STATUS,
                PedidoContract.PedidoEntry.COLUMS_CPF
        };
        db = banco.getReadableDatabase();
        cursor = db.query(PedidoContract.PedidoEntry.TABLE_NAME, campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor carregaDadoById(int id){
        Cursor cursor;
        String[] campos =  {
                PedidoContract.PedidoEntry._ID,
                PedidoContract.PedidoEntry.COLUMS_EMISSAO,
                PedidoContract.PedidoEntry.COLUMS_STATUS,
                PedidoContract.PedidoEntry.COLUMS_CPF
        };
        String where = PedidoContract.PedidoEntry._ID + "=" + id;
        db = banco.getReadableDatabase();
        cursor = db.query(PedidoContract.PedidoEntry.TABLE_NAME, campos, where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
    public Cursor carregaDadoByData(String data){
        Cursor cursor;
        String[] campos =  {
                PedidoContract.PedidoEntry._ID,
                PedidoContract.PedidoEntry.COLUMS_EMISSAO,
                PedidoContract.PedidoEntry.COLUMS_STATUS,
                PedidoContract.PedidoEntry.COLUMS_CPF
        };
        String where = PedidoContract.PedidoEntry.COLUMS_EMISSAO + "=" + data;
        db = banco.getReadableDatabase();
        cursor = db.query(PedidoContract.PedidoEntry.TABLE_NAME, campos, where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }


    public void excluiPedido(String id){
        String where = PedidoContract.PedidoEntry._ID + "=" + id;
        db = banco.getReadableDatabase();
        db.delete(PedidoContract.PedidoEntry.TABLE_NAME,where,null);

        vpExcluir.excluiVerPedidoByPedido(id);
        db.close();
    }
}

