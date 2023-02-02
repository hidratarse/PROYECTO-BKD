package com.example.proyecto_bkd.ranking;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto_bkd.R;
import com.example.proyecto_bkd.perfiles.Perfil;

import java.util.ArrayList;

public class Ranking2_adapter extends RecyclerView.Adapter<Ranking2_adapter.ViewHolder> {

    private ArrayList<Perfil> datos;

    public Ranking2_adapter(ArrayList<Perfil> dataSet){
        datos = new ArrayList<>();
        add(dataSet);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView nombre;
        private final TextView puntuacion;
        private final TextView posicion;
        private final ImageView primero;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.id_jugador_ranking2);
            puntuacion = itemView.findViewById(R.id.id_puntuacion_ranking2);
            posicion = itemView.findViewById(R.id.id_posicion_ranking2);
            primero = itemView.findViewById(R.id.coronaPrimero);

        }
        public TextView getNombre(){return nombre;}

        public TextView getPuntuacion(){return puntuacion;}

        public TextView getPosicion() {return posicion;}

        public ImageView getPrimero(){return primero;}
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ranking_jugador_view,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position == 0 | position == 1 | position == 2) {
            holder.getPrimero().setVisibility(View.VISIBLE);
            holder.getPosicion().setVisibility(View.GONE);
        }
        Perfil p =datos.get(position);
        String nombre = p.getNombre();
        int puntuacion = p.getPuntuacionGeneral();
        holder.getNombre().setText(nombre);
        holder.getPuntuacion().setText(String.valueOf(puntuacion));
        holder.getPosicion().setText(String.valueOf(position+1));
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
