package br.com.redewsouza.win7.dkatto.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import br.com.redewsouza.win7.dkatto.R;

public class AjudaActivity extends AppCompatActivity {

    Button btnAjudaLista;
    Button btnAjudaCatalogo;
    Button btnAjudaCompartilhar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajuda);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnAjudaLista = (Button) findViewById(R.id.btn_ajudaLista);
        btnAjudaLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AjudaActivity.this, AjudaListaActivity.class);
                startActivity(intent);
                onPause();
            }
        });

        btnAjudaCatalogo = (Button) findViewById(R.id.btn_ajudaVerCatalogo);
        btnAjudaCatalogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AjudaActivity.this, AjudaCatalogoActivity.class);
                startActivity(intent);
                onPause();
            }
        });

        btnAjudaCompartilhar = (Button) findViewById(R.id.btn_ajudaCompartilharItens);
        btnAjudaCompartilhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AjudaActivity.this, AjudaEnvListaActivity.class);
                startActivity(intent);
                onPause();
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
