package com.example.proyecto_bkd.partida;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto_bkd.R;

import java.util.ArrayList;

public class VerPartidasAdapter extends RecyclerView.Adapter<VerPartidasAdapter.ViewHolder> {
    private ArrayList<ResumenPartidas> datos;

    /*
     * Relacionado con el evento.
     */
    public interface ItemClickListener {
        void onClick(View view, int position, ResumenPartidas product);
    }

    private ItemClickListener clickListener;

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
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
            // Define click listener for the ViewHolder's View

            fechaPart = (TextView) view.findViewById(R.id.tFecha);
            jug1 = (TextView) view.findViewById(R.id.tJugador1);
            jug2 = (TextView) view.findViewById(R.id.tJugador2);
            jug3 = (TextView) view.findViewById(R.id.tJugador3);
            jug4 = (TextView) view.findViewById(R.id.tJugador4);
            ptos1= (TextView) view.findViewById(R.id.tPuntos1);
            ptos2 = (TextView) view.findViewById(R.id.tPuntos2);
            ptos3= (TextView) view.findViewById(R.id.tPuntos3);
            ptos4= (TextView) view.findViewById(R.id.tPuntos4);
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
            // Si tengo un manejador de evento lo propago con el índice
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition(),datos.get(getAdapterPosition()));
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public VerPartidasAdapter(ArrayList<ResumenPartidas> dataSet) {
        datos = new ArrayList<ResumenPartidas>();
        add(dataSet);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public VerPartidasAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.lista_partidas, viewGroup, false);

        return new VerPartidasAdapter.ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(VerPartidasAdapter.ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        ResumenPartidas p = datos.get(position);
        if(p.getJ3()==null){
            viewHolder.setInfo(p.getFecha(),p.getJ1(),p.getJ2(),p.getPto1(),p.getPto2());
        }else if(p.getJ4()==null){
            viewHolder.setInfo(p.getFecha(),p.getJ1(),p.getJ2(),p.getJ3(),p.getPto1(),p.getPto2(),p.getPto3());
        }else{
            viewHolder.setInfo(p.getFecha(),p.getJ1(),p.getJ2(),p.getJ3(),p.getJ4(),p.getPto1(),p.getPto2(),p.getPto3(),p.getPto4());
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return datos.size();
    }

    public void add(ArrayList<ResumenPartidas> dataSet){
        datos.addAll(dataSet);
        notifyDataSetChanged();
    }
}
