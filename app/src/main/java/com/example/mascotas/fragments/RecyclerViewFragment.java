package com.example.mascotas.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.mascotas.R;
import com.example.mascotas.adapters.MascotaAdaptador;
import com.example.mascotas.models.Mascota;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecyclerViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecyclerViewFragment extends Fragment {

    private RecyclerView rvMascotas;
    private ArrayList<Mascota> mascotas;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RecyclerViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecyclerViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecyclerViewFragment newInstance(String param1, String param2) {
        RecyclerViewFragment fragment = new RecyclerViewFragment();
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
        View v = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        crearMascotas();

        rvMascotas = (RecyclerView) v.findViewById(R.id.rvMascotas);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvMascotas.setLayoutManager(llm);
        iniciarAdaptador();

        return v;
    }

    public void iniciarAdaptador(){

        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas, getActivity());
        rvMascotas.setAdapter(adaptador);

    }

    public void crearMascotas(){

        mascotas = new ArrayList<Mascota>();

        mascotas.add(new Mascota("Loli",R.drawable.gato, 1));
        mascotas.add(new Mascota("Lupi", R.drawable.perro, 5));
        mascotas.add(new Mascota("Lolo", R.drawable.perro, 0));
        mascotas.add(new Mascota("Wanda",R.drawable.perro, 0));
        mascotas.add(new Mascota("Leila", R.drawable.gato, 3));
        mascotas.add(new Mascota("Lizi",R.drawable.perro, 0));
        mascotas.add(new Mascota("Roncho", R.drawable.gato, 3));
        mascotas.add(new Mascota("Pechu",R.drawable.perro, 0));
        mascotas.add(new Mascota("Picu", R.drawable.gato, 3));

    }
}