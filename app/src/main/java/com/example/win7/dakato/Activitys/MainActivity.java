package com.example.win7.dakato.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.win7.dakato.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAcessar = (Button) findViewById(R.id.btnAcessar);
        TextView txtCadastro = (TextView) findViewById(R.id.txtCadastro);

        btnAcessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txtCpf = (TextView) findViewById(R.id.txtCpf);
                String cpf = txtCpf.getText().toString();
                if(cpf.equals("")){
                    Intent intent = new Intent(MainActivity.this,MenuInicialActivity.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "Bem Vindo"+txtCpf.getText(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Usuário inválido. Por favor solicite o cadastro."+txtCpf.getText(),
                            Toast.LENGTH_LONG).show();

                }
            }
        });

    }
}
