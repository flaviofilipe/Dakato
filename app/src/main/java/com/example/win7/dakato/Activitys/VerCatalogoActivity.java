package com.example.win7.dakato.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.win7.dakato.R;
import com.squareup.picasso.Picasso;

public class VerCatalogoActivity extends AppCompatActivity {

    String[] item;

    private TextView titulo;
    private TextView referencia;
    private TextView preco;
    private TextView tamanho;
    private ImageView img;

    String cpf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_catalogo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Item Adiconado a lista", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Declaracoes
        titulo = (TextView) findViewById(R.id.txt_titulo);
        referencia = (TextView) findViewById(R.id.txt_referencia);
        preco = (TextView) findViewById(R.id.txt_preco);
        tamanho = (TextView) findViewById(R.id.txt_tamanho);
        img = (ImageView) findViewById(R.id.img_verCatalogo);
        cpf = this.getIntent().getStringExtra("cpf");
        //Fim declaracoes

        //Recebe ID

        Bundle b=this.getIntent().getExtras();
        item = b.getStringArray("item");

        titulo.setText(item[0]);
        referencia.setText(item[1]);
        preco.setText(item[2]);
        Picasso.with(this).load(item[3]).fit().centerCrop().into(img);
        tamanho.setText(item[4]);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(VerCatalogoActivity.this, CatalogoActivity.class);
                intent.putExtra("cpf", cpf);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
