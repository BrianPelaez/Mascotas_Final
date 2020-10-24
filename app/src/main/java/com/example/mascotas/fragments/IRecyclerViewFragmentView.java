package com.example.mascotas.fragments;

import com.example.mascotas.adapters.MascotaAdaptador;
import com.example.mascotas.models.Mascota;

import java.util.ArrayList;

public interface IRecyclerViewFragmentView {

    void generarLinearLayoutVertical();

    MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas);

    void iniciarAdaptador(MascotaAdaptador adaptador);
}
