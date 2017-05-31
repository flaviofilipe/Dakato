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

    public PedidoController(Context context){
        banco = new CreateDBHelper(context);
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

    public String excluiPedido(Pedido pedido){
        long resultado;
        db = banco.getWritableDatabase();
        resultado = db.delete(PedidoContract.PedidoEntry.TABLE_NAME, PedidoContract.PedidoEntry.COLUMS_EMISSAO+" = ?",
                new String[]{String.valueOf(pedido.getId())});
        db.close();
        if (resultado ==-1)
            return "Erro ao excluir registro";
        else
            return "Registro excluido com sucesso";
    }

    /*
    public List<Pedido> listaTodosPedidos(){
        List<Pedido> listaPedidos = new ArrayList<Pedido>();
        String query = "SELECT * FROM " + PedidoContract.PedidoEntry.TABLE_NAME;
        db = banco.getWritableDatabase();
        Cursor c = db.rawQuery(query, null);
        if(c.moveToFirst()){
            do{
                Pedido pedido = new Pedido();
                pedido.setId(Integer.parseInt(c.getString(0)));
                pedido.setEmissao(c.getString(1));
                pedido.setStatus(c.getString(2));
                pedido.setCpf(c.getString(3));

                listaPedidos.add(pedido);

            }while (c.moveToNext());
        }

        return listaPedidos;
    }
    */
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
}

