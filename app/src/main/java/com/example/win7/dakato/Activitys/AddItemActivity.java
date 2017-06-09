package com.example.win7.dakato.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.win7.dakato.Model.VerPedidoController;
import com.example.win7.dakato.R;
import com.squareup.picasso.Picasso;

public class AddItemActivity extends AppCompatActivity {

    String codigo;
    String cpf;
    String[] item;
    private TextView titulo;
    private TextView referencia;
    private TextView preco;
    private TextView tamanho;
    private ImageView img;

    private EditText P;
    private EditText PP;
    private EditText M;
    private EditText G;
    private EditText GG;
    private EditText Obs;

    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Declaracoes
        titulo = (TextView) findViewById(R.id.txt_titulo);
        referencia = (TextView) findViewById(R.id.txt_referencia);
        preco = (TextView) findViewById(R.id.txt_preco);
        tamanho = (TextView) findViewById(R.id.txt_tamanho);
        img = (ImageView) findViewById(R.id.img_addCatalogo);

        P = (EditText) findViewById(R.id.edt_p);
        PP = (EditText) findViewById(R.id.edt_pp);
        M = (EditText) findViewById(R.id.edt_m);
        G = (EditText) findViewById(R.id.edt_g);
        GG = (EditText) findViewById(R.id.edt_GG);
        Obs = (EditText) findViewById(R.id.edt_Obs);

        btnAdd = (Button) findViewById(R.id.btnEnviarPedido);
        //Fim declaracoes

        //Recebe ID

        codigo = this.getIntent().getStringExtra("codigo");
        cpf = this.getIntent().getStringExtra("cpf");
        Bundle b=this.getIntent().getExtras();
        item = b.getStringArray("item");


        titulo.setText(item[0]);
        referencia.setText(item[1]);
        preco.setText(item[2]);
        Picasso.with(this).load(item[3]).fit().centerCrop().into(img);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VerPedidoController inserir = new VerPedidoController(getBaseContext());
                String resultado;
                resultado = inserir.inserePedido(
                        codigo,
                        item[1],
                        P.getText().toString(),
                        PP.getText().toString(),
                        M.getText().toString(),
                        G.getText().toString(),
                        GG.getText().toString(),
                        Obs.getText().toString()
                );
                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

                Intent i = new Intent(AddItemActivity.this, VerPedidoActivity.class);  //your class
                i.putExtra("codigo", codigo);
                i.putExtra("cpf", cpf);
                startActivity(i);
                finish();
            }
        });


    }

}
