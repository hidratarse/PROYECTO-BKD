package com.example.proyecto_bkd.resumenturno;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto_bkd.R;

import java.util.ArrayList;

public class ResumenTurnoAdapter extends RecyclerView.Adapter<ResumenTurnoAdapter.ViewHolder>{
    private ArrayList<Formulario> datos;

    public ResumenTurnoAdapter (ArrayList<Formulario> dataSet){
        datos = new ArrayList<>();
        add(dataSet);
    }

    private void add(ArrayList<Formulario> dataSet) {
        datos.addAll(dataSet);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
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

    }

    @Override
    public int getItemCount() {
        return datos.size();
    }
}
