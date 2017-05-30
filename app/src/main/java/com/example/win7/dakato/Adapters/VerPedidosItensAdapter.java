package com.example.win7.dakato.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.win7.dakato.R;
import com.example.win7.dakato.VerPedidoItens;

import java.util.ArrayList;

/**
 * Created by WIN7 on 29/05/2017.
 */

public class VerPedidosItensAdapter extends ArrayAdapter<VerPedidoItens> {
    private Context context;
    private ArrayList<VerPedidoItens> verPedido;


    public VerPedidosItensAdapter(Context context, ArrayList<VerPedidoItens> verPedido) {
        super(context, 0, verPedido);
        this.context = context;
        this.verPedido = verPedido;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        //Pega a posicao do bjt q ta buscando
        VerPedidoItens verPedidoPosicao = this.verPedido.get(position);

        //Informa o layout
        convertView = LayoutInflater.from(this.context).inflate(R.layout.list_ver_pedido_layout,null);

        TextView txtId = (TextView) convertView.findViewById(R.id.txt_vp_id);
        txtId.setText(String.valueOf(verPedidoPosicao.getId()));

        TextView txtPedido_id = (TextView) convertView.findViewById(R.id.txt_pedidoId);
        txtPedido_id.setText(String.valueOf(verPedidoPosicao.getPedido_id()));


        TextView txtP = (TextView) convertView.findViewById(R.id.txt_vp_p);
        txtP.setText(String.valueOf(verPedidoPosicao.getP()));

        TextView txtPP = (TextView) convertView.findViewById(R.id.txt_vp_pp);
        txtPP.setText(String.valueOf(verPedidoPosicao.getPp()));

        TextView txtM = (TextView) convertView.findViewById(R.id.txt_vp_m);
        txtM.setText(String.valueOf(verPedidoPosicao.getM()));

        TextView txtG = (TextView) convertView.findViewById(R.id.txt_vp_g);
        txtG.setText(String.valueOf(verPedidoPosicao.getG()));

        TextView txtGG = (TextView) convertView.findViewById(R.id.txt_vp_gg);
        txtGG.setText(String.valueOf(verPedidoPosicao.getGg()));

        TextView txtObs = (TextView) convertView.findViewById(R.id.txt_vp_obs);
        txtObs.setText(verPedidoPosicao.getObs());


        return convertView;
    }

}
