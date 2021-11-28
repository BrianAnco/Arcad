package com.example.navigationtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ParseAdapterCategoria extends RecyclerView.Adapter<ParseAdapterCategoria.ViewHolder> {

    private ArrayList<ParseItemCategoria> parseItemsCategoria;
    private Context context;

    public ParseAdapterCategoria(ArrayList<ParseItemCategoria> parseItemsCategoria, Context context) {
        this.parseItemsCategoria = parseItemsCategoria;
        this.context = context;
    }

    @NonNull
    @Override
    public ParseAdapterCategoria.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parse_item_categoria, parent, false);
        return new ParseAdapterCategoria.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParseAdapterCategoria.ViewHolder holder, int position) {
        ParseItemCategoria parseItemCategoria = parseItemsCategoria.get(position);
        holder.categoria.setText(parseItemCategoria.getCategoria());
        if(parseItemCategoria.getCategoria().equals("Todo")){
            holder.icono.setImageResource(R.drawable.ctodo);
        }
        if(holder.categoria.getText().equals("Acción")){
            holder.icono.setImageResource(R.drawable.caccion);
        }
        if(parseItemCategoria.getCategoria().equals("Aventura")){
            holder.icono.setImageResource(R.drawable.caventura);
        }
        if(parseItemCategoria.getCategoria().equals("FPS")){
            holder.icono.setImageResource(R.drawable.cfps);
        }
        if(parseItemCategoria.getCategoria().equals("Gestion")){
            holder.icono.setImageResource(R.drawable.cgestion);
        }
        if(parseItemCategoria.getCategoria().equals("MMORPG")){
            holder.icono.setImageResource(R.drawable.cmmorpg);
        }
        if(parseItemCategoria.getCategoria().equals("Carreras")){
            holder.icono.setImageResource(R.drawable.ccarreras);
        }
        if(parseItemCategoria.getCategoria().equals("Rol")){
            holder.icono.setImageResource(R.drawable.crol);
        }
        if(parseItemCategoria.getCategoria().equals("Simulacion")){
            holder.icono.setImageResource(R.drawable.csimulacion);
        }
        if(parseItemCategoria.getCategoria().equals("Sport")){
            holder.icono.setImageResource(R.drawable.csport);
        }
        if(parseItemCategoria.getCategoria().equals("Estrategia")){
            holder.icono.setImageResource(R.drawable.cestrategia);
        }
        if(parseItemCategoria.getCategoria().equals("Tarjetas Prepago")){
            holder.icono.setImageResource(R.drawable.ctarjetas);
        }




    }

    @Override
    public int getItemCount() {
        return parseItemsCategoria.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView categoria;
        ImageView icono;

        public ViewHolder(@NonNull View view) {
            super(view);
            categoria = view.findViewById(R.id.txtCategoria);
            icono = view.findViewById(R.id.icono);
        }
    }
}