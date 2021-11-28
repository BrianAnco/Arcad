package com.example.navigationtest;


import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest{
    private static final String REGISTER_REQUEST_URL="http://192.168.1.7/Register.php";
    private final Map<String,String> params;

    public RegisterRequest(String name, String username, String password, int age, Response.Listener<String> listener){
        super(Method.POST, REGISTER_REQUEST_URL, listener,  null);
        params=new HashMap<>();
        params.put("name", name);
        params.put("username", username);
        params.put("password", password);
        params.put("age", age+"");

    }

    @Override
    public Map<String, String> getParams(){
        return params;
    }
}
