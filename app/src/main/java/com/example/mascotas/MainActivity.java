package com.example.mascotas;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.mascotas.adapters.MascotaAdaptador;
import com.example.mascotas.adapters.PageAdapter;
import com.example.mascotas.adapters.PerfilAdaptador;
import com.example.mascotas.fragments.IPerfilFragmentView;
import com.example.mascotas.fragments.IRecyclerViewFragmentView;
import com.example.mascotas.fragments.PerfilFragment;
import com.example.mascotas.fragments.RecyclerViewFragment;
import com.example.mascotas.models.Mascota;
import com.example.mascotas.presentador.IPerfilFragmentPresentador;
import com.example.mascotas.presentador.PerfilFragmentPresentador;
import com.example.mascotas.presentador.RecyclerViewFragmentPresentador;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {


    private FloatingActionButton fibCamara;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar miActionbar;
    private ArrayList<Fragment> fragments;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        miActionbar = (Toolbar) findViewById(R.id.miActionbar);
        context = this;

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

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                fragments.get(1).onResume();

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }


    private ArrayList<Fragment> agregarFragments(){
        fragments = new ArrayList<>();
        fragments.add(new RecyclerViewFragment());
        fragments.add(new PerfilFragment());
        return fragments;
    }

    private void inicializarViewPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        tabLayout.setupWithViewPager(viewPager, true);
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