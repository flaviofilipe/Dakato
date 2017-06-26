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
    private int posicaoLV;

    String cpf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_catalogo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Declaracoes
        titulo = (TextView) findViewById(R.id.txt_titulo);
        referencia = (TextView) findViewById(R.id.txt_referencia);
        preco = (TextView) findViewById(R.id.txt_preco);
        tamanho = (TextView) findViewById(R.id.txt_tamanho);
        img = (ImageView) findViewById(R.id.img_verCatalogo);
        cpf = this.getIntent().getStringExtra("cpf");
        posicaoLV = this.getIntent().getIntExtra("posicao",0);
        //Fim declaracoes

        //Recebe ID

        Bundle b=this.getIntent().getExtras();
        item = b.getStringArray("item");

        titulo.setText(item[0]);
        referencia.setText(item[1]);
        preco.setText(item[2]);
        Picasso.with(this).load(item[3]).placeholder(R.drawable.semimagem).into(img);
        tamanho.setText(item[4]);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picasso.with(VerCatalogoActivity.this)
                        .load(item[3])
                        .centerCrop()
                        .into(img);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                /*
                Intent intent = new Intent(VerCatalogoActivity.this, CatalogoActivity.class);
                intent.putExtra("cpf", cpf);
                intent.putExtra("posicao", posicaoLV);
                startActivity(intent);

                finish();
                */
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
