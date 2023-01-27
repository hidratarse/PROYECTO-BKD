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
        private final ImageView imagen_perfil;
        private final TextView nombre;
        private final ImageButton imagen_madera;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imagen_perfil = itemView.findViewById(R.id.id_foto_perfil);
            nombre = itemView.findViewById(R.id.id_perfiles_nombre);
            imagen_madera = itemView.findViewById(R.id.id_imagen_madera);

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
