package com.example.navigationtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Registro extends AppCompatActivity implements View.OnClickListener {

    EditText txtNombre, txtUsuario, txtPassword, txtNacimiento, txtPais, txtCelular;
    Spinner spnSexo;
    Button btnCrear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        txtNombre = findViewById(R.id.txtNombre);
        txtUsuario = findViewById(R.id.txtUsuario);
        txtPassword = findViewById(R.id.txtPassword);
        txtNacimiento = findViewById(R.id.dtNacimiento);
        txtPais = findViewById(R.id.txtPais);
        txtCelular = findViewById(R.id.txtCelular);
        spnSexo = findViewById(R.id.spnSexo);
        btnCrear = findViewById(R.id.registrate);

        btnCrear.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}