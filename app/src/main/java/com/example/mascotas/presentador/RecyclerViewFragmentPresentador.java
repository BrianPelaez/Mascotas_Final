package com.example.mascotas.presentador;

import android.content.Context;

import androidx.viewpager.widget.ViewPager;

import com.example.mascotas.DB.ConstuctorMascotas;
import com.example.mascotas.fragments.IRecyclerViewFragmentView;
import com.example.mascotas.models.Mascota;

import java.security.AccessControlContext;
import java.util.ArrayList;

public class RecyclerViewFragmentPresentador implements IRecyclerViewFragmentPresentador {

    private IRecyclerViewFragmentView iRecyclerViewFragmentView;
    private Context context;
    private ConstuctorMascotas constuctorMascotas;
    private ArrayList<Mascota> mascotas;

    public RecyclerViewFragmentPresentador(IRecyclerViewFragmentView iRecyclerViewFragmentView, Context context) {
        this.iRecyclerViewFragmentView = iRecyclerViewFragmentView;
        this.context = context;
        obtenerMascotasDB();
    }



    @Override
    public void obtenerMascotasDB() {
        constuctorMascotas = new ConstuctorMascotas(context);
        mascotas = constuctorMascotas.obtenerDatos();
        mostrarMascotasRV();

    }

    @Override
    public void mostrarMascotasRV() {
        iRecyclerViewFragmentView.iniciarAdaptador(iRecyclerViewFragmentView.crearAdaptador(mascotas));
        iRecyclerViewFragmentView.generarLinearLayoutVertical();

    }
}
