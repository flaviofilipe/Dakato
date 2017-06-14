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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.win7.dakato.Adapters.CatalogoAdapter;
import com.example.win7.dakato.Catalogo;
import com.example.win7.dakato.Model.CatalogoContract;
import com.example.win7.dakato.Model.CatalogoController;
import com.example.win7.dakato.R;
import com.example.win7.dakato.ViewHolders.CatalogoViewHolder;
import com.firebase.client.Firebase;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
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
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CatalogoActivity extends AppCompatActivity {

    ImageButton btnPesquisar;
    RecyclerView recyclerView;
    String codigo;
    String cpf;
    EditText edtPesquisar;
    FirebaseListAdapter mAdapter;
    DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_catalogo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Recebe ID
        codigo = this.getIntent().getStringExtra("codigo");
        cpf = this.getIntent().getStringExtra("cpf");

        btnPesquisar = (ImageButton) findViewById(R.id.btnPesquisar);
        edtPesquisar = (EditText) findViewById(R.id.edt_pesquisar);

        /*Recycler view + firebase
        recyclerView = (RecyclerView) findViewById(R.id.rv_Catalogo);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        */
        mRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://dkato-790c9.firebaseio.com/ITENS");

    }

    @Override
    protected void onStart() {

        super.onStart();
        listaItensFirebase();


        btnPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pesquisa = edtPesquisar.getText().toString();
                if (pesquisa.equals("")) {
                    listaItensFirebase();
                    Toast.makeText(CatalogoActivity.this, "Vazio", Toast.LENGTH_SHORT);
                }else{
                    procurarItensFirebase(pesquisa);
                }
            }
        });


    }

    private void procurarItensFirebase(String pesquisa) {

        final ListView mListView = (ListView) findViewById(R.id.lv_catalogo);
        mAdapter = new FirebaseListAdapter<Catalogo>(this,Catalogo.class,R.layout.list_catalogo_layout,mRef.orderByChild("referencia").startAt(pesquisa)){

            @Override
            protected void populateView(View v, Catalogo model, int position) {

                final ImageView imgCatalogo = (ImageView) v.findViewById(R.id.img_catalogo);
                final TextView txtReferencia = (TextView) v.findViewById(R.id.txt_catReferencia);
                final TextView txtNome = (TextView) v.findViewById(R.id.txt_catNome);
                final TextView txtPpreco = (TextView) v.findViewById(R.id.txt_catPreco);

                final String nome = model.getNome();
                final String referencia = model.getReferencia();
                final String preco = model.getPreco();
                final String img = model.getImg();
                final String tamanhos = model.getTamanhos();

                txtReferencia.setText(model.getReferencia());
                txtNome.setText(model.getNome());
                Picasso.with(CatalogoActivity.this).load(model.getImg()).fit().centerCrop().into(imgCatalogo);
                txtPpreco.setText(model.getPreco());


            }
        };
        mListView.setAdapter(mAdapter);
        if (codigo == null) {
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Catalogo model = (Catalogo) parent.getItemAtPosition(position);

                    final String nome = String.valueOf(model.getNome());
                    final String referencia = String.valueOf(model.getReferencia());
                    final String preco = String.valueOf(model.getPreco());
                    final String img = model.getImg();
                    final String tamanhos = model.getTamanhos();

                    Intent i = new Intent(CatalogoActivity.this, VerCatalogoActivity.class);  //your class

                    Bundle b = new Bundle();
                    b.putStringArray("item", new String[]{nome, referencia, preco, img, tamanhos});
                    i.putExtra("cpf", cpf);
                    i.putExtras(b);

                    startActivity(i);
                }
            });
        } else {
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Catalogo model = (Catalogo) parent.getItemAtPosition(position);

                    final String nome = String.valueOf(model.getNome());
                    final String referencia = String.valueOf(model.getReferencia());
                    final String preco = String.valueOf(model.getPreco());
                    final String img = model.getImg();
                    final String tamanhos = model.getTamanhos();
                    Intent i = new Intent(CatalogoActivity.this, AddItemActivity.class);  //your class

                    Bundle b = new Bundle();
                    b.putStringArray("item", new String[]{nome, referencia, preco, img, tamanhos});
                    i.putExtras(b);
                    i.putExtra("codigo", codigo);
                    i.putExtra("cpf", cpf);

                    startActivity(i);
                }
            });
        }

        /*
        //Lista os itens pela ordem nome
        mAdapter = new FirebaseRecyclerAdapter<Catalogo, CatalogoViewHolder>(Catalogo.class, R.layout.list_catalogo_layout, CatalogoViewHolder.class, mRef.orderByChild("referencia").startAt(pesquisa)) {
            @Override
            public void populateViewHolder(CatalogoViewHolder catalogoViewHolder, final Catalogo cat, int position) {


                final String nome = cat.getNome();
                final String referencia = cat.getReferencia();
                final String preco = cat.getPreco();
                final String img = cat.getImg();
                final String tamanhos = cat.getTamanhos();

                catalogoViewHolder.setNome(nome);
                catalogoViewHolder.setReferencia(String.valueOf(referencia));
                catalogoViewHolder.setPreco(String.valueOf(preco));
                catalogoViewHolder.setImg(String.valueOf(img), CatalogoActivity.this);

                if (codigo == null) {
                    catalogoViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //String[] item = {cat.getNome(),cat.getReferencia()};
                            Intent i = new Intent(CatalogoActivity.this, VerCatalogoActivity.class);  //your class

                            Bundle b = new Bundle();
                            b.putStringArray("item", new String[]{nome, referencia, preco, img, tamanhos});
                            i.putExtra("cpf", cpf);
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
                            b.putStringArray("item", new String[]{nome, referencia, preco, img, tamanhos});
                            i.putExtras(b);
                            i.putExtra("codigo", codigo);
                            i.putExtra("cpf", cpf);

                            startActivity(i);

                        }
                    });
                }
            }
        };

        //Insere no nome
        recyclerView.setAdapter(mAdapter);
        */
    }

    private void listaItensFirebase() {


        final ListView mListView = (ListView) findViewById(R.id.lv_catalogo);
        mAdapter = new FirebaseListAdapter<Catalogo>(this,Catalogo.class,R.layout.list_catalogo_layout,mRef.orderByChild("referencia")){

            @Override
            protected void populateView(View v, Catalogo model, int position) {

                final ImageView imgCatalogo = (ImageView) v.findViewById(R.id.img_catalogo);
                final TextView txtReferencia = (TextView) v.findViewById(R.id.txt_catReferencia);
                final TextView txtNome = (TextView) v.findViewById(R.id.txt_catNome);
                final TextView txtPpreco = (TextView) v.findViewById(R.id.txt_catPreco);


                txtReferencia.setText(model.getReferencia());
                txtNome.setText(model.getNome());
                Picasso.with(CatalogoActivity.this).load(model.getImg()).into(imgCatalogo);
                txtPpreco.setText(model.getPreco());

            }
        };
        mListView.setAdapter(mAdapter);

        if (codigo == null) {
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Catalogo model = (Catalogo) parent.getItemAtPosition(position);

                    final String nome = String.valueOf(model.getNome());
                    final String referencia = String.valueOf(model.getReferencia());
                    final String preco = String.valueOf(model.getPreco());
                    final String img = model.getImg();
                    final String tamanhos = model.getTamanhos();
                    Intent i = new Intent(CatalogoActivity.this, VerCatalogoActivity.class);  //your class

                    Bundle b = new Bundle();
                    b.putStringArray("item", new String[]{nome, referencia, preco, img, tamanhos});
                    i.putExtra("cpf", cpf);
                    i.putExtras(b);

                    startActivity(i);
                    //finish();
                }
            });
        } else {
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Catalogo model = (Catalogo) parent.getItemAtPosition(position);

                    final String nome = String.valueOf(model.getNome());
                    final String referencia = String.valueOf(model.getReferencia());
                    final String preco = String.valueOf(model.getPreco());
                    final String img = model.getImg();
                    final String tamanhos = model.getTamanhos();
                    Intent i = new Intent(CatalogoActivity.this, AddItemActivity.class);  //your class

                    Bundle b = new Bundle();
                    b.putStringArray("item", new String[]{nome, referencia, preco, img, tamanhos});
                    i.putExtras(b);
                    i.putExtra("codigo", codigo);
                    i.putExtra("cpf", cpf);

                    startActivity(i);
                    //finish();
                }
            });
        }

        /*Lista os itens pela ordem nome
        mAdapter = new FirebaseRecyclerAdapter<Catalogo, CatalogoViewHolder>(Catalogo.class, R.layout.list_catalogo_layout, CatalogoViewHolder.class, mRef.orderByChild("nome")) {
            @Override
            public void populateViewHolder(CatalogoViewHolder catalogoViewHolder, final Catalogo cat, int position) {
                catalogoViewHolder.setNome(cat.getNome());
                catalogoViewHolder.setReferencia(String.valueOf(cat.getReferencia()));
                catalogoViewHolder.setPreco(String.valueOf(cat.getPreco()));
                catalogoViewHolder.setImg(String.valueOf(cat.getImg()), CatalogoActivity.this);

                final String nome = cat.getNome();
                final String referencia = cat.getReferencia();
                final String preco = cat.getPreco();
                final String img = cat.getImg();
                final String tamanhos = cat.getTamanhos();

                if (codigo == null) {
                    catalogoViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //String[] item = {cat.getNome(),cat.getReferencia()};
                            Intent i = new Intent(CatalogoActivity.this, VerCatalogoActivity.class);  //your class

                            Bundle b = new Bundle();
                            b.putStringArray("item", new String[]{nome, referencia, preco, img, tamanhos});
                            i.putExtra("cpf", cpf);
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
                            b.putStringArray("item", new String[]{nome, referencia, preco, img, tamanhos});
                            i.putExtras(b);
                            i.putExtra("codigo", codigo);
                            i.putExtra("cpf", cpf);

                            startActivity(i);

                        }
                    });
                }
            }
        };

        //Insere no nome
        recyclerView.setAdapter(mAdapter);
        */
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
                finish();
                return true;
            case android.R.id.home:
                Intent intent = new Intent(CatalogoActivity.this, MenuInicialActivity.class);
                intent.putExtra("cpf", cpf);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
