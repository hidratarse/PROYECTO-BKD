package com.example.proyecto_bkd.partida;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.proyecto_bkd.R;
import com.example.proyecto_bkd.perfiles.data.Perfil;

import java.util.ArrayList;
import java.util.List;

public class JugadorAdapter extends RecyclerView.Adapter<JugadorAdapter.ViewHolder> {
    private List<Perfil> datos = new ArrayList<>();
    private ArrayList<String> seleccionado = new ArrayList<>();

    public interface ItemClickListener {
        void onClick(View view, Perfil perfil);
    }

    private JugadorAdapter.ItemClickListener clickListener;

    public void setClickListener(JugadorAdapter.ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }


    public void setResults(List<Perfil> datos){
        this.datos = datos;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imagen_marco, imagen_perfil;
        private TextView nombre;
        private ImageButton imagen_madera;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imagen_marco = itemView.findViewById(R.id.id_marco);
            nombre = itemView.findViewById(R.id.id_perfiles_nombre);
            imagen_madera = itemView.findViewById(R.id.id_imagen_madera);
            imagen_perfil = itemView.findViewById(R.id.imgProfile);
            itemView.setOnClickListener(this);

        }

        public TextView getNombre() {
            return nombre;
        }

        @Override
        public void onClick(View view) {
            int cont=0;
            if (clickListener != null) {
                clickListener.onClick(view, datos.get(getAdapterPosition()));
                seleccionado.add(datos.get(getAdapterPosition()).getId());
                for (int i = 0; i <seleccionado.size() ; i++) {
                    if(seleccionado.get(i)==datos.get(getAdapterPosition()).getId()){
                        cont++;
                    }
                }
            }
            switch (cont%5){
                case 0:
                    imagen_marco.setImageResource(R.drawable.marco_naranja);
                    break;
                case 1:
                    imagen_marco.setImageResource(R.drawable.marco_rojo);
                    break;
                case 2:
                    imagen_marco.setImageResource(R.drawable.marco_negro);
                    break;
                case 3:
                    imagen_marco.setImageResource(R.drawable.marco_amarillo);
                    break;
                case 4:
                    imagen_marco.setImageResource(R.drawable.marco_rosa);
                    break;
            }
        }
    }

    @NonNull
    @Override
    public JugadorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.perfiles_view, parent, false);
        return new JugadorAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull JugadorAdapter.ViewHolder holder, int position) {
        Perfil p = datos.get(position);

        if (p.getPfpImg() != null) {
            String imgUrl = p.getPfpImg();
            Glide.with(holder.itemView)
                    .load(imgUrl)
                    .into(holder.imagen_perfil);
        } else {
            if (position == 0) {
                holder.imagen_perfil.setImageResource(R.drawable.conejo11);
            }
            if (position == 1) {
                holder.imagen_perfil.setImageResource(R.drawable.conejo2);
            }
            if (position == 2) {
                holder.imagen_perfil.setImageResource(R.drawable.conejo3);
            }
            if (position == 3) {
                holder.imagen_perfil.setImageResource(R.drawable.conejo4);
            }
            if (position == 4) {
                holder.imagen_perfil.setImageResource(R.drawable.conejo5);
            }
            if (position == 5) {
                holder.imagen_perfil.setImageResource(R.drawable.conejo6);
            }
            if (position == 6) {
                holder.imagen_perfil.setImageResource(R.drawable.conejo7);
            }
            if (position == 7) {
                holder.imagen_perfil.setImageResource(R.drawable.conejo8);
            }
            if (position == 8) {
                holder.imagen_perfil.setImageResource(R.drawable.conejo10);
            }
            if (position == 9) {
                holder.imagen_perfil.setImageResource(R.drawable.conejo1);
            }
        }
        holder.getNombre().setText(p.getNombre());
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

}