package br.com.redewsouza.win7.dkatto.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.TextView;

import br.com.redewsouza.win7.dkatto.Funcoes;
import br.com.redewsouza.win7.dkatto.Model.PedidoContract;
import br.com.redewsouza.win7.dkatto.Pedido;
import br.com.redewsouza.win7.dkatto.R;

import java.util.ArrayList;

/**
 * Created by WIN7 on 27/05/2017.
 */

public class PedidosAdapter extends CursorAdapter {


    public PedidosAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_pedido_layout, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView txtEmissao = (TextView) view.findViewById(R.id.txt_emissao);
        TextView txtId = (TextView) view.findViewById(R.id.txt_id);
        TextView txtTotal = (TextView) view.findViewById(R.id.txt_total);

        String id = cursor.getString(cursor.getColumnIndexOrThrow(PedidoContract.PedidoEntry._ID));
        String emissao = cursor.getString(cursor.getColumnIndexOrThrow(PedidoContract.PedidoEntry.COLUMS_EMISSAO));
        Double total = cursor.getDouble(cursor.getColumnIndexOrThrow(PedidoContract.PedidoEntry.COLUMS_TOTAL));

        txtEmissao.setText(emissao);
        txtId.setText(id);

        txtTotal.setText("R$"+ Funcoes.precoSaida(total));


    }
}
