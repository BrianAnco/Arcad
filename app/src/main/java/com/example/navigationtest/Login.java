package com.example.navigationtest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Login extends AppCompatActivity {

    HttpURLConnection conexion;
    EditText txtUsuario, txtPassword;
    Button btnRegistro;
    Button btnIngresar;
    //private SharedPreferences prefs;
    //private SharedPreferences.Editor editor;
    private boolean login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //prefs = getSharedPreferences("Config", MODE_PRIVATE);
        /*login = prefs.getBoolean("onlogin", false);
        if (login) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }*/
        setContentView(R.layout.activity_login);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
        txtUsuario = findViewById(R.id.user);
        txtPassword = findViewById(R.id.password);
        btnIngresar = findViewById(R.id.ingresar);
        btnRegistro = findViewById(R.id.registrate);

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentRegistro = new Intent(Login.this, Registro.class);

                Login.this.startActivity(intentRegistro);
            }

        });
    }

    private int ValidaDatos(String u_usuario, String u_password) {
        int result=3;
        try {
            String miurl="http://192.168.1.46/Arcad_Login.php?u_usuario=" + URLEncoder.encode(u_usuario, "UTF-8")+"&u_password="+URLEncoder.encode(u_password, "UTF-8");
            URL url=new URL(miurl);
            conexion = (HttpURLConnection) url.openConnection();
            Log.e("Conexion:",conexion+"");
            if (conexion.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                String linea = reader.readLine();
                if (linea.equals("success"))
                    result=0;
                else
                    result=1;
            } else {
                Log.e("mitag", conexion.getResponseMessage());
            }
            Log.e("responsecode", conexion.getResponseCode()+"");
            Log.e("urlconecction",HttpURLConnection.HTTP_OK+"");
            Log.e("result", result+"");

        } catch (Exception e) {
            Log.e("mitag", e.getMessage(), e);
        } finally {
            if (conexion!=null) conexion.disconnect();
            return result;
        }
    }


    public void onLogin(View view) {
        int V = ValidaDatos(txtUsuario.getText().toString(), txtPassword.getText().toString());
        if (V == 0) {
            /*editor = prefs.edit();
            editor.putBoolean("onlogin", true);
            editor.apply();*/
            Intent i = new Intent(this, MainActivity.class);
            i.putExtra("usuario_sesion",txtUsuario.getText().toString());
            Log.e("usuario_sesion_login:",txtUsuario.getText().toString());
            startActivity(i);
            finish();
        } else
            Toast.makeText(this, "Ingreso Fallido", Toast.LENGTH_SHORT).show();
    }
    }
