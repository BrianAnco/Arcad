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

public class ParseAdapter extends RecyclerView.Adapter<ParseAdapter.ViewHolder> {

    private ArrayList<ParseItem> parseItems;
    private Context context;

    public ParseAdapter(ArrayList<ParseItem> parseItems, Context context) {
        this.parseItems = parseItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ParseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parse_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParseAdapter.ViewHolder holder, int position) {
        ParseItem parseItem = parseItems.get(position);
        holder.textView.setText(parseItem.getTitle());
        holder.descripcion.setText(parseItem.getDescription());
        holder.precio.setText(parseItem.getPrecio());
        if(parseItem.getTitle().length()>=16){
            holder.textView.setPadding(0,0,0,0);

        }
        /*if(parseItem.getTitle().length()>=30){
            holder.textView.setPadding(0,-15,0,0);
        }*/
        if(parseItem.getTitle().length()<16){
            holder.textView.setPadding(0,25,0,0);
        }
        //Toast.makeText(context.getApplicationContext(), parseItem.getDurl(), Toast.LENGTH_LONG).show();
        Picasso.get().load(parseItem.getImgUrl()).into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return parseItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imageView;
        TextView textView;
        TextView descripcion;
        TextView precio;

        public ViewHolder(@NonNull View view) {
            super(view);
            imageView = view.findViewById(R.id.imageView);
            textView = view.findViewById(R.id.textView);
            descripcion = view.findViewById(R.id.textViewDesc);
            precio = view.findViewById(R.id.textViewPrecio);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            ParseItem parseItem = parseItems.get(position);
        }
    }
}