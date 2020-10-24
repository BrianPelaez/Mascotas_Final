package com.example.mascotas.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.mascotas.MainActivity;
import com.example.mascotas.models.Mascota;

import java.util.ArrayList;

public class BaseDatos extends SQLiteOpenHelper {

    Context context;
    public BaseDatos(Context context) {
        super(context, ConstantesBaseDatos.DATABASE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String querryCrearTablaMascota  = "CREATE TABLE " + ConstantesBaseDatos.TABLA_MASCOTA + "(" +
                ConstantesBaseDatos.TABLA_MASCOTA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatos.TABLA_MASCOTA_NOMBRE + " TEXT, " +
                ConstantesBaseDatos.TABLA_MASCOTA_FOTO + " INTEGER" +
                ")";


        String querryCrearTablaLikes = "CREATE TABLE "+ ConstantesBaseDatos.TABLA_LIKES_MASCOTA + "(" +
                ConstantesBaseDatos.TABLA_LIKES_MASCOTA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                ConstantesBaseDatos.TABLA_LIKES_MASCOTA_ID_MASCOTA + " INTEGER, "+
                ConstantesBaseDatos.TABLA_LIKES_MASCOTA_LIKES + " INTEGER, "+
                "FOREIGN KEY ("+ ConstantesBaseDatos.TABLA_LIKES_MASCOTA_ID_MASCOTA +")" + "REFERENCES "+
                ConstantesBaseDatos.TABLA_MASCOTA + "("+ ConstantesBaseDatos.TABLA_MASCOTA_ID+")"+
                ")";

        db.execSQL(querryCrearTablaMascota);
        db.execSQL(querryCrearTablaLikes);

    }

    public ArrayList<Mascota> obtenerMascotasFav(){
        ArrayList<Mascota> mascotas = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        //SELECT *, COUNT(*) FROM mascota, mascota_likes
        //          WHERE mascota.id = mascota_likes.id_mascota
        //          GROUP BY mascota.id HAVING COUNT(mascota_likes.likes)
        //          ORDER BY COUNT(mascota_likes.id_mascota);

        String queryFavoritos = "SELECT *, COUNT(*) FROM " + ConstantesBaseDatos.TABLA_MASCOTA + ", " + ConstantesBaseDatos.TABLA_LIKES_MASCOTA +
                " WHERE " + ConstantesBaseDatos.TABLA_MASCOTA+"."+ConstantesBaseDatos.TABLA_MASCOTA_ID +
                " = " +ConstantesBaseDatos.TABLA_LIKES_MASCOTA+"."+ ConstantesBaseDatos.TABLA_LIKES_MASCOTA_ID_MASCOTA +
                " GROUP BY " + ConstantesBaseDatos.TABLA_MASCOTA +"."+ ConstantesBaseDatos.TABLA_MASCOTA_ID +
                " ORDER BY COUNT (" + ConstantesBaseDatos.TABLA_LIKES_MASCOTA + "." + ConstantesBaseDatos.TABLA_LIKES_MASCOTA_ID_MASCOTA + ")" +
                " DESC LIMIT 5"
                ;
        Cursor registrosFavoritos = db.rawQuery(queryFavoritos, null);

        while (registrosFavoritos.moveToNext()){
            Mascota mascota = new Mascota();
            mascota.setId(registrosFavoritos.getInt(0));
            mascota.setNombre(registrosFavoritos.getString(1));
            mascota.setFoto(registrosFavoritos.getInt(2));
            mascota.setLikes(registrosFavoritos.getInt(6));
            mascotas.add(mascota);
        } // CHEQUEAR PARA OBTENER EL TOP 5 DE LOS LIKEADOS

        db.close();
        return mascotas;
    }

    public ArrayList<Mascota> obtenerMascotas(){
        ArrayList<Mascota> mascotas = new ArrayList<>();
        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLA_MASCOTA;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while (registros.moveToNext()){
            Mascota mascota = new Mascota();
            mascota.setId(registros.getInt(0));
            mascota.setNombre(registros.getString(1));
            mascota.setFoto(registros.getInt(2));

            String querryLikes =    "SELECT COUNT(" + ConstantesBaseDatos.TABLA_LIKES_MASCOTA_LIKES +") as likes " +
                                    " FROM " + ConstantesBaseDatos.TABLA_LIKES_MASCOTA +
                                    " WHERE " + ConstantesBaseDatos.TABLA_LIKES_MASCOTA_ID_MASCOTA + "=" + mascota.getId();

            Cursor registroLikes = db.rawQuery(querryLikes, null);
            if (registroLikes.moveToNext()){
                mascota.setLikes(registroLikes.getInt(0));
            } else {mascota.setLikes(0);}

            mascotas.add(mascota);
        }

        db.close();
        return mascotas;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ ConstantesBaseDatos.TABLA_MASCOTA);
        db.execSQL("DROP TABLE IF EXISTS "+ ConstantesBaseDatos.TABLA_LIKES_MASCOTA);
        onCreate(db);
    }

    public void insertarMascotas(ContentValues contentValues){

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLA_MASCOTA, null, contentValues);
        db.close();

    }

    public void insertarLike(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLA_LIKES_MASCOTA, null, contentValues);
        db.close();
    }

    public int obtenerLikes(Mascota mascota){
        int likes = 0;
        String query =  "SELECT COUNT(" + ConstantesBaseDatos.TABLA_LIKES_MASCOTA_LIKES + ")" +
                        " FROM " + ConstantesBaseDatos.TABLA_LIKES_MASCOTA +
                        " WHERE " + ConstantesBaseDatos.TABLA_LIKES_MASCOTA_ID_MASCOTA + "=" + mascota.getId();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        if (registros.moveToNext()){
            likes = registros.getInt(0);
        }
        db.close();
        return likes;
    }

}
