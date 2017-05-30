package com.example.win7.dakato.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.win7.dakato.Adapters.PedidosAdapter;
import com.example.win7.dakato.Pedido;
import com.example.win7.dakato.R;

import java.util.ArrayList;

public class PedidosActivity extends AppCompatActivity {

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
                Intent intent = new Intent(PedidosActivity.this, CadListaActivity.class);
                startActivity(intent);
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //String[] pedidos = new String[]{"Pedido1", "Pedido2"};
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pedidos);

        ListView lv_pedidos = (ListView) findViewById(R.id.lv_pedidos);
        //Add na lista
        final ArrayList<Pedido> pedido = new ArrayList<Pedido>();
        Pedido pedido1 = new Pedido("f200abc","29/05/2015","Aprovado","00000");
        for(int i=0;i<=10;i++) {
            pedido.add(pedido1);
        }
        PedidosAdapter pedidosAdapter = new PedidosAdapter(this,pedido);
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
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_atualizar, menu);
        return true;
    }

}
