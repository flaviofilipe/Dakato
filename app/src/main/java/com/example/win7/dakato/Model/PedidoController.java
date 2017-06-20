package com.example.win7.dakato.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.win7.dakato.Pedido;

import java.util.ArrayList;
import java.util.List;

public class PedidoController {
    private SQLiteDatabase db;
    private CreateDBHelper banco;
    VerPedidoController vpExcluir;

    public PedidoController(Context context){
        banco = new CreateDBHelper(context);
        vpExcluir = new VerPedidoController(context);
    }

    public String inserePedido(String emissao, String cpf){
        ContentValues valores = new ContentValues();
        long resultado;

        db = banco.getWritableDatabase();
        valores.put(PedidoContract.PedidoEntry.COLUMS_EMISSAO, emissao);
        valores.put(PedidoContract.PedidoEntry.COLUMS_CPF, cpf);
        valores.put(PedidoContract.PedidoEntry.COLUMS_TOTAL, 0);

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
                PedidoContract.PedidoEntry.COLUMS_CPF,
                PedidoContract.PedidoEntry.COLUMS_TOTAL
        };
        String order = PedidoContract.PedidoEntry.COLUMS_EMISSAO + " DESC ";
        db = banco.getReadableDatabase();
        cursor = db.query(PedidoContract.PedidoEntry.TABLE_NAME, campos, null, null, null, null, order, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor carregaDadoById(String id){
        Cursor cursor;
        String[] campos =  {
                PedidoContract.PedidoEntry._ID,
                PedidoContract.PedidoEntry.COLUMS_EMISSAO,
                PedidoContract.PedidoEntry.COLUMS_CPF,
                PedidoContract.PedidoEntry.COLUMS_TOTAL
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



    public void excluiPedido(String id){
        String where = PedidoContract.PedidoEntry._ID + "=" + id;
        db = banco.getReadableDatabase();
        db.delete(PedidoContract.PedidoEntry.TABLE_NAME,where,null);

        vpExcluir.excluiVerPedidoByPedido(id);
        db.close();
    }
}

