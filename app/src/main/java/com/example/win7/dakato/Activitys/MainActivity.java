package com.example.win7.dakato.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.win7.dakato.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    Button btnAcessar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //implementaFB();

        btnAcessar =(Button) findViewById(R.id.btnAcessar);

    }

    @Override
    protected void onStart() {

        super.onStart();


        btnAcessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txtCpf = (TextView) findViewById(R.id.txtCpf);
                String cpf = txtCpf.getText().toString();
                if (cpf.equals("")) {
                    Toast.makeText(MainActivity.this, "Preencha com seu número de CPF.",
                            Toast.LENGTH_LONG).show();
                } else {

                    Intent intent = new Intent(MainActivity.this, MenuInicialActivity.class);
                    intent.putExtra("cpf", cpf);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "Bem Vindo", Toast.LENGTH_SHORT).show();
                    //finish();

                }
            }
        });


    }

    private void implementaFB() {
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
