package com.example.win7.dakato.ViewHolders;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.win7.dakato.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.squareup.picasso.Picasso;

/**
 * Created by WIN7 on 05/06/2017.
 */

public class CatalogoViewHolder extends RecyclerView.ViewHolder {

    public final TextView referencia;
    public final TextView nome;
    public final TextView preco;
    public final ImageView img;

    public CatalogoViewHolder(View view) {
        super(view);
        img = (ImageView) view.findViewById(R.id.img_catalogo);
        referencia = (TextView) view.findViewById(R.id.txt_catReferencia);
        nome = (TextView) view.findViewById(R.id.txt_catNome);
        preco = (TextView) view.findViewById(R.id.txt_catPreco);

    }

    public void setReferencia(String ref) {
        referencia.setText(ref);
    }

    public void setNome(String nomeV) {
        nome.setText(nomeV);
    }

    public void setPreco(String p) {
        preco.setText(p);
    }

    public void setImg(String i, Context context) {

        Picasso.with(context).load(i).fit().centerCrop().into(img);
        img.setImageURI(Uri.parse(i));

    }
}
