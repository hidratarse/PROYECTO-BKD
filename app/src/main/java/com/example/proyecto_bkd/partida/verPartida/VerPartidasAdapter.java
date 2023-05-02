package com.example.proyecto_bkd.partida.verPartida;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto_bkd.R;
import com.example.proyecto_bkd.partida.data.Partidas;

import java.util.ArrayList;
import java.util.List;

public class VerPartidasAdapter extends RecyclerView.Adapter<VerPartidasAdapter.ViewHolder> {
    private List<Partidas> datos = new ArrayList<>();

    public interface ItemClickListener {
        void onClick(View view, Partidas partidas);
    }

    private ItemClickListener clickListener;

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public void setResults(List<Partidas> datos){
        this.datos = datos;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView fechaPart;
        private final TextView jug1;
        private final TextView jug2;
        private final TextView jug3;
        private final TextView jug4;
        private final TextView ptos1;
        private final TextView ptos2;
        private final TextView ptos3;
        private final TextView ptos4;


        public ViewHolder(View view) {
            super(view);
            fechaPart = view.findViewById(R.id.tFecha);
            jug1 = view.findViewById(R.id.tJugador1);
            jug2 = view.findViewById(R.id.tJugador2);
            jug3 = view.findViewById(R.id.tJugador3);
            jug4 = view.findViewById(R.id.tJugador4);
            ptos1= view.findViewById(R.id.tPuntos1);
            ptos2 = view.findViewById(R.id.tPuntos2);
            ptos3= view.findViewById(R.id.tPuntos3);
            ptos4= view.findViewById(R.id.tPuntos4);
            view.setOnClickListener(this);
        }

        public void setInfo(String fecha, String j1, String j2, int pt1, int pt2) {
            fechaPart.setText(fecha);
            jug1.setText(j1);
            jug3.setText(j2);
            ptos1.setText(pt1+"");
            ptos3.setText(pt2+"");
            jug2.setVisibility(View.GONE);
            jug4.setVisibility(View.GONE);
            ptos2.setVisibility(View.GONE);
            ptos4.setVisibility(View.GONE);
        }
        public void setInfo(String fecha, String j1, String j2, String j3, int pt1, int pt2, int pt3) {
            fechaPart.setText(fecha);
            jug1.setText(j1);
            jug2.setText(j2);
            jug3.setText(j3);
            ptos1.setText(pt1+"");
            ptos2.setText(pt2+"");
            ptos3.setText(pt3+"");
        }
        public void setInfo(String fecha, String j1, String j2, String j3, String j4, int pt1, int pt2, int pt3, int pt4) {
            fechaPart.setText(fecha);
            jug1.setText(j1);
            jug2.setText(j2);
            jug3.setText(j3);
            jug4.setText(j4);
            ptos1.setText(pt1+"");
            ptos2.setText(pt2+"");
            ptos3.setText(pt3+"");
            ptos4.setText(pt4+"");
        }
        @Override
        public void onClick(View view) {
            if (clickListener != null)
                clickListener.onClick(view, datos.get(getAdapterPosition()));

        }
    }

    @Override
    public VerPartidasAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.lista_partidas, viewGroup, false);

        return new VerPartidasAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VerPartidasAdapter.ViewHolder viewHolder, final int position) {
        Partidas p = datos.get(position);
        if(p.getJ3()==null){
            viewHolder.setInfo(p.getFecha(),p.getJ1().getNomJugador(),p.getJ2().getNomJugador(), p.getJ1().getPuntos(),p.getJ2().getPuntos());
        }else if(p.getJ4()==null){
            viewHolder.setInfo(p.getFecha(),p.getJ1().getNomJugador(),p.getJ2().getNomJugador(),p.getJ3().getNomJugador(),p.getJ1().getPuntos(),p.getJ2().getPuntos(),p.getJ3().getPuntos());
        }else{
            viewHolder.setInfo(p.getFecha(),p.getJ1().getNomJugador(),p.getJ2().getNomJugador(),p.getJ3().getNomJugador(),p.getJ4().getNomJugador(),p.getJ1().getPuntos(),p.getJ2().getPuntos(),p.getJ3().getPuntos(),p.getJ4().getPuntos());
        }

    }

    @Override
    public int getItemCount() {
        return datos.size();
    }
}
