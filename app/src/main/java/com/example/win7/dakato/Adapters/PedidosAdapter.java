package com.example.win7.dakato.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.win7.dakato.Pedido;
import com.example.win7.dakato.R;

import java.util.ArrayList;

/**
 * Created by WIN7 on 27/05/2017.
 */

public class PedidosAdapter extends ArrayAdapter<Pedido> {

    private Context context;
    private ArrayList<Pedido> pedido;

    public PedidosAdapter(Context context, ArrayList<Pedido> pedido) {
        super(context, 0, pedido);
        this.context = context;
        this.pedido = pedido;
    }

    //Devolve view personalizada

    public View getView(int position, View convertView, ViewGroup parent) {
        //Pega a posicao do bjt q ta buscando
        Pedido pedidoPosicao = this.pedido.get(position);

        //Informa o layout
        convertView = LayoutInflater.from(this.context).inflate(R.layout.list_pedido_layout,null);

        TextView txtEmissao = (TextView) convertView.findViewById(R.id.txt_emissao);
        txtEmissao.setText(pedidoPosicao.getEmissao());

        return convertView;
    }
}
