package com.example.win7.dakato.Activitys;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.win7.dakato.Adapters.PedidosAdapter;
import com.example.win7.dakato.Model.CreateDBHelper;
import com.example.win7.dakato.Model.PedidoContract;
import com.example.win7.dakato.Model.PedidoController;
import com.example.win7.dakato.Pedido;
import com.example.win7.dakato.R;
import com.firebase.client.ChildEventListener;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static java.security.AccessController.getContext;

public class PedidosActivity extends AppCompatActivity {

    ListView lv_pedidos;
    PedidoController db = new PedidoController(this);
    ArrayList<Pedido> itemPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //============== Padrao ==================================
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //============== Fim Padrao ==============================

        //============== Botao Flutuante =========================
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(PedidosActivity.this, CadListaActivity.class);
                startActivity(intent);
                */
                PedidoController inserir = new PedidoController(getBaseContext());
                String resultado;
                resultado = inserir.inserePedido("31/05/2017", "Status 1", "0000");
                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

                Intent i = new Intent(PedidosActivity.this, PedidosActivity.class);  //your class
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
                finish();


            }

        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //============== *Fim Botao Flutuante* ===================

        //arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list_of_rooms);

        //listView.setAdapter(arrayAdapter);

        //============== Declaracoes =============================
        final TextView txtDtEmissao = (TextView) findViewById(R.id.txtDataEmissao);
        lv_pedidos = (ListView) findViewById(R.id.lv_pedidos);
        /*
        itemPedido = new ArrayList<Pedido>();
        Pedido pedido1 = new Pedido();
        Pedido pedido2 = new Pedido();
        final PedidosAdapter pedidosAdapter = new PedidosAdapter(this, itemPedido);
        lv_pedidos.setAdapter(pedidosAdapter);
        */
        //============== Fim Declaracoes ==========================

        /*============== Insere na lista ===========================
        pedido1.setEmissao("02-06-2017");
        pedido1.setStatus("Ativo");
        itemPedido.add(pedido1);
        itemPedido.add(pedido2);
        //============== Fim Insere na lista =======================
        */
        listarPedidos();




        /*============== Lista dados Firebase ======================

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Set<String> set = new HashSet<String>();
                Iterator i = dataSnapshot.getChildren().iterator();

                while (i.hasNext()){
                    set.add(((DataSnapshot)i.next()).getKey());

                }

                list_of_rooms.clear();
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        //============== Fim Lista dados Firebase ===================*/
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
                startActivity(i);
                Toast.makeText(PedidosActivity.this, "Atualizando", Toast.LENGTH_SHORT).show();
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //================== Fim Menu Top ============================




    //================= Lista pedidos BD ========================
    public void listarPedidos() {
        PedidoController crud = new PedidoController(getBaseContext());
        final Cursor cursor = crud.carregaDados();

        String[] nomeCampos = new String[]{PedidoContract.PedidoEntry.COLUMS_EMISSAO,
                PedidoContract.PedidoEntry.COLUMS_STATUS};
        int[] idViews = new int[]{R.id.txt_emissao, R.id.txt_status};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(),
                R.layout.list_pedido_layout, cursor, nomeCampos, idViews, 0);
        lv_pedidos = (ListView) findViewById(R.id.lv_pedidos);
        lv_pedidos.setAdapter(adaptador);

        lv_pedidos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String codigo;
                cursor.moveToPosition(position);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow(PedidoContract.PedidoEntry._ID));
                Intent intent = new Intent(PedidosActivity.this, VerPedidoActivity.class);
                //Passa param
                intent.putExtra("codigo", codigo);
                startActivity(intent);
                //intent.putExtra(MainActivity.KEY_FILME, itemFilme);
            }
        });

    }
    //================= Fim Lista pedidos BD ====================

}



