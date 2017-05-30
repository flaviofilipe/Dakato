package com.example.win7.dakato.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

import com.example.win7.dakato.Adapters.VerPedidosItensAdapter;
import com.example.win7.dakato.R;
import com.example.win7.dakato.VerPedidoItens;

import java.util.ArrayList;

public class VerPedidoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_pedido);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerPedidoActivity.this, AddItemActivity.class);
                startActivity(intent);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //List view
        ListView lv_verPedidos = (ListView) findViewById(R.id.lv_verpedidos);
        //Add na lista
        final ArrayList<VerPedidoItens> verPedido = new ArrayList<VerPedidoItens>();
        VerPedidoItens pedido1 = new VerPedidoItens(1, 1, 3, 5, 0, 0, 1, 0, "teste");
        for (int i = 0; i <= 10; i++) {
            verPedido.add(pedido1);
        }
        VerPedidosItensAdapter verPedidosItensAdapter = new VerPedidosItensAdapter(this, verPedido);
        lv_verPedidos.setAdapter(verPedidosItensAdapter);
        //Fim list view

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_ver_pedido, menu);
        return true;
    }

}
