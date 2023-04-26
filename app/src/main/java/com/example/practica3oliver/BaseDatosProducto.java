package com.example.practica3oliver;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class BaseDatosProducto extends SQLiteOpenHelper {

    private static final String NOMBRE_BD = "baseDatosProducto";
    private static final int VERSION_BD = 1;

    public BaseDatosProducto (Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Producto (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, nombre VARCHAR(40), ingredientes VARCHAR(40), gr DOUBLE, precio DOUBLE, disponible INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Producto");
        onCreate(db);
    }

    public void insertarProducto (String nombre, double precio, String ingredientes, double gr, int disponible) {

         /*
         *
         * Almacenar un producto en la base de datos.
         *
         */

        // Modo escritura y lectura
        SQLiteDatabase db = getWritableDatabase();

        // Declaro valores
        ContentValues valores = new ContentValues();
        valores.put("nombre", nombre);
        valores.put("precio", precio);
        valores.put("ingredientes", ingredientes);
        valores.put("gr", gr);
        valores.put("disponible", disponible);

        if (db.isOpen()) {
            // OPERACIONES
            db.insert("Producto", null, valores);
            db.close();
        }
    }

    public void editarProducto (Producto p, String nombre) {
        // Modo escritura y lectura
        SQLiteDatabase db = getWritableDatabase();

        // Declaro valores
        ContentValues valores = new ContentValues();
        valores.put("nombre", p.getNombre());
        valores.put("precio", p.getPrecio());
        valores.put("ingredientes", p.getIngredientes());
        valores.put("gr", p.getGr());
        valores.put("disponible", p.getDisponible());

        String[] args = new String[]{nombre};

        if (db.isOpen()) {
            // OPERACIONES
            db.update("Producto", valores, "nombre = ?",args);
            db.close();
        }
    }

    public void eliminarProducto (int id) {
        // Modo escritura y lectura
        SQLiteDatabase db = getWritableDatabase();

        // Declaro valores
        String[] argumento = new String[]{String.valueOf(id)};

        if (db.isOpen()) {
            // OPERACIONES
            db.delete("Producto", "id = ?", argumento);
            db.close();
        }
    }

    public Cursor obtenerUnSoloProducto () {
        // Modo escritura y lectura
        SQLiteDatabase db = getReadableDatabase();

        // Declarar un cursor
        Cursor c = null;

        // Declaro valores
        // String[] argumento = new String[]{String.valueOf(id)};

        if (db.isOpen()) {
            c = db.rawQuery("SELECT * FROM Producto WHERE id = ?", null);
        }
        return c;
    }

    public Cursor obtenerProducto () {

        /*
         *
         * Obtendremos el nombre de todos los productos almacenados en la base de datos.
         *
         */

        // Modo escritura y lectura
        SQLiteDatabase db = getReadableDatabase();

        // Declarar un cursor
        Cursor cursor = null;

        // EN LAS CONSULTAS NO SE CIERRA LA BASE DE DATOS
        if (db.isOpen()) {
            cursor = db.rawQuery("SELECT * FROM Producto", null);
        }
        return cursor;
    }

    public Cursor obtenerProductosDisponibles() {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = null;

        if (db.isOpen()) {
            cursor = db.rawQuery("SELECT * FROM Producto WHERE disponible = 1", null);
        }
        return cursor;
    }

    // Consultar el número de registros
    public int numProductos () {
        return (int) DatabaseUtils.queryNumEntries(this.getReadableDatabase(), "Producto");
    }
}
