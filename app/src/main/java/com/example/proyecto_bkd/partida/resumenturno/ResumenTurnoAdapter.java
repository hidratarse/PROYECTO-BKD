package com.example.proyecto_bkd.partida.resumenturno;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto_bkd.R;
import com.example.proyecto_bkd.partida.data.Feudo;
import com.example.proyecto_bkd.partida.resumenturno.actividades.ResumenTurno;

import java.util.ArrayList;

public class ResumenTurnoAdapter extends RecyclerView.Adapter<ResumenTurnoAdapter.ViewHolder>{
    private ArrayList<Feudo> datos;

    public interface ItemClickListener{
        void onClick(View view, Feudo feudo);
    }
    private ItemClickListener clickListener;

    public void setClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public ResumenTurnoAdapter (ArrayList<Feudo> feudoArrayList){
        this.datos=feudoArrayList;
    }

    public void setResults(ArrayList<Feudo> feudoArrayList){
        this.datos=feudoArrayList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView torres,txtPuntuacion;
        private ImageView madera,pez,zanahoria,polvo,seta,plata,oro,cobre,diamante,perla;

        public ViewHolder(View view) {
            super(view);
            madera= view.findViewById(R.id.imgMadera);
            pez= view.findViewById(R.id.imgPez);
            zanahoria= view.findViewById(R.id.imgZanahoria);
            polvo= view.findViewById(R.id.imgPolvo);
            seta= view.findViewById(R.id.imgSeta);
            plata= view.findViewById(R.id.imgPlata);
            oro= view.findViewById(R.id.imgOro);
            cobre= view.findViewById(R.id.imgCobre);
            diamante= view.findViewById(R.id.imgDiamante);
            perla= view.findViewById(R.id.imgPerla);
            torres= view.findViewById(R.id.tNumTorres);
            txtPuntuacion= view.findViewById(R.id.txtPuntuacion);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) {
                clickListener.onClick(view, datos.get(getAdapterPosition()));
            }
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.feudo,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Feudo f =datos.get(position);
        holder.torres.setText(f.getTorres()+"");
        holder.txtPuntuacion.setText(f.getPuntos()+"");
        if(f.getRecursos().contains("madera")){
            holder.madera.setVisibility(View.VISIBLE);
        }else{
            holder.madera.setVisibility(View.GONE);
        }
        if(f.getRecursos().contains("zanahoria")){
            holder.zanahoria.setVisibility(View.VISIBLE);
        }else{
            holder.zanahoria.setVisibility(View.GONE);
        }
        if(f.getRecursos().contains("pez")){
            holder.pez.setVisibility(View.VISIBLE);
        }else{
            holder.pez.setVisibility(View.GONE);
        }
        if(f.getRecursos().contains("polvo")){
            holder.polvo.setVisibility(View.VISIBLE);
        }else{
            holder.polvo.setVisibility(View.GONE);
        }
        if(f.getRecursos().contains("seta")){
            holder.seta.setVisibility(View.VISIBLE);
        }else{
            holder.seta.setVisibility(View.GONE);
        }
        if(f.getRecursos().contains("perla")){
            holder.perla.setVisibility(View.VISIBLE);
        }else{
            holder.perla.setVisibility(View.GONE);
        }
        if(f.getRecursos().contains("diamante")){
            holder.diamante.setVisibility(View.VISIBLE);
        }else{
            holder.diamante.setVisibility(View.GONE);
        }
        if(f.getRecursos().contains("oro")){
            holder.oro.setVisibility(View.VISIBLE);
        }else{
            holder.oro.setVisibility(View.GONE);
        }
        if(f.getRecursos().contains("plata")){
            holder.plata.setVisibility(View.VISIBLE);
        }else{
            holder.plata.setVisibility(View.GONE);
        }
        if(f.getRecursos().contains("cobre")){
            holder.cobre.setVisibility(View.VISIBLE);
        }else{
            holder.cobre.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }
}