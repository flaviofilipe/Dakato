package com.example.win7.dakato.Activitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.win7.dakato.Adapters.VerPedidosItensAdapter;
import com.example.win7.dakato.Model.PedidoController;
import com.example.win7.dakato.Model.VerPedidoController;
import com.example.win7.dakato.Model.VerPedidosContract;
import com.example.win7.dakato.R;
import com.example.win7.dakato.VerPedidoItens;

import java.util.ArrayList;

public class VerPedidoActivity extends AppCompatActivity {

    String cpf;
    PedidoController crud;
    String codigo;
    ListView lv_verPedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_pedido);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Recebe ID
        codigo = this.getIntent().getStringExtra("codigo");
        crud = new PedidoController(getBaseContext());
        cpf = this.getIntent().getStringExtra("cpf");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerPedidoActivity.this, CatalogoActivity.class);
                intent.putExtra("codigo", codigo);
                startActivity(intent);
                finish();


            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listarVerPedidos();

        //========== Decrlaracoes ===========
        lv_verPedidos = (ListView) findViewById(R.id.lv_verpedidos);

    }

    private void listarVerPedidos() {
        VerPedidoController crud = new VerPedidoController(getBaseContext());
        final Cursor cursor = crud.carregaDadoByPedidos(codigo);

        String[] nomeCampos = new String[]{
                VerPedidosContract.VerPedidosEntry._VP_ID,
                VerPedidosContract.VerPedidosEntry.COLUMS_VP_PEDIDO_ID,
                VerPedidosContract.VerPedidosEntry.COLUMS_VP_ITEMCATALOGO_ID,
                VerPedidosContract.VerPedidosEntry.COLUMS_VP_P,
                VerPedidosContract.VerPedidosEntry.COLUMS_VP_PP,
                VerPedidosContract.VerPedidosEntry.COLUMS_VP_M,
                VerPedidosContract.VerPedidosEntry.COLUMS_VP_G,
                VerPedidosContract.VerPedidosEntry.COLUMS_VP_GG,
                VerPedidosContract.VerPedidosEntry.COLUMS_VP_OBS
        };
        int[] idViews = new int[]{
                R.id.txt_vp_id,
                R.id.txt_pedidoId,
                R.id.txt_vp_itemCatalogo,
                R.id.txt_vp_pp,
                R.id.txt_vp_p,
                R.id.txt_vp_m,
                R.id.txt_vp_g,
                R.id.txt_vp_gg,
                R.id.txt_vp_obs
        };

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(),
                R.layout.list_ver_pedido_layout, cursor, nomeCampos, idViews, 0);
        lv_verPedidos = (ListView) findViewById(R.id.lv_verpedidos);
        lv_verPedidos.setAdapter(adaptador);
        lv_verPedidos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                cursor.moveToPosition(position);
                String id_item = cursor.getString(cursor.getColumnIndexOrThrow(VerPedidosContract.VerPedidosEntry._ID));
                final String pedido_id = cursor.getString(cursor.getColumnIndexOrThrow(VerPedidosContract.VerPedidosEntry.COLUMS_VP_PEDIDO_ID));
                final String catalogo_id = cursor.getString(cursor.getColumnIndexOrThrow(VerPedidosContract.VerPedidosEntry.COLUMS_VP_ITEMCATALOGO_ID));
                final String obs = cursor.getString(cursor.getColumnIndexOrThrow(VerPedidosContract.VerPedidosEntry.COLUMS_VP_OBS));
                final String p = cursor.getString(cursor.getColumnIndexOrThrow(VerPedidosContract.VerPedidosEntry.COLUMS_VP_P));
                final String pp = cursor.getString(cursor.getColumnIndexOrThrow(VerPedidosContract.VerPedidosEntry.COLUMS_VP_PP));
                final String m = cursor.getString(cursor.getColumnIndexOrThrow(VerPedidosContract.VerPedidosEntry.COLUMS_VP_M));
                final String g = cursor.getString(cursor.getColumnIndexOrThrow(VerPedidosContract.VerPedidosEntry.COLUMS_VP_G));
                final String gg = cursor.getString(cursor.getColumnIndexOrThrow(VerPedidosContract.VerPedidosEntry.COLUMS_VP_GG));


                new AlertDialog.Builder(VerPedidoActivity.this)
                        .setTitle("Compartilhar")
                        .setMessage("Deseja compartinhar este item? " + cursor.getCount())
                        .setPositiveButton("sim",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        //Compartilha no whats
                                        // cria a intent e define a ação
                                        Intent intent = new Intent(Intent.ACTION_SEND);
                                        // tipo de conteúdo da intent
                                        intent.setType("text/plain");
                                        // string a ser enviada para outra intent
                                        intent.putExtra(Intent.EXTRA_TEXT, "Nova solicitação \nCPF: " + cpf
                                                + " \nCatalogo id: " + catalogo_id
                                                + " \nPP: " + pp
                                                + " \nP: " + p
                                                + " \nM: " + m
                                                + " \nG: " + g
                                                + " \nGG: " + gg
                                                + " \nObs: " + obs
                                        );
                                        // inicia a intent
                                        startActivity(intent);
                                    }
                                })
                        .setNegativeButton("não", null)
                        .show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_ver_pedido, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_deletar:
                final PedidoController excluir = new PedidoController(getBaseContext());

                new AlertDialog.Builder(this)
                        .setTitle("Deletar lista")
                        .setMessage("Tem certeza que pretende deletar a lista " + codigo)
                        .setPositiveButton("sim",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        excluir.excluiPedido(codigo);
                                        Toast.makeText(getApplicationContext(), "Lista excluida com sucesso", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(VerPedidoActivity.this, PedidosActivity.class);  //your class
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);

                                        finish();
                                    }
                                })
                        .setNegativeButton("não", null)
                        .show();
                return true;
            case R.id.menu_compartilhar:
                shareText();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void shareText() {
        ArrayList<String> lista = new ArrayList<String>();

        VerPedidoController crud = new VerPedidoController(getBaseContext());
        final Cursor cursor = crud.carregaDadoByPedidos(codigo);
        int quant = cursor.getCount();
        Cursor item;
        String id[] = new String[0];

        String itemcatalogo_id = cursor.getString(cursor.getColumnIndex(VerPedidosContract.VerPedidosEntry.COLUMS_VP_ITEMCATALOGO_ID));
        String obs = cursor.getString(cursor.getColumnIndex(VerPedidosContract.VerPedidosEntry.COLUMS_VP_OBS));

        Toast.makeText(VerPedidoActivity.this, String.valueOf(lista), Toast.LENGTH_SHORT).show();


        /*Compartilha no whats
        // cria a intent e define a ação
        Intent intent = new Intent( Intent.ACTION_SEND );
        // tipo de conteúdo da intent
        intent.setType( "text/plain" );
        // string a ser enviada para outra intent
        intent.putExtra( Intent.EXTRA_TEXT, "Mensagem teste pelo app. Pedido id: "+pedido_id
                + " Catalogo id: "+itemcatalogo_id
                + " Obs: " +obs
        );
        // inicia a intent
        startActivity( intent );
        */
    }

}
