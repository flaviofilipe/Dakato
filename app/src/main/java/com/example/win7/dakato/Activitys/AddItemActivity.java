package com.example.win7.dakato.Activitys;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.win7.dakato.Funcoes;
import com.example.win7.dakato.Model.PedidoContract;
import com.example.win7.dakato.Model.PedidoController;
import com.example.win7.dakato.Model.VerPedidoController;
import com.example.win7.dakato.R;
import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddItemActivity extends AppCompatActivity {

    String codigo;
    String cpf;
    String[] item;
    private TextView titulo;
    private TextView referencia;
    private TextView preco;
    private TextView tamanho;
    private ImageView img;

    private EditText edtP;
    private EditText edtPP;
    private EditText edtM;
    private EditText edtG;
    private EditText edtGG;
    private EditText edtObs;

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

        edtP = (EditText) findViewById(R.id.edt_p);
        edtPP = (EditText) findViewById(R.id.edt_pp);
        edtM = (EditText) findViewById(R.id.edt_m);
        edtG = (EditText) findViewById(R.id.edt_g);
        edtGG = (EditText) findViewById(R.id.edt_GG);
        edtObs = (EditText) findViewById(R.id.edt_Obs);

        btnAdd = (Button) findViewById(R.id.btnEnviarPedido);
        //Fim declaracoes

        //Recebe ID

        codigo = this.getIntent().getStringExtra("codigo");
        cpf = this.getIntent().getStringExtra("cpf");
        Bundle b = this.getIntent().getExtras();
        item = b.getStringArray("item");
        String tamanhos = item[4];


        titulo.setText(item[0]);
        referencia.setText(item[1]);
        preco.setText(item[2]);
        tamanho.setText(tamanhos);
        Picasso.with(this).load(item[3]).into(img);


        if (tamanhos == null) {
            tamanhos = " ";
        }
        final List<String> t = Arrays.asList(tamanhos.split(","));
        //P
        if (t.contains("p") || t.contains("P")) {
            edtP.setEnabled(true);
        } else {
            edtP.setEnabled(false);
            edtP.setHint("Indisponível");
        }
        //PP
        if (t.contains("pp") || t.contains("PP")) {
            edtPP.setEnabled(true);
        } else {
            edtPP.setEnabled(false);
            edtPP.setHint("Indisponível");
        }
        //M
        if (t.contains("m") || t.contains("M")) {
            edtM.setEnabled(true);
        } else {
            edtM.setEnabled(false);
            edtM.setHint("Indisponível");
        }
        //G
        if (t.contains("g") || t.contains("G")) {
            edtG.setEnabled(true);
        } else {
            edtG.setEnabled(false);
            edtG.setHint("Indisponível");
        }
        //GG
        if (t.contains("gg") || t.contains("GG")) {
            edtGG.setEnabled(true);
        } else {
            edtGG.setEnabled(false);
            edtGG.setHint("Indisponível");
        }


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VerPedidoController inserir = new VerPedidoController(getBaseContext());
                PedidoController totalPedido = new PedidoController(getBaseContext());
                Cursor cursor = totalPedido.carregaDadoById(codigo);
                Double total = cursor.getDouble(cursor.getColumnIndexOrThrow(PedidoContract.PedidoEntry.COLUMS_TOTAL));

                String resultado;

                //=============== Somar produtos ========================
                //Double precoTotal = Funcoes.precoEntrada(preco.getText().toString());
                Double precoTotal = somaProdutos(
                        edtPP.getText().toString(),
                        edtP.getText().toString(),
                        edtM.getText().toString(),
                        edtG.getText().toString(),
                        edtGG.getText().toString());

                //=============== *Fim Somar produtos* ========================
                resultado = inserir.inserePedido(
                        codigo,
                        item[1],
                        edtPP.getText().toString(),
                        edtP.getText().toString(),
                        edtM.getText().toString(),
                        edtG.getText().toString(),
                        edtGG.getText().toString(),
                        edtObs.getText().toString(),
                        precoTotal
                );
                total += precoTotal;
                inserir.updateTotal(codigo, total);
                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

                Intent i = new Intent(AddItemActivity.this, VerPedidoActivity.class);  //your class
                i.putExtra("codigo", codigo);
                i.putExtra("cpf", cpf);
                startActivity(i);
                finish();
            }
        });

    }


    private Double somaProdutos(String PP, String P, String M, String G, String GG) {
        int valPP;
        int valP;
        int valM;
        int valG;
        int valGG;

        if (!PP.equals("")) {
            valPP = Integer.parseInt(PP);
        } else {
            valPP = 0;
        }
        if (!P.equals("")) {
            valP = Integer.parseInt(P);
        } else {
            valP = 0;
        }
        if (!M.equals("")) {
            valM = Integer.parseInt(M);
        } else {
            valM = 0;
        }
        if (!G.equals("")) {
            valG = Integer.parseInt(G);
        } else {
            valG = 0;
        }
        if (!GG.equals("")) {
            valGG = Integer.parseInt(GG);
        } else {
            valGG = 0;
        }

        Double precoTotal = Funcoes.precoEntrada(preco.getText().toString());
        int quant = valPP + valP + valM + valG + valGG;
        precoTotal *= quant;
        return Funcoes.precoEntrada(String.valueOf(precoTotal));
    }


}