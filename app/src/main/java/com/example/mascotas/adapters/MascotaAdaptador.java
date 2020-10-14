package com.example.mascotas.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mascotas.models.Mascota;
import com.example.mascotas.R;

import java.util.ArrayList;

public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder> {

    ArrayList<Mascota> mascotas;
    Activity activity;

    public MascotaAdaptador(ArrayList<Mascota> mascotas, Activity activity) {
        this.mascotas = mascotas;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MascotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascotas, parent, false);
        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MascotaViewHolder mascotaViewHolder, int position) {

        final Mascota mascota = mascotas.get(position);
        mascotaViewHolder.ivMascota.setImageResource(mascota.getFoto());
        mascotaViewHolder.tvLikes.setText(String.valueOf(mascota.getLikes()));
        mascotaViewHolder.tvNombre.setText(mascota.getNombre());


        mascotaViewHolder.ibLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mascotaViewHolder.tvLikes.setText(String.valueOf(mascota.getLikes()+1));
            }
        });


    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder{

        private TextView tvNombre;
        private ImageView ivMascota;
        private TextView tvLikes;
        private ImageButton ibLike;

        public MascotaViewHolder(View v){
            super(v);

            tvNombre = (TextView) v.findViewById(R.id.tvNombre);
            tvLikes = (TextView) v.findViewById(R.id.tvLikes);
            ivMascota = (ImageView) v.findViewById(R.id.ivMascota);
            ibLike = (ImageButton) v.findViewById(R.id.ibLike);


        }

    }
}


