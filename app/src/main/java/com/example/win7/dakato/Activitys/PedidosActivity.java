package com.example.win7.dakato.Activitys;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.win7.dakato.Adapters.PedidosAdapter;
import com.example.win7.dakato.Model.CreateDBHelper;
import com.example.win7.dakato.Model.PedidoContract;
import com.example.win7.dakato.Model.PedidoController;
import com.example.win7.dakato.Pedido;
import com.example.win7.dakato.R;

import java.util.ArrayList;
import java.util.List;

public class PedidosActivity extends AppCompatActivity {

    ListView lv_pedidos;
    PedidoController db = new PedidoController(this);
    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(PedidosActivity.this, CadListaActivity.class);
                startActivity(intent);
                */
                PedidoController inserir = new PedidoController(getBaseContext());
                String resultado;
                resultado = inserir.inserePedido("31/05/2017","Status 1","0000");
                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //String[] pedidos = new String[]{"Pedido1", "Pedido2"};
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pedidos);

        lv_pedidos = (ListView) findViewById(R.id.lv_pedidos);
        listarPedidos();


        /*Add na lista
        final ArrayList<Pedido> pedido = new ArrayList<Pedido>();
        Pedido pedido1 = new Pedido();
        pedido1.setEmissao("29/05/2015");
        pedido1.setStatus("Aprovado");
        pedido1.setCpf("00000");
        for (int i = 0; i <= 10; i++) {
            pedido.add(pedido1);
        }
        PedidosAdapter pedidosAdapter = new PedidosAdapter(this, pedido);
        lv_pedidos.setAdapter(pedidosAdapter);


        lv_pedidos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Pedido itemPedido = pedido.get(position);
                Intent intent = new Intent(PedidosActivity.this, VerPedidoActivity.class);
                //intent.putExtra(MainActivity.KEY_FILME, itemFilme);
                startActivity(intent);
            }
        });
        */
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_atualizar, menu);
        return true;
    }

    public void listarPedidos(){
        /*
        arrayList = new ArrayList<String>();
        List<Pedido> pedidos = db.listaTodosPedidos();
        adapter = new ArrayAdapter<String>(PedidosActivity.this, R.layout.list_pedido_layout,arrayList);
        lv_pedidos.setAdapter(adapter);
        for(Pedido p : pedidos){
            //Log.d("Lista","\nID:"+p.getId()+" Emissão: "+p.getEmissao());
            arrayList.add(p.getCpf());
            adapter.notifyDataSetChanged();
            */
            //-----------------------------------------------------------------

        PedidoController crud = new PedidoController(getBaseContext());
        Cursor cursor = crud.carregaDados();

        String[] nomeCampos = new String[] {PedidoContract.PedidoEntry.COLUMS_EMISSAO,
                PedidoContract.PedidoEntry.COLUMS_STATUS};
        int[] idViews = new int[] {R.id.txt_emissao, R.id.txt_status};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(),
                R.layout.list_pedido_layout,cursor,nomeCampos,idViews, 0);
        lv_pedidos = (ListView)findViewById(R.id.lv_pedidos);
        lv_pedidos.setAdapter(adaptador);

        }

    }



