package com.example.proyecto_bkd.resumenturno.data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto_bkd.R;
import com.example.proyecto_bkd.resumenturno.Feudo;

import java.util.ArrayList;

public class ResumenTurnoAdapter extends RecyclerView.Adapter<ResumenTurnoAdapter.ViewHolder>{
    private ArrayList<Feudo> datos=new ArrayList<>();

    public interface ItemClickListener{
        void onClick(View view, int position, Feudo feudo);
    }
    private ItemClickListener clickListener;

    public void setClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setResults(ArrayList<Feudo> datos){
        this.datos = datos;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView torres,txtPuntuacion;
        private ImageView madera,pez,zanahoria,polvo,seta,plata,oro,cobre,diamante,perla;

        public ViewHolder(View view) {
            super(view);
            madera=(ImageView) view.findViewById(R.id.imgMadera);
            pez=(ImageView) view.findViewById(R.id.imgPez);
            zanahoria=(ImageView) view.findViewById(R.id.imgZanahoria);
            polvo=(ImageView) view.findViewById(R.id.imgPolvo);
            seta=(ImageView) view.findViewById(R.id.imgSeta);
            plata=(ImageView) view.findViewById(R.id.imgPlata);
            oro=(ImageView) view.findViewById(R.id.imgOro);
            cobre=(ImageView) view.findViewById(R.id.imgCobre);
            diamante=(ImageView) view.findViewById(R.id.imgDiamante);
            perla=(ImageView) view.findViewById(R.id.imgPerla);
            torres=(TextView) view.findViewById(R.id.tNumTorres);
            txtPuntuacion=(TextView) view.findViewById(R.id.txtPuntuacion);
            }
        public void setInfo(Feudo feudo){
            txtPuntuacion.setText(feudo.getPuntos());
            torres.setText(feudo.getTorres());
        }

        @Override
        public void onClick(View view) {
            /*
            if (clickListener != null) {
                clickListener.onClick(view, datos.get(getAdapterPosition()));
            }
            */
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
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }
}