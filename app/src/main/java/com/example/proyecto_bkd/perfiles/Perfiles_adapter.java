package com.example.proyecto_bkd.perfiles;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto_bkd.R;

import java.util.ArrayList;

public class Perfiles_adapter extends RecyclerView.Adapter<Perfiles_adapter.ViewHolder> {

    private ArrayList<Perfil> datos;

    public Perfiles_adapter(ArrayList<Perfil> dataSet){
        datos = new ArrayList<>();
        add(dataSet);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imagen_marco,imagen_perfil;
        private final TextView nombre;
        private final ImageButton imagen_madera;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imagen_marco = itemView.findViewById(R.id.id_marco);
            nombre = itemView.findViewById(R.id.id_perfiles_nombre);
            imagen_madera = itemView.findViewById(R.id.id_imagen_madera);
            imagen_perfil=itemView.findViewById(R.id.imgProfile);
        }

        public TextView getNombre() {
            return nombre;
        }
    }

    @NonNull
    @Override
    public Perfiles_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.perfiles_view,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Perfil p = datos.get(position);
        String nombre = p.getNombre();
        if(position==0) {
            holder.imagen_perfil.setImageResource(R.drawable.conejo1);
        }
        if(position==1) {
            holder.imagen_perfil.setImageResource(R.drawable.conejo2);
        }
        if(position==2) {
            holder.imagen_perfil.setImageResource(R.drawable.conejo3);
        }
        if(position==3) {
            holder.imagen_perfil.setImageResource(R.drawable.conejo4);
        }
        if(position==4) {
            holder.imagen_perfil.setImageResource(R.drawable.conejo5);
        }
        if(position==5) {
            holder.imagen_perfil.setImageResource(R.drawable.conejo6);
        }
        if(position==6) {
            holder.imagen_perfil.setImageResource(R.drawable.conejo7);
        }
        if(position==7) {
            holder.imagen_perfil.setImageResource(R.drawable.conejo8);
        }
        if(position==8) {
            holder.imagen_perfil.setImageResource(R.drawable.conejo10);
        }
        if(position==9) {
            holder.imagen_perfil.setImageResource(R.drawable.conejo10);
        }
        Log.d(TAG,nombre);
        holder.getNombre().setText(p.getNombre());
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    private void add(ArrayList<Perfil> dataSet) {
        datos.addAll(dataSet);
        notifyDataSetChanged();
    }
}
