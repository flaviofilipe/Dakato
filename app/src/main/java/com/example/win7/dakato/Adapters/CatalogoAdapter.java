package com.example.win7.dakato.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.win7.dakato.Catalogo;
import com.example.win7.dakato.R;

import java.util.ArrayList;

/**
 * Created by WIN7 on 29/05/2017.
 */

public class CatalogoAdapter  extends ArrayAdapter<Catalogo> {
    private Context context;
    private ArrayList<Catalogo> catalogo;

    public CatalogoAdapter(Context context,  ArrayList<Catalogo> catalogo) {
        super(context, 0, catalogo);
        this.context = context;
        this.catalogo = catalogo;
    }

    //Devolve view personalizada

    public View getView(int position, View convertView, ViewGroup parent) {
        //Pega a posicao do bjt q ta buscando
        Catalogo catalogoPosicao = this.catalogo.get(position);

        //Informa o layout
        convertView = LayoutInflater.from(this.context).inflate(R.layout.list_catalogo_layout,null);

        //Insere um inteiro
        ImageView imgCatalogo = (ImageView) convertView.findViewById(R.id.img_catalogo);
        imgCatalogo.setImageResource(catalogoPosicao.getImg());


        TextView txtReferencia = (TextView) convertView.findViewById(R.id.txt_referencia);
        txtReferencia.setText(catalogoPosicao.getReferencia());

        TextView txtNome = (TextView) convertView.findViewById(R.id.txt_nome);
        txtNome.setText((CharSequence) catalogoPosicao.getDNome());

        TextView txtPreco = (TextView) convertView.findViewById(R.id.txt_preco);
        txtPreco.setText(String.valueOf(catalogoPosicao.getPreco()));


        return convertView;
    }
}
