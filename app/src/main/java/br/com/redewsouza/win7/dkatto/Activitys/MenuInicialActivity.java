package br.com.redewsouza.win7.dkatto.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.redewsouza.win7.dkatto.R;

public class MenuInicialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicial);

        Button btnPedido = (Button) findViewById(R.id.btnPedido);
        Button btnCatalogo = (Button) findViewById(R.id.btnCatalogo);
        Button btnAjuda = (Button) findViewById(R.id.btnAjuda);
        final String cpf = this.getIntent().getStringExtra("cpf");

        //Pedido
        btnPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuInicialActivity.this,PedidosActivity.class);
                intent.putExtra("cpf", cpf);
                startActivity(intent);
                onPause();
            }
        });

        //Catalogo
        btnCatalogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuInicialActivity.this,CatalogoActivity.class);
                intent.putExtra("cpf", cpf);
                startActivity(intent);
                onPause();
            }
        });

        //Ajuda
        btnAjuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuInicialActivity.this, AjudaActivity.class);
                intent.putExtra("cpf", cpf);
                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                onPause();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
