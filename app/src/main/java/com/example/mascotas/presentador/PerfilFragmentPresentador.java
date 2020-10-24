package com.example.mascotas.presentador;

import android.content.Context;

import com.example.mascotas.DB.ConstantesBaseDatos;
import com.example.mascotas.DB.ConstuctorMascotas;
import com.example.mascotas.fragments.IPerfilFragmentView;
import com.example.mascotas.fragments.PerfilFragment;
import com.example.mascotas.models.Mascota;

import java.util.ArrayList;

public class PerfilFragmentPresentador implements IPerfilFragmentPresentador{

    private IPerfilFragmentView iPerfilFragmentView;
    private Context context;
    private ArrayList<Mascota> mascotas;
    private ConstuctorMascotas constuctorMascotas;

    public PerfilFragmentPresentador(IPerfilFragmentView iPerfilFragmentView, Context context) {
        this.iPerfilFragmentView = iPerfilFragmentView;
        this.context = context;
        obtenerMascotasFavDB();
    }

    @Override
    public void obtenerMascotasFavDB() {
        constuctorMascotas = new ConstuctorMascotas(context);
        mascotas = constuctorMascotas.obtenerFav();
        mostrarMascotasFavRV();

    }

    @Override
    public void mostrarMascotasFavRV() {
        iPerfilFragmentView.iniciarAdaptador(iPerfilFragmentView.crearAdaptador(mascotas));
        iPerfilFragmentView.generarGridLayoutManager();


    }
}
