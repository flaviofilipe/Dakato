package com.example.win7.dakato.Activitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.win7.dakato.Adapters.PedidosAdapter;
import com.example.win7.dakato.Model.PedidoContract;
import com.example.win7.dakato.Model.PedidoController;
import com.example.win7.dakato.R;

import java.text.SimpleDateFormat;

public class PedidosActivity extends AppCompatActivity {

    ListView lv_pedidos;
    String cpf;
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //============== Padrao ==================================
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //============== Fim Padrao ==============================

        cpf = this.getIntent().getStringExtra("cpf");
        data = pegaData();

        //============== Botao Flutuante =========================
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PedidoController inserir = new PedidoController(getBaseContext());
                String resultado;

                resultado = inserir.inserePedido(data, cpf);
                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

                Intent i = new Intent(PedidosActivity.this, PedidosActivity.class);  //your class
                i.putExtra("cpf", cpf);
                startActivity(i);
                finish();
            }

        });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //============== *Fim Botao Flutuante* ===================

        //============== Declaracoes =============================
        lv_pedidos = (ListView) findViewById(R.id.lv_pedidos);

        listarPedidos();

    }

    private String pegaData() {
        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy");
        String dateString = sdf.format(date);
        return dateString;
    }

    //================== Menu Top ==============================
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
                Intent i = new Intent(PedidosActivity.this, PedidosActivity.class);  //your class
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                i.putExtra("cpf", cpf);
                startActivity(i);
                Toast.makeText(PedidosActivity.this, "Atualizando", Toast.LENGTH_SHORT).show();
                finish();
                return true;

            case android.R.id.home:
                /*
                Intent intent = new Intent(PedidosActivity.this, MenuInicialActivity.class);
                intent.putExtra("cpf", cpf);
                startActivity(intent);
                finish();
                */
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //================== Fim Menu Top ============================

    //================= Lista pedidos BD ========================
    public void listarPedidos() {
        final PedidoController crud = new PedidoController(getBaseContext());
        final Cursor cursor = crud.carregaDados();
        lv_pedidos = (ListView) findViewById(R.id.lv_pedidos);
        PedidosAdapter adapter = new PedidosAdapter(this, cursor);

        lv_pedidos.setAdapter(adapter);

        lv_pedidos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String codigo;
                cursor.moveToPosition(position);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow(PedidoContract.PedidoEntry._ID));
                Intent intent = new Intent(PedidosActivity.this, VerPedidoActivity.class);
                //Passa param
                intent.putExtra("codigo", codigo);
                intent.putExtra("cpf", cpf);
                startActivity(intent);
            }
        });

        lv_pedidos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final PedidoController excluir = new PedidoController(getBaseContext());
                final String codigo;
                cursor.moveToPosition(position);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow(PedidoContract.PedidoEntry._ID));

                new AlertDialog.Builder(PedidosActivity.this)
                        .setTitle("Deletar lista")
                        .setMessage("Tem certeza que pretende deletar a lista " + codigo)
                        .setPositiveButton("Deletar",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        excluir.excluiPedido(codigo);
                                        Toast.makeText(getApplicationContext(), "Lista excluida com sucesso", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(PedidosActivity.this, PedidosActivity.class);  //your class
                                        intent.putExtra("cpf", cpf);
                                        startActivity(intent);

                                        finish();
                                    }
                                })
                        .setNegativeButton("Cancelar", null).setIcon(android.R.drawable.ic_menu_delete)
                        .show();
                return true;
            }
        });
    }
    //================= Fim Lista pedidos BD ====================

}



