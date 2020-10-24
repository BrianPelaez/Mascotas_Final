package com.example.mascotas.DB;

import android.content.ContentValues;
import android.content.Context;

import com.example.mascotas.R;
import com.example.mascotas.models.Mascota;


import java.util.ArrayList;

public class ConstuctorMascotas {

    private static final int LIKE = 1;
    private Context context;

    public ConstuctorMascotas(Context context){
        this.context = context;
    }

    public ArrayList<Mascota> obtenerFav(){
        //CHEQUEAR ESTO CON EL NUEVO PRESENTADOR
        BaseDatos db = new BaseDatos(context);
        return db.obtenerMascotasFav();
    }

    public ArrayList<Mascota> obtenerDatos(){

        BaseDatos db = new BaseDatos(context);
        insertarContactos(db);
        return db.obtenerMascotas();
    }

    public void insertarContactos(BaseDatos db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLA_MASCOTA_NOMBRE, "Pepe");
        contentValues.put(ConstantesBaseDatos.TABLA_MASCOTA_FOTO, R.drawable.perro);

        db.insertarMascotas(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLA_MASCOTA_NOMBRE, "Lupi");
        contentValues.put(ConstantesBaseDatos.TABLA_MASCOTA_FOTO, R.drawable.gato);

        db.insertarMascotas(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLA_MASCOTA_NOMBRE, "Lizi");
        contentValues.put(ConstantesBaseDatos.TABLA_MASCOTA_FOTO, R.drawable.perro);

        db.insertarMascotas(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLA_MASCOTA_NOMBRE, "Wanda");
        contentValues.put(ConstantesBaseDatos.TABLA_MASCOTA_FOTO, R.drawable.perro);

        db.insertarMascotas(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLA_MASCOTA_NOMBRE, "Felix");
        contentValues.put(ConstantesBaseDatos.TABLA_MASCOTA_FOTO, R.drawable.gato);

        db.insertarMascotas(contentValues);

    }

    public void darLike(Mascota mascota){
        BaseDatos db = new BaseDatos(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLA_LIKES_MASCOTA_ID_MASCOTA, mascota.getId());
        contentValues.put(ConstantesBaseDatos.TABLA_LIKES_MASCOTA_LIKES, LIKE);
        db.insertarLike(contentValues);
    }

    public int obtenerLikes(Mascota mascota){
        BaseDatos db = new BaseDatos(context);
        return db.obtenerLikes(mascota);
    }

}
