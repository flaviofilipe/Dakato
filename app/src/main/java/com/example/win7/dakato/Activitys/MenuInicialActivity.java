package com.example.win7.dakato.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.win7.dakato.R;

public class MenuInicialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicial);

        Button btnPedido = (Button) findViewById(R.id.btnPedido);
        Button btnCatalogo = (Button) findViewById(R.id.btnCatalogo);
        Button btnEnviarPedido = (Button) findViewById(R.id.btnEnviarPedido);

        //Pedido
        btnPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuInicialActivity.this,PedidosActivity.class);
                startActivity(intent);
            }
        });

        //Catalogo
        btnCatalogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuInicialActivity.this,CatalogoActivity.class);
                startActivity(intent);
            }
        });

    }
}
