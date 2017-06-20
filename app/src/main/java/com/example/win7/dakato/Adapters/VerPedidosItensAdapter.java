package com.example.win7.dakato.Adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.win7.dakato.Funcoes;
import com.example.win7.dakato.Model.VerPedidosContract;
import com.example.win7.dakato.R;
import com.example.win7.dakato.VerPedidoItens;

import java.util.ArrayList;

public class VerPedidosItensAdapter extends CursorAdapter {

    public VerPedidosItensAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_ver_pedido_layout, parent, false);
    }

    public void bindView(View view, Context context, Cursor cursor) {

        TextView txtId = (TextView) view.findViewById(R.id.txt_vp_id);
        TextView txtRef = (TextView) view.findViewById(R.id.txt_vp_itemCatalogo);

        TextView txtP = (TextView) view.findViewById(R.id.txt_vp_p);
        TextView txtPP = (TextView) view.findViewById(R.id.txt_vp_pp);
        TextView txtM = (TextView) view.findViewById(R.id.txt_vp_m);
        TextView txtG = (TextView) view.findViewById(R.id.txt_vp_g);
        TextView txtGG = (TextView) view.findViewById(R.id.txt_vp_gg);

        TextView txtObs = (TextView) view.findViewById(R.id.txt_vp_obs);
        TextView txtPreco = (TextView) view.findViewById(R.id.txt_vp_preco);


        String id = cursor.getString(cursor.getColumnIndexOrThrow(VerPedidosContract.VerPedidosEntry._ID));
        String ref = cursor.getString(cursor.getColumnIndexOrThrow(VerPedidosContract.VerPedidosEntry.COLUMS_VP_ITEMCATALOGO_ID));
        String p = cursor.getString(cursor.getColumnIndexOrThrow(VerPedidosContract.VerPedidosEntry.COLUMS_VP_P));
        String pp = cursor.getString(cursor.getColumnIndexOrThrow(VerPedidosContract.VerPedidosEntry.COLUMS_VP_PP));
        String m = cursor.getString(cursor.getColumnIndexOrThrow(VerPedidosContract.VerPedidosEntry.COLUMS_VP_M));
        String g = cursor.getString(cursor.getColumnIndexOrThrow(VerPedidosContract.VerPedidosEntry.COLUMS_VP_G));
        String gg = cursor.getString(cursor.getColumnIndexOrThrow(VerPedidosContract.VerPedidosEntry.COLUMS_VP_GG));
        String obs = cursor.getString(cursor.getColumnIndexOrThrow(VerPedidosContract.VerPedidosEntry.COLUMS_VP_OBS));
        Double preco = cursor.getDouble(cursor.getColumnIndexOrThrow(VerPedidosContract.VerPedidosEntry.COLUMS_VP_PRECO));


        txtId.setText(String.valueOf(id));
        /*
        final TextView txtPP = new TextView(context);
        final TextView txtP = new TextView(context);
        final TextView txtM = new TextView(context);
        final TextView txtG = new TextView(context);
        final TextView txtGG = new TextView(context);
        LinearLayout llayout = (LinearLayout) view.findViewById(R.id.ll_tamanhos);
        */


        if (!p.equals("")) {
            txtP.setText("P: "+String.valueOf(p));
        }else {
            txtP.setVisibility(View.GONE);
        }
        if (!pp.equals("")) {
            txtPP.setText("PP: "+String.valueOf(pp));
        }else {
            txtPP.setVisibility(View.GONE);
        }
        if (!m.equals("")) {
            txtM.setText("M: "+String.valueOf(m));
        }else {
            txtM.setVisibility(View.GONE);
        }
        if (!g.equals("")) {
            txtG.setText("G: "+String.valueOf(g));
        }else {
            txtG.setVisibility(View.GONE);
        }
        if (!gg.equals("")) {
            txtGG.setText("GG: "+String.valueOf(gg));

        }else {
            txtGG.setVisibility(View.GONE);
        }

        txtObs.setText(obs);
        txtPreco.setText("R$"+ Funcoes.precoSaida(preco));
        txtRef.setText("Ref "+ ref);

    }

}
