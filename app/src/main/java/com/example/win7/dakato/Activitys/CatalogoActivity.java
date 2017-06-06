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
import android.view.Menu;
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
import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.api.model.StringList;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CatalogoActivity extends AppCompatActivity {

    ImageButton btnPesquisar;
    ListView lv_catalogo;
    RecyclerView recyclerView;


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

        final Catalogo catalogo1 = new Catalogo();
        catalogo1.setNome("aaa");
        catalogo1.setPreco(123.00);
        catalogo1.setReferencia("r123");
        catalogo1.setImg(String.valueOf(R.drawable.ic_logo_dakatto));
        //final ImageView img = catalogo1.getImagemUrl();


        /*Recebendo imagem firebase/picasso
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReferenceFromUrl("gs://dkato-790c9.appspot.com/").child("download.jpg");
        //Pegar tamanho da tela
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        final int height = (displayMetrics.heightPixels - (displayMetrics.heightPixels/2));
        final int width = (displayMetrics.widthPixels - 16);
        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                //Picasso.with(CatalogoActivity.this).load(uri.toString()).resize(width,height).into(img);
                catalogo1.setImg(String.valueOf(uri.toString()));
            }
        });
        */
        //RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.rv_Catalogo);



        List<Catalogo> c = new ArrayList<Catalogo>();
        for(int i=0;i<=10;i++) {
            c.add(catalogo1);
        }

        recyclerView.setAdapter(new CatalogoAdapter(c,this));
        RecyclerView.LayoutManager layout = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layout);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_atualizar, menu);
        return true;
    }


    public void listarCatalogo() {
        CatalogoController crud = new CatalogoController(getBaseContext());
        final Cursor cursor = crud.carregaDados();

        String[] nomeCampos = new String[]{
                CatalogoContract.CatalogoEntry.COLUMS_REFERENCIA,
                CatalogoContract.CatalogoEntry.COLUMS_NOME,
                CatalogoContract.CatalogoEntry.COLUMS_PRECO
        };
        int[] idViews = new int[]{R.id.txt_catReferencia, R.id.txt_catNome, R.id.txt_catPreco};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(),
                R.layout.list_catalogo_layout, cursor, nomeCampos, idViews, 0);
        lv_catalogo = (ListView) findViewById(R.id.lv_pedidos);
        lv_catalogo.setAdapter(adaptador);

        lv_catalogo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String codigo;
                cursor.moveToPosition(position);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow(CatalogoContract.CatalogoEntry._ID));
                Intent intent = new Intent(CatalogoActivity.this, VerCatalogoActivity.class);
                //Passa param
                intent.putExtra("codigo", codigo);
                startActivity(intent);
                finish();
                //intent.putExtra(MainActivity.KEY_FILME, itemFilme);
            }
        });

    }


}
