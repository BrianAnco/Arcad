package com.example.navigationtest;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ParseAdapterTiendas extends RecyclerView.Adapter<ParseAdapterTiendas.ViewHolder> {

    private ArrayList<ParseItemTiendas> parseItemsTiendas;
    private Context context;

    public ParseAdapterTiendas(ArrayList<ParseItemTiendas> parseItemsTiendas, Context context) {
        this.parseItemsTiendas = parseItemsTiendas;
        this.context = context;
    }

    @NonNull
    @Override
    public ParseAdapterTiendas.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parse_item_tiendas, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ParseItemTiendas parseItemTiendas = parseItemsTiendas.get(position);
        holder.opinion.setText(parseItemTiendas.getOpinion());
        holder.tienda.setText(parseItemTiendas.getTienda());
        holder.precio.setText(parseItemTiendas.getPrecio());
    }



    @Override
    public int getItemCount() {
        return parseItemsTiendas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView opinion;
        TextView tienda;
        TextView precio;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            opinion = itemView.findViewById(R.id.lblOpinion);
            tienda = itemView.findViewById(R.id.lblTienda);
            precio = itemView.findViewById(R.id.lblPrecio);

        }

        @Override
        public void onClick(View view) {

        }
    }
}
