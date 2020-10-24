package com.example.mascotas.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import com.example.mascotas.R;
import com.example.mascotas.adapters.MascotaAdaptador;
import com.example.mascotas.adapters.PerfilAdaptador;
import com.example.mascotas.models.Mascota;
import com.example.mascotas.presentador.IPerfilFragmentPresentador;
import com.example.mascotas.presentador.PerfilFragmentPresentador;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PerfilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PerfilFragment extends Fragment implements IPerfilFragmentView {


    RecyclerView rvPerfil;
    IPerfilFragmentPresentador presentador;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PerfilFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PerfilFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PerfilFragment newInstance(String param1, String param2) {
        PerfilFragment fragment = new PerfilFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v = inflater.inflate(R.layout.fragment_perfil, container, false);

        rvPerfil = (RecyclerView) v.findViewById(R.id.rvPerfil);
        presentador = new PerfilFragmentPresentador(this, getContext());

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        presentador = new PerfilFragmentPresentador(this, getContext());
    }

    @Override
    public void generarGridLayoutManager(){
        GridLayoutManager glm = new GridLayoutManager(getActivity(), 3);
        glm.setOrientation(LinearLayoutManager.VERTICAL);
        rvPerfil.setLayoutManager(glm);

    }

    @Override
    public PerfilAdaptador crearAdaptador(ArrayList<Mascota> mascotas){
        PerfilAdaptador adaptador = new PerfilAdaptador(mascotas, getActivity());
        return adaptador;
    }
    @Override
    public void iniciarAdaptador(PerfilAdaptador adaptador){
        rvPerfil.setAdapter(adaptador);

    }


}