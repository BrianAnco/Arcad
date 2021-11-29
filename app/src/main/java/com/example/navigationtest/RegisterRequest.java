package com.example.navigationtest;


import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest{
    private static final String REGISTER_REQUEST_URL="http://localhost/Arcard_Registro.php";
    private final Map<String,String> params;

    public RegisterRequest(String u_nombre, String u_usuario, String u_password, String u_nacimiento, String u_pais, String u_celular, String u_sexo, String u_perfil
            ,Response.Listener<String> listener){
        super(Method.POST, REGISTER_REQUEST_URL, listener,  null);
        params=new HashMap<>();
        params.put("u_nombre", u_nombre);
        params.put("u_usuario", u_usuario);
        params.put("u_password", u_password);
        params.put("u_nacimiento", u_nacimiento);
        params.put("u_pais", u_pais);
        params.put("u_celular", u_celular);
        params.put("u_sexo", u_sexo);
        params.put("u_perfil", u_perfil);

    }

    @Override
    public Map<String, String> getParams(){
        return params;
    }
}
