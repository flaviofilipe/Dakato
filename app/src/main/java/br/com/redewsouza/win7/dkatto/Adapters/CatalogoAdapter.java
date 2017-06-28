package br.com.redewsouza.win7.dkatto.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.redewsouza.win7.dkatto.Catalogo;
import br.com.redewsouza.win7.dkatto.R;
import br.com.redewsouza.win7.dkatto.ViewHolders.CatalogoViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WIN7 on 29/05/2017.
 */

public class CatalogoAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<Catalogo> catalogos;


    public CatalogoAdapter(List<Catalogo> catalogo, Context context) {

        this.context = context;
        this.catalogos = catalogo;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.list_catalogo_layout, parent, false);
        CatalogoViewHolder holder = new CatalogoViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        CatalogoViewHolder holder = (CatalogoViewHolder) viewHolder;
        Catalogo catalogo = catalogos.get(position);
        holder.nome.setText(catalogo.getNome());
        holder.img.setImageResource(Integer.parseInt(catalogo.getImg()));
        //Picasso.with(context).load(catalogo.getImg()).into(holder.img);
        holder.referencia.setText(catalogo.getReferencia());
        holder.preco.setText(String.valueOf(catalogo.getPreco()));
    }

    @Override
    public int getItemCount() {
        //Retorna a quantidade de itens
        return catalogos.size();
    }
}
