package com.example.mascotas.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mascotas.R;
import com.example.mascotas.models.Mascota;

import java.util.ArrayList;

public class PerfilAdaptador extends RecyclerView.Adapter<PerfilAdaptador.PerfilViewHolder> {

    ArrayList<Mascota> mascotas;
    Activity activity;

    public PerfilAdaptador(ArrayList<Mascota> mascotas, Activity activity){
        this.mascotas = mascotas;
        this.activity = activity;
    }

    @NonNull
    @Override
    public PerfilViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_fotosperfil, parent, false);
        return new PerfilViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PerfilViewHolder perfilViewHolder, int position) {

        final Mascota mascota = mascotas.get(position);
        perfilViewHolder.ivMascota.setImageResource(mascota.getFoto());
        perfilViewHolder.tvLikes.setText(String.valueOf(mascota.getLikes()));
        perfilViewHolder.ivHueso.setImageResource(R.drawable.ic_bone_dark);


    }


    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class PerfilViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivMascota;
        private ImageView ivHueso;
        private TextView tvLikes;


        public PerfilViewHolder(View v){
            super(v);

            ivMascota = (ImageView) v.findViewById(R.id.ivMascota);
            ivHueso = (ImageView) v.findViewById(R.id.ivHueso);
            tvLikes = (TextView) v.findViewById(R.id.tvLikes);

        }





    }
}
