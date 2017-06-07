package com.example.win7.dakato.Activitys;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.win7.dakato.Adapters.CatalogoAdapter;
import com.example.win7.dakato.Catalogo;
import com.example.win7.dakato.Model.CatalogoContract;
import com.example.win7.dakato.Model.CatalogoController;
import com.example.win7.dakato.R;
import com.example.win7.dakato.ViewHolders.CatalogoViewHolder;
import com.firebase.client.Firebase;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.api.model.StringList;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CatalogoActivity extends AppCompatActivity {

    ImageButton btnPesquisar;
    RecyclerView recyclerView;
    String codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnPesquisar = (ImageButton) findViewById(R.id.btnPesquisar);
        btnPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CatalogoActivity.this, PedidosActivity.class);  //your class
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
                finish();
            }
        });

        //Recebe ID
        codigo = this.getIntent().getStringExtra("codigo");

        //Recycler view + firebase
        recyclerView = (RecyclerView) findViewById(R.id.rv_Catalogo);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerAdapter mAdapter;

        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        DatabaseReference mRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://dkato-790c9.firebaseio.com/Itens");


        //Lista os itens pela ordem nome
        mAdapter = new FirebaseRecyclerAdapter<Catalogo, CatalogoViewHolder>(Catalogo.class, R.layout.list_catalogo_layout, CatalogoViewHolder.class, mRef.orderByChild("nome")) {
            @Override
            public void populateViewHolder(CatalogoViewHolder catalogoViewHolder, final Catalogo cat, int position) {
                catalogoViewHolder.setNome(cat.getNome());
                catalogoViewHolder.setReferencia(cat.getReferencia());
                catalogoViewHolder.setPreco(String.valueOf(cat.getPreco()));
                catalogoViewHolder.setImg(String.valueOf(cat.getImg()), CatalogoActivity.this);

                final String nome = cat.getNome();
                final String referencia = cat.getReferencia();
                final String preco = cat.getPreco();
                final String img = cat.getImg();

                if (codigo == null) {
                    catalogoViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //String[] item = {cat.getNome(),cat.getReferencia()};
                            Intent i = new Intent(CatalogoActivity.this, VerCatalogoActivity.class);  //your class

                            Bundle b = new Bundle();
                            b.putStringArray("item", new String[]{nome, referencia, preco, img});
                            i.putExtras(b);

                            startActivity(i);

                        }
                    });
                } else {
                    catalogoViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //String[] item = {cat.getNome(),cat.getReferencia()};
                            Intent i = new Intent(CatalogoActivity.this, AddItemActivity.class);  //your class


                            Bundle b = new Bundle();
                            b.putStringArray("item", new String[]{nome, referencia, preco, img});
                            i.putExtras(b);
                            i.putExtra("codigo", codigo);

                            startActivity(i);

                        }
                    });
                }

            }

        };

        //Insere no nome
        recyclerView.setAdapter(mAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_atualizar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_atualizar:
                super.onRestart();
                Intent i = new Intent(CatalogoActivity.this, CatalogoActivity.class);  //your class
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
                Toast.makeText(CatalogoActivity.this, "Atualizando", Toast.LENGTH_SHORT).show();
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
