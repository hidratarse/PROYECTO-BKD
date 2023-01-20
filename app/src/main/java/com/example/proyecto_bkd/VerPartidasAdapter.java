package com.example.proyecto_bkd;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

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
        private final TextView fecha;
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

            fecha = (TextView) view.findViewById(R.id.tFecha);
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

        public void setInfo(String nombre, int precio_val) {
            fecha.setText(nombre);
            precio.setText(String.valueOf(precio_val));
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

        return new VerPartidasAdapter().ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(VerPartidasAdapter.ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        ResumenPartidas p = datos.get(position);
        viewHolder.setInfo(p.getNombre(),p.getPrecio());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return datos.size();
    }

    public void add(ArrayList<VerPartidasAdapter> dataSet){
        datos.addAll(dataSet);
        notifyDataSetChanged();
    }
}
