package com.example.mascotas.fragments;

import com.example.mascotas.adapters.PerfilAdaptador;
import com.example.mascotas.models.Mascota;

import java.util.ArrayList;

public interface IPerfilFragmentView {

    void iniciarAdaptador(PerfilAdaptador adaptador);

    PerfilAdaptador crearAdaptador(ArrayList<Mascota> mascotas);

    void generarGridLayoutManager();


}
