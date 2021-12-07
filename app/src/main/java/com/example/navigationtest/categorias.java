package com.example.navigationtest;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
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
 * Use the {@link categorias#newInstance} factory method to
 * create an instance of this fragment.
 */
public class categorias extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView  r1, r2, r3, r4, r5;
    private ParseAdapter adapter;

    public Vector<String> valor;
    private ArrayList<ParseItem> parseItems = new ArrayList<>();
    private ArrayList<ParseItem> parseItems2 = new ArrayList<>();
    private ArrayList<ParseItem> parseItems3 = new ArrayList<>();
    private ArrayList<ParseItem> parseItems4 = new ArrayList<>();
    private ArrayList<ParseItem> parseItems5 = new ArrayList<>();
    private String res;


    public categorias() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment categorias.
     */
    // TODO: Rename and change types and number of parameters
    public static categorias newInstance(String param1, String param2) {
        categorias fragment = new categorias();
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
        return inflater.inflate(R.layout.fragment_categorias, container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Intent intent = getActivity().getIntent();
        String usuario_sesion = intent.getStringExtra("usuario_sesion");
        Log.e("usuario_favoritos:", usuario_sesion);

        DividerItemDecoration divider1 = new DividerItemDecoration(requireContext(),LinearLayoutManager.VERTICAL);

        ListaFavoritos(usuario_sesion);
        r1 = getView().findViewById(R.id.recycler1);
        r1.setHasFixedSize(true);
        r1.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ParseAdapter(parseItems, getContext());
        r1.setAdapter(adapter);
        r1.addItemDecoration(divider1);

        ListaFavoritos2(usuario_sesion);
        r2 = getView().findViewById(R.id.recycler2);
        r2.setHasFixedSize(true);
        r2.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ParseAdapter(parseItems2, getContext());
        r2.setAdapter(adapter);
        r2.addItemDecoration(divider1);

        ListaFavoritos3(usuario_sesion);
        r3 = getView().findViewById(R.id.recycler3);
        r3.setHasFixedSize(true);
        r3.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ParseAdapter(parseItems3, getContext());
        r3.setAdapter(adapter);
        r3.addItemDecoration(divider1);

        ListaFavoritos4(usuario_sesion);
        r4 = getView().findViewById(R.id.recycler4);
        r4.setHasFixedSize(true);
        r4.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ParseAdapter(parseItems4, getContext());
        r4.setAdapter(adapter);
        r4.addItemDecoration(divider1);

        ListaFavoritos5(usuario_sesion);
        r5 = getView().findViewById(R.id.recycler5);
        r5.setHasFixedSize(true);
        r5.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ParseAdapter(parseItems5, getContext());
        r5.setAdapter(adapter);
        r5.addItemDecoration(divider1);

    }

    public Vector<String> ListaFavoritos(String usuario) {
        List<JuegoRecomendado> favoritos;
        favoritos= leerJSon(conseguirstring(usuario));
        valor=new Vector<>();
        Vector<String> salida = new Vector<>();
        for (JuegoRecomendado favorito : favoritos) {
            salida.add(favorito.getJ_nombre()+ " " + favorito.getJ_enlace()+ " " +favorito.getJ_lanzamiento()+ " "+
                    favorito.getJ_genero()+ " " + favorito.getJ_urlimagen()+ favorito.getJ_id());

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
            HttpGet request = new HttpGet("http://192.168.1.46/Arcad_Recomendacion1.php?u_usuario="+usuario);
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

    private List<JuegoRecomendado> leerJSon(String string) {
        List<JuegoRecomendado> JuegoRecomendado = new ArrayList<>();
        try {
            JSONArray json_array = new JSONArray(string);
            for (int i = 0; i < json_array.length(); i++) {
                JSONObject objeto = json_array.getJSONObject(i);
                JuegoRecomendado.add(new JuegoRecomendado(objeto.getString("j_nombre"), objeto.getString("j_enlace"),
                        objeto.getString("j_lanzamiento"), objeto.getString("j_genero"), objeto.getString("j_precio"), objeto.getString("j_urlimagen"), objeto.getInt("j_id")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return JuegoRecomendado;
    }

    //=======================================================================================

    public Vector<String> ListaFavoritos2(String usuario) {
        List<JuegoRecomendado> favoritos;
        favoritos= leerJSon2(conseguirstring2(usuario));
        valor=new Vector<>();
        Vector<String> salida = new Vector<>();
        for (JuegoRecomendado favorito : favoritos) {
            salida.add(favorito.getJ_nombre()+ " " + favorito.getJ_enlace()+ " " +favorito.getJ_lanzamiento()+ " "+
                    favorito.getJ_genero()+ " " + favorito.getJ_urlimagen()+ favorito.getJ_id());

            parseItems2.add(new ParseItem(("background-image: url("+favorito.getJ_urlimagen()+")"),favorito.getJ_nombre(),favorito.getJ_lanzamiento()+" - "+favorito.getJ_genero(),
                    favorito.getJ_precio(),favorito.getJ_enlace()));

        }
        Log.i("mierror",salida.toString());
        return salida;
    }

    public String conseguirstring2(String usuario) {
        try
        {
            HttpClient httpclient = new DefaultHttpClient();
            httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
//la ruta del json
            HttpGet request = new HttpGet("http://192.168.1.46/Arcad_Recomendacion2.php?u_usuario="+usuario);
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

    private List<JuegoRecomendado> leerJSon2(String string) {
        List<JuegoRecomendado> JuegoRecomendado = new ArrayList<>();
        try {
            JSONArray json_array = new JSONArray(string);
            for (int i = 0; i < json_array.length(); i++) {
                JSONObject objeto = json_array.getJSONObject(i);
                JuegoRecomendado.add(new JuegoRecomendado(objeto.getString("j_nombre"), objeto.getString("j_enlace"),
                        objeto.getString("j_lanzamiento"), objeto.getString("j_genero"), objeto.getString("j_precio"), objeto.getString("j_urlimagen"), objeto.getInt("j_id")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return JuegoRecomendado;
    }

    //=======================================================================================

    public Vector<String> ListaFavoritos3(String usuario) {
        List<JuegoRecomendado> favoritos;
        favoritos= leerJSon3(conseguirstring3(usuario));
        valor=new Vector<>();
        Vector<String> salida = new Vector<>();
        for (JuegoRecomendado favorito : favoritos) {
            salida.add(favorito.getJ_nombre()+ " " + favorito.getJ_enlace()+ " " +favorito.getJ_lanzamiento()+ " "+
                    favorito.getJ_genero()+ " " + favorito.getJ_urlimagen()+ favorito.getJ_id());

            parseItems3.add(new ParseItem(("background-image: url("+favorito.getJ_urlimagen()+")"),favorito.getJ_nombre(),favorito.getJ_lanzamiento()+" - "+favorito.getJ_genero(),
                    favorito.getJ_precio(),favorito.getJ_enlace()));

        }
        Log.i("mierror",salida.toString());
        return salida;
    }

    public String conseguirstring3(String usuario) {
        try
        {
            HttpClient httpclient = new DefaultHttpClient();
            httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
//la ruta del json
            HttpGet request = new HttpGet("http://192.168.1.46/Arcad_Recomendacion3.php?u_usuario="+usuario);
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

    private List<JuegoRecomendado> leerJSon3(String string) {
        List<JuegoRecomendado> JuegoRecomendado = new ArrayList<>();
        try {
            JSONArray json_array = new JSONArray(string);
            for (int i = 0; i < json_array.length(); i++) {
                JSONObject objeto = json_array.getJSONObject(i);
                JuegoRecomendado.add(new JuegoRecomendado(objeto.getString("j_nombre"), objeto.getString("j_enlace"),
                        objeto.getString("j_lanzamiento"), objeto.getString("j_genero"), objeto.getString("j_precio"), objeto.getString("j_urlimagen"), objeto.getInt("j_id")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return JuegoRecomendado;
    }

    //=======================================================================================

    public Vector<String> ListaFavoritos4(String usuario) {
        List<JuegoRecomendado> favoritos;
        favoritos= leerJSon4(conseguirstring4(usuario));
        valor=new Vector<>();
        Vector<String> salida = new Vector<>();
        for (JuegoRecomendado favorito : favoritos) {
            salida.add(favorito.getJ_nombre()+ " " + favorito.getJ_enlace()+ " " +favorito.getJ_lanzamiento()+ " "+
                    favorito.getJ_genero()+ " " + favorito.getJ_urlimagen()+ favorito.getJ_id());

            parseItems4.add(new ParseItem(("background-image: url("+favorito.getJ_urlimagen()+")"),favorito.getJ_nombre(),favorito.getJ_lanzamiento()+" - "+favorito.getJ_genero(),
                    favorito.getJ_precio(),favorito.getJ_enlace()));

        }
        Log.i("mierror",salida.toString());
        return salida;
    }

    public String conseguirstring4(String usuario) {
        try
        {
            HttpClient httpclient = new DefaultHttpClient();
            httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
//la ruta del json
            HttpGet request = new HttpGet("http://192.168.1.46/Arcad_Recomendacion4.php?u_usuario="+usuario);
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

    private List<JuegoRecomendado> leerJSon4(String string) {
        List<JuegoRecomendado> JuegoRecomendado = new ArrayList<>();
        try {
            JSONArray json_array = new JSONArray(string);
            for (int i = 0; i < json_array.length(); i++) {
                JSONObject objeto = json_array.getJSONObject(i);
                JuegoRecomendado.add(new JuegoRecomendado(objeto.getString("j_nombre"), objeto.getString("j_enlace"),
                        objeto.getString("j_lanzamiento"), objeto.getString("j_genero"), objeto.getString("j_precio"), objeto.getString("j_urlimagen"), objeto.getInt("j_id")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return JuegoRecomendado;
    }

    //=======================================================================================

    public Vector<String> ListaFavoritos5(String usuario) {
        List<JuegoRecomendado> favoritos;
        favoritos= leerJSon5(conseguirstring5(usuario));
        valor=new Vector<>();
        Vector<String> salida = new Vector<>();
        for (JuegoRecomendado favorito : favoritos) {
            salida.add(favorito.getJ_nombre()+ " " + favorito.getJ_enlace()+ " " +favorito.getJ_lanzamiento()+ " "+
                    favorito.getJ_genero()+ " " + favorito.getJ_urlimagen()+ favorito.getJ_id());

            parseItems5.add(new ParseItem(("background-image: url("+favorito.getJ_urlimagen()+")"),favorito.getJ_nombre(),favorito.getJ_lanzamiento()+" - "+favorito.getJ_genero(),
                    favorito.getJ_precio(),favorito.getJ_enlace()));

        }
        Log.i("mierror",salida.toString());
        return salida;
    }

    public String conseguirstring5(String usuario) {
        try
        {
            HttpClient httpclient = new DefaultHttpClient();
            httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
//la ruta del json
            HttpGet request = new HttpGet("http://192.168.1.46/Arcad_Recomendacion5.php?u_usuario="+usuario);
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

    private List<JuegoRecomendado> leerJSon5(String string) {
        List<JuegoRecomendado> JuegoRecomendado = new ArrayList<>();
        try {
            JSONArray json_array = new JSONArray(string);
            for (int i = 0; i < json_array.length(); i++) {
                JSONObject objeto = json_array.getJSONObject(i);
                JuegoRecomendado.add(new JuegoRecomendado(objeto.getString("j_nombre"), objeto.getString("j_enlace"),
                        objeto.getString("j_lanzamiento"), objeto.getString("j_genero"), objeto.getString("j_precio"), objeto.getString("j_urlimagen"), objeto.getInt("j_id")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return JuegoRecomendado;
    }

}