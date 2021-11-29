package com.example.navigationtest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Registro extends AppCompatActivity implements View.OnClickListener {

    EditText txtNombre, txtUsuario, txtPassword, txtNacimiento, txtPais, txtCelular;
    Spinner spnSexo;
    Button btnCrear;
    HttpURLConnection conexion;

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
        final String u_nombre = txtNombre.getText().toString();
        final String u_usuario = txtUsuario.getText().toString();
        final String u_password = txtPassword.getText().toString();
        final String u_nacimiento = txtNacimiento.getText().toString();
        final String u_pais = txtPais.getText().toString();
        final String u_celular = txtCelular.getText().toString();
        String u_sexo = "0";
        if(spnSexo.getSelectedItem().toString().equals("Masculino")){
            u_sexo = "0";
        }
        else{
            u_sexo = "1";
        }

        final String u_perfil = "0";

        String finalU_sexo = u_sexo;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    String miurl="http://192.168.1.46/Arcad_Registro.php?u_nombre=" + URLEncoder.encode(u_nombre, "UTF-8") +
                            "&u_usuario=" + URLEncoder.encode(u_usuario, "UTF-8") +
                            "&u_password=" + URLEncoder.encode(u_password, "UTF-8") +
                            "&u_nacimiento=" + URLEncoder.encode(u_nacimiento, "UTF-8") +
                            "&u_pais=" + URLEncoder.encode(u_pais, "UTF-8") +
                            "&u_celular=" + URLEncoder.encode(u_celular, "UTF-8") +
                            "&u_sexo=" + URLEncoder.encode(finalU_sexo, "UTF-8") +
                            "&u_perfil=" + URLEncoder.encode(u_perfil, "UTF-8");

                    URL url=new URL(miurl);
                    Log.e("registro_conexion",miurl);
                    conexion = (HttpURLConnection) url.openConnection();
                    Log.e("conexion: ", conexion+"");
                    if (conexion.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                        String linea = reader.readLine();
                        if (!linea.equals("OK\\n")) {
                            Log.e("registro_error_servicio","Error en servicio Web nueva");
                        }
                        else
                        {
                            Log.e("registro_exito","No hay error");
                            //Toast.makeText(this, "Inserción exitosa", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    } else {
                        Log.e("mierror", conexion.getResponseMessage());
                    }
                    Log.e("responsecode", conexion.getResponseCode()+"");
                    Log.e("urlconecction",HttpURLConnection.HTTP_OK+"");

                } catch (Exception e) { Log.e("registro_error", e.getMessage()+"Fallo al final del catch.", e);
                } finally { if (conexion!=null) conexion.disconnect();
                }

            }
        }).start();

        Log.d("items", "nombre: " + u_nombre + " . usuario: " + u_usuario + " . password: " + u_password
                + " . nacimiento: " + u_nacimiento+" . pais: "+u_pais+" . celular: "+u_celular+" . sexo: "+u_sexo+" . perfil: "+u_perfil);
    }
}