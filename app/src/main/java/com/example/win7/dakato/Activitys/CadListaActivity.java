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
import android.widget.TextView;
import android.widget.Toast;

import com.example.win7.dakato.Model.PedidoController;
import com.example.win7.dakato.R;

public class CadListaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_lista);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Button btnCriar = (Button) findViewById(R.id.btnCriar);

        btnCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    PedidoController crud = new PedidoController(getBaseContext());
                    EditText txtLista = (EditText) findViewById(R.id.edt_lista);
                    String lista = txtLista.getText().toString();
                    String resultado;
                    resultado = crud.inserePedido("31/05/2017","Status 1","0000");
                    Intent intent = new Intent(CadListaActivity.this, PedidosActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

            }
        });
    }

}
