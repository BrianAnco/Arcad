package com.example.navigationtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class DetalleJuego extends AppCompatActivity {

    private ImageView portada_juego;
    private RecyclerView recyclerView;
    private ParseAdapterTiendas adapter;
    private ArrayList<ParseItemTiendas> parseItemsTiendas = new ArrayList<>();
    private TextView titulo_juego, anio_genero_juego, descripcion_juego, seguir_juego;
    private RecyclerView tiendas;
    String imagen_url="hola";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_juego);

        /*recyclerView = findViewById(R.id.recyclerTiendas);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ParseAdapterTiendas(parseItemsTiendas, this);
        recyclerView.setAdapter(adapter);*/


        /*
        WebView view = (WebView) findViewById(R.id.webV);
        view.getSettings().setJavaScriptEnabled(true);
        view.loadUrl(getIntent().getStringExtra("detalle_url"));*/

        String urlnuevo = getIntent().getStringExtra("detalle_url");
        final WebView webview = (WebView)findViewById(R.id.webV);

        webview.getSettings().setJavaScriptEnabled(true);



        webview.setWebViewClient(new WebViewClient() {


            public void onPageStarted(WebView view, String url, Bitmap favicon){
                webview.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url)
            {
                // hide element by class name
                webview.loadUrl("javascript:(function() { " +
                        "document.getElementsByClassName('banner-search-form-input')[0].style.display='none';" +
                        "document.getElementsByClassName('col-sm-9 game__details-wrapper')[0].style.display='none';" +
                        "document.getElementsByClassName('col-md-3 game__sidebar-wrapper')[0].style.display='none';" +
                        "document.getElementsByClassName('container banner-top-row')[0].style.display='none';" +
                        "document.getElementsByClassName('nav nav-tabs tabs')[0].style.display='none';" +
                        "document.getElementsByClassName('nav nav-tabs tabs')[1].style.display='none';" +
                        "document.getElementsByClassName('nav nav-tabs tabs')[2].style.display='none';" +
                        "document.getElementsByClassName('nav nav-tabs tabs')[3].style.display='none';" +
                        "document.getElementsByClassName('banner-side')[0].style.display='none';" +
                        "document.getElementsByClassName('tab-content topclick-list')[0].style.display='none';" +
                        "document.getElementsByClassName('nav nav-tabs content-box-title tabs')[0].style.display='none';" +
                        "document.getElementsByClassName('nav nav-tabs content-box-title tabs')[1].style.display='none';" +
                        "document.getElementsByClassName('tab-content topclick-list')[1].style.display='none';" +
                        "document.getElementsByClassName('row d-flex justify-content-end footer-social')[0].style.display='none';" +
                        "document.getElementsByClassName('tab-content topclick-list')[1].style.display='none';" +
                        "document.getElementsByClassName('footer')[0].style.display='none';" +
                        "document.getElementsByClassName('report-price-error')[0].style.display='none';" +
                        "document.getElementsByClassName('report-price-error price-summary')[0].style.display='none';" +

                        "window.scrollBy(0,80);" +

                        "document.getElementsByClassName('tab-content filters-section')[0].style.display='none';" +
                        "document.getElementsByClassName('zendesk__container')[0].style.display='none';" +


                        "document.getElementsByClassName('page-template')[0].style.backgroundSize='10%';" +
                        "document.getElementsByClassName('page-template')[0].style.backgroundPosition='0 0';" +
                        "document.getElementsByClassName('container content')[0].style.height ='0px';" +
                        "})()");

                webview.loadUrl("javascript:(function() { " +
                        "document.getElementById('comments').style.display='none';})()");
                webview.loadUrl("javascript:(function() { " +
                        "document.getElementById('respond').style.display='none';})()");
                // hide element by id
                /*webview.loadUrl("javascript:(function() { " +
                        "document.getElementById('your_id').style.display='none';})()");*/

                webview.setVisibility(View.INVISIBLE);
                webview.setVisibility(View.VISIBLE);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView webview, WebResourceRequest request) {
                //Abre url en browser.
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(request.getUrl().toString())));
                return true;
            }
        });

        webview.loadUrl(urlnuevo);
        webview.setVisibility(View.VISIBLE);


        portada_juego = findViewById(R.id.juego_imagen);
        titulo_juego = findViewById(R.id.juego_titulo);
        anio_genero_juego = findViewById(R.id.juego_genero);
        titulo_juego.setText(getIntent().getStringExtra("titulo"));
        anio_genero_juego.setText(getIntent().getStringExtra("anio_genero"));
        Picasso.get().load(getIntent().getStringExtra("img_url")).into(portada_juego);

        Content content = new Content();
        content.execute();
    }

    private class Content extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected Void doInBackground(Void... voids) {


            String NombreJuego = getIntent().getStringExtra("titulo");
            NombreJuego=NombreJuego.toLowerCase();
            NombreJuego=NombreJuego.replace(' ','-');
            String url = "https://gocdkeys.com/es/comprar-"+NombreJuego+"-pc-cd-key";
            Log.e("url 1:", url);

            try {


                Document doc = Jsoup.connect(url).get();

                Elements data = doc.select("div.stores-list");

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



                    /*parseItemsTiendas.add(new ParseItem(imgUrl, title, description, precio, durl));
                    Log.d("items", "img: " + imgUrl + " . title: " + title + " . description: " + description + " . precio: " + precio+" . durl: "+durl);*/
                }



            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;

        }
    }



}