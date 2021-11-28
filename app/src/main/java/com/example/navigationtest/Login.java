package com.example.navigationtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {

    Button btnRegistro;
    Button btnIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnIngresar = findViewById(R.id.ingresar);
        btnRegistro = findViewById(R.id.registrate);

        btnRegistro.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intentRegistro = new Intent(Login.this, Registro.class);
                Login.this.startActivity(intentRegistro);
            }

        });

        btnIngresar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intentIngresar = new Intent(Login.this, MainActivity.class);
                Login.this.startActivity(intentIngresar);
            }

        });
    }
}