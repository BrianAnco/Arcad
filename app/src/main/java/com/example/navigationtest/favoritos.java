package com.example.navigationtest;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntity;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpVersion;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpGet;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.DefaultHttpClient;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.params.CoreProtocolPNames;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.EntityUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link favoritos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class favoritos extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private RecyclerView recyclerView;
    private ParseAdapter adapter;
    private Vector<String> misdatos;
    public Vector<String> valor;
    private ArrayList<ParseItem> parseItems = new ArrayList<>();
    private String res;

    public favoritos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment favoritos.
     */
    // TODO: Rename and change types and number of parameters
    public static favoritos newInstance(String param1, String param2) {
        favoritos fragment = new favoritos();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {





        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favoritos, container, false);


    }


    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Intent intent = getActivity().getIntent();
        String usuario_sesion = intent.getStringExtra("usuario_sesion");
        Log.e("usuario_favoritos:", usuario_sesion);

        ListaFavoritos(usuario_sesion);
        recyclerView = getView().findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ParseAdapter(parseItems, getContext());
        recyclerView.setAdapter(adapter);

    }


    public Vector<String> ListaFavoritos(String usuario) {
        List<JuegoFavorito> favoritos;
        favoritos= leerJSon(conseguirstring(usuario));
        valor=new Vector<>();
        Vector<String> salida = new Vector<>();
        for (JuegoFavorito favorito : favoritos) {
            salida.add(favorito.getJ_nombre()+ " " + favorito.getJ_enlace()+ " " +favorito.getJ_lanzamiento()+ " "+
                    favorito.getJ_genero()+ " " + favorito.getJ_urlimagen()+ " " +favorito.getU_id()+ " "+ favorito.getJ_id());

            parseItems.add(new ParseItem(("background-image: url("+favorito.getJ_urlimagen()+")"),favorito.getJ_nombre(),favorito.getJ_lanzamiento()+" - "+favorito.getJ_genero(),
                    favorito.getJ_precio(),favorito.getJ_enlace()));

        }
        Log.i("mierror",salida.toString());
        return salida;
    }
    public String conseguirstring(String usuario) {
        try
        {
            HttpClient httpclient = new DefaultHttpClient();
            httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
//la ruta del json
            HttpGet request = new HttpGet("http://192.168.1.46/Arcad_Listar_Favoritos.php?u_usuario="+usuario);
            HttpResponse response = httpclient.execute(request);
            HttpEntity resEntity = response.getEntity();
            String _response= EntityUtils.toString(resEntity);
            res=_response;
        }catch(Exception e)
        {
            return res="";
        }
        return res;
    }

    private List<JuegoFavorito> leerJSon(String string) {
        List<JuegoFavorito> JuegoFavorito = new ArrayList<>();
        try {
            JSONArray json_array = new JSONArray(string);
            for (int i = 0; i < json_array.length(); i++) {
                JSONObject objeto = json_array.getJSONObject(i);
                JuegoFavorito.add(new JuegoFavorito(objeto.getString("j_nombre"), objeto.getString("j_enlace"),
                        objeto.getString("j_lanzamiento"), objeto.getString("j_genero"), objeto.getString("j_precio"), objeto.getString("j_urlimagen"),
                        objeto.getInt("u_id"), objeto.getInt("j_id")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return JuegoFavorito;
    }


}