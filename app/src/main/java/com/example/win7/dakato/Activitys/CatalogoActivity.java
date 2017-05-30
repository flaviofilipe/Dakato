package com.example.win7.dakato.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.win7.dakato.Adapters.CatalogoAdapter;
import com.example.win7.dakato.Catalogo;
import com.example.win7.dakato.R;

import java.util.ArrayList;

public class CatalogoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //List view
        ListView lvCatalogo= (ListView) findViewById(R.id.lv_catalogo);
        //Add na lista
        final ArrayList<Catalogo> catalogo = new ArrayList<Catalogo>();
        Catalogo catalogo1 = new Catalogo("1","1234","Nome",10.0,R.drawable.ic_logo_dakatto,true,true,true,true,true);
        for(int i=0;i<=10;i++) {
            catalogo.add(catalogo1);
        }
        CatalogoAdapter catalogoAdapter = new CatalogoAdapter(this,catalogo);
        lvCatalogo.setAdapter(catalogoAdapter);
        //Fim List view

        lvCatalogo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Catalogo itemCatalogo = catalogo.get(position);
                Intent intent = new Intent(CatalogoActivity.this, VerCatalogoActivity.class);
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
