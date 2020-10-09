package com.example.mascotas;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvMascotas;
    LinearLayoutManager llm;
    ArrayList<Mascota> mascotas;
    FloatingActionButton fibCamara;
    ImageButton ibLike;
    androidx.appcompat.widget.Toolbar miActionbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        miActionbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.miActionbar);
        setSupportActionBar(miActionbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        crearMascotas();

        rvMascotas = (RecyclerView) findViewById(R.id.rvMascotas);

        llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvMascotas.setLayoutManager(llm);
        iniciarAdaptador();

        fibCamara = (FloatingActionButton) findViewById(R.id.fibCamara);
        fibCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Camara", Snackbar.LENGTH_LONG).setAction("accion", null).show();
            }
        });
        fibCamara.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_like:
                Intent intent = new Intent(this, mascotas_likeadas.class);
                startActivity(intent);
                return true;
            default: return super.onOptionsItemSelected(item);
        }

    }

    public void iniciarAdaptador(){

        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas, this);
        rvMascotas.setAdapter(adaptador);

    }

    public void crearMascotas(){

        mascotas = new ArrayList<Mascota>();

        mascotas.add(new Mascota("Loli",R.drawable.gato, 1));
        mascotas.add(new Mascota("Lupi", R.drawable.perro, 5));
        mascotas.add(new Mascota("Lolo", R.drawable.perro, 0));
        mascotas.add(new Mascota("Wanda",R.drawable.perro, 0));
        mascotas.add(new Mascota("Leila", R.drawable.gato, 3));


    }


}