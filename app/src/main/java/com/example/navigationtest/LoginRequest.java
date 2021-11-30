package com.example.navigationtest;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {

    private static final String LOGIN_REQUEST_URL="http://localhost/Arcad_Login.php";
    private final Map<String,String> params;

    public LoginRequest(String u_usuario, String u_password, Response.Listener<String> listener){
        super(Request.Method.POST, LOGIN_REQUEST_URL, listener,  null);
        params=new HashMap<>();
        params.put("u_usuario", u_usuario);
        params.put("u_password", u_password);

    }

    @Override
    public Map<String, String> getParams(){
        return params;
    }
}