package com.example.navigationtest;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link inicio#newInstance} factory method to
 * create an instance of this fragment.
 */
public class inicio extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView recyclerView;
    private RecyclerView recyclerViewCategoria;
    private ParseAdapter adapter;
    private TextView busqueda;
    private ImageButton btnBuscar;
    private TextView palabra_clave;
    private ParseAdapterCategoria adapterCategoria;
    private ArrayList<ParseItem> parseItems = new ArrayList<>();
    private ArrayList<ParseItemCategoria> parseItemsCategoria = new ArrayList<>();
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public inicio() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment inicio.
     */
    // TODO: Rename and change types and number of parameters
    public static inicio newInstance(String param1, String param2) {
        inicio fragment = new inicio();
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
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView = getView().findViewById(R.id.recyclerView);


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



        adapter = new ParseAdapter(parseItems, getContext());
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);


        recyclerViewCategoria = getView().findViewById(R.id.recyclerViewCategorias);
        recyclerViewCategoria.setHasFixedSize(true);
        recyclerViewCategoria.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewCategoria.setLayoutManager(layoutManager);
        adapterCategoria = new ParseAdapterCategoria(parseItemsCategoria, getContext());
        recyclerViewCategoria.setAdapter(adapterCategoria);
        ListarTodasCategorias();
        ListarPopulares();


        Content content = new Content();
        content.execute();
        palabra_clave = getView().findViewById(R.id.lblPopulares2);
        btnBuscar = getView().findViewById(R.id.imageButton);
        busqueda = getView().findViewById(R.id.txtBuscar);



        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.removeAllViewsInLayout();
                parseItems.clear();

                if(busqueda.getText().toString().equals("")){
                    String pop = "Populares";
                    palabra_clave.setText(pop.toUpperCase());
                    ListarPopulares();
                    Content content2= new Content();
                    content2.execute();
                }
                else{
                    ListarBusqueda(busqueda.getText().toString());
                    palabra_clave.setText(busqueda.getText().toString().toUpperCase());
                    Content content2= new Content();
                    content2.execute();
                }

            }
        });



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        Intent intent = getActivity().getIntent();
        String usuario_sesion = intent.getStringExtra("usuario_sesion");
        Log.e("usuario_sesion_inicio:",usuario_sesion);


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inicio, container, false);
    }




    private class Content extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            adapter.notifyDataSetChanged();
            adapterCategoria.notifyDataSetChanged();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            ListarTodasCategorias();



            return null;

        }
    }

    private void ListarPopulares(){
        try {



            String url = "https://cheapdigitaldownload.com/catalog/category-pc-games-all/page-1/";

            Document doc = Jsoup.connect(url).get();

            Elements data = doc.select("li.search-results-row");

            int size = data.size();
            Log.d("doc", "doc: "+doc);
            Log.d("data", "data: "+data);
            Log.d("size", ""+size);
            for (int i = 0; i < size; i++) {
                String imgUrl = data.select("li.search-results-row")
                        .select("div.search-results-row-image-ratio")
                        .eq(i)
                        .attr("style");

                String title = data.select("li.search-results-row")
                        .select("h2")
                        .eq(i)
                        .text();

                String description = data.select("li.search-results-row")
                        .select("div.search-results-row-game-infos")
                        .eq(i)
                        .text();

                String precio = data.select("li.search-results-row")
                        .select("div.search-results-row-price")
                        .eq(i)
                        .text();

                String durl = data.select("li.search-results-row")
                        .select("a")
                        .eq(i)
                        .attr("href");

                parseItems.add(new ParseItem(imgUrl, title, description, precio, durl));
                Log.d("items", "img: " + imgUrl + " . title: " + title + " . description: " + description + " . precio: " + precio+" . durl: "+durl);
            }



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ListarTodasCategorias(){
        try {

            String url = "https://www.clavecd.es/catalog/category-pc-games-all/";

            Document doc = Jsoup.connect(url).get();

            Elements data = doc.select("li.search-filters-fields-row");
            int size = data.size();
            Log.d("doc", "doc: "+doc);
            Log.d("data", "data: "+data);
            Log.d("size", ""+size);
            for (int i = 0; i < 11; i++) {

                String categoria = data.select("li.search-filters-fields-row")
                        .select("a")
                        .eq(i)
                        .text();

                parseItemsCategoria.add(new ParseItemCategoria(categoria));
                Log.d("items", "categoria: " + categoria);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ListarBusqueda(String palabra){
        try {

            palabra=palabra.toLowerCase();
            palabra=palabra.replace(" ","+");
            String url = "https://cheapdigitaldownload.com/catalog/search-"+palabra+"/";

            Document doc = Jsoup.connect(url).get();

            Elements data = doc.select("li.search-results-row");

            int size = data.size();
            Log.d("doc", "doc: "+doc);
            Log.d("data", "data: "+data);
            Log.d("size", ""+size);
            for (int i = 0; i < size; i++) {
                String imgUrl = data.select("li.search-results-row")
                        .select("div.search-results-row-image-ratio")
                        .eq(i)
                        .attr("style");

                String title = data.select("li.search-results-row")
                        .select("h2")
                        .eq(i)
                        .text();

                String description = data.select("li.search-results-row")
                        .select("div.search-results-row-game-infos")
                        .eq(i)
                        .text();

                String precio = data.select("li.search-results-row")
                        .select("div.search-results-row-price")
                        .eq(i)
                        .text();

                String durl = data.select("li.search-results-row")
                        .select("a")
                        .eq(i)
                        .attr("href");

                parseItems.add(new ParseItem(imgUrl, title, description, precio, durl));
                Log.d("items", "img: " + imgUrl + " . title: " + title + " . description: " + description + " . precio: " + precio+" . durl: "+durl);
            }



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}