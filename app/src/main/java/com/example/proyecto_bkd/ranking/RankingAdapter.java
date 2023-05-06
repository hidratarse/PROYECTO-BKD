package com.example.proyecto_bkd.ranking;

import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto_bkd.R;
import com.example.proyecto_bkd.partida.resumenturno.actividades.ResumenTurno;
import com.example.proyecto_bkd.perfiles.data.Perfil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RankingAdapter extends RecyclerView.Adapter<RankingAdapter.ViewHolder> {
    private List<Perfil> datos = new ArrayList<>();

    public void setResults(List<Perfil> datos){
        this.datos = datos;
        notifyDataSetChanged();
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
        if (position < 3) {
            holder.getPrimero().setVisibility(View.VISIBLE);
            holder.getPosicion().setVisibility(View.GONE);
        }
        switch (position) {
            case 0:
                holder.getPrimero().setImageResource(R.drawable.crownoro);
                break;
            case 1:
                holder.getPrimero().setImageResource(R.drawable.crownplata2);
                break;
            case 2 :
                holder.getPrimero().setImageResource(R.drawable.crownbronce2);
                break;

        }
        Perfil p =datos.get(position);
        String nombre = p.getNombre();
        int puntuacion = Integer.parseInt(p.getMaxPuntuacion());
        String victorias = p.getPartidasGanadas();
        holder.getNombre().setText(nombre);
        if(Ranking.ordenPuntos){
            holder.getPuntuacion().setText(String.valueOf(puntuacion));
        }else{
            holder.getPuntuacion().setText(victorias);
        }
        holder.getPosicion().setText(String.valueOf(position+1));
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }
}
