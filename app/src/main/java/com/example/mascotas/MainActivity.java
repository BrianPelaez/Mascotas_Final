package com.example.mascotas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.mascotas.adapters.MascotaAdaptador;
import com.example.mascotas.adapters.PageAdapter;
import com.example.mascotas.fragments.PerfilFragment;
import com.example.mascotas.fragments.RecyclerViewFragment;
import com.example.mascotas.models.Mascota;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvMascotas;
    private LinearLayoutManager llm;
    private ArrayList<Mascota> mascotas;
    private FloatingActionButton fibCamara;
    private ImageButton ibLike;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar miActionbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       Toolbar miActionbar = (Toolbar) findViewById(R.id.miActionbar);

        if (miActionbar != null){
            setSupportActionBar(miActionbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }


        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        inicializarViewPager();


        fibCamara = (FloatingActionButton) findViewById(R.id.fibCamara);
        fibCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Camara", Snackbar.LENGTH_LONG).setAction("accion", null).show();
            }
        });
        fibCamara.show();

    }

    private ArrayList<Fragment> agregarFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new RecyclerViewFragment());
        fragments.add(new PerfilFragment());
        return fragments;
    }

    private void inicializarViewPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.estrella);
        tabLayout.getTabAt(1).setIcon(R.drawable.estrella);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.item_like:
                intent = new Intent(this, mascotas_likeadas.class);
                startActivity(intent);
                break;
            case R.id.item_contacto:
                intent = new Intent(this, Formulario.class);
                startActivity(intent);
                break;
            case R.id.item_about:
                intent = new Intent(this, Bio.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}