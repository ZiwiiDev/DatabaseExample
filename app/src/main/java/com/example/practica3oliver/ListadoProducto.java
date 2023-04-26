package com.example.practica3oliver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class ListadoProducto extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private BaseDatosProducto baseDatos;
    private ArrayList<Producto> listaProductos;
    private Adaptador adaptador;
    private int posicion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_producto);

        /* Referencia al toolbar */
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Referencia a la vista del layout
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1)); // Contexto y el número de columnas

        baseDatos = new BaseDatosProducto(getApplicationContext());

        Log.e("numero_filas", String.valueOf(baseDatos.numProductos()));

        if (baseDatos.numProductos() == 0) {
            Toast.makeText(getApplicationContext(), "¡Debe cargar datos en la base de datos!", Toast.LENGTH_SHORT).show();
        }
    }

    public void recuperarProducto() {
        Cursor c = baseDatos.obtenerProducto();
        listaProductos = new ArrayList<>();

        if (c.getCount() > 0) {
            c.moveToFirst();

            // Mientras que no sea la última posición
            while (!c.isAfterLast()) {
                String nombre = c.getString(c.getColumnIndexOrThrow("nombre"));
                String ingredientes = c.getString(c.getColumnIndexOrThrow("ingredientes"));
                double gr = c.getDouble(c.getColumnIndexOrThrow("gr"));
                double precio = c.getDouble(c.getColumnIndexOrThrow("precio"));
                int disponible = c.getInt(c.getColumnIndexOrThrow("disponible"));

                listaProductos.add(new Producto(0, nombre, ingredientes, gr, precio, disponible));

                c.moveToNext();
            }
        }
        c.close();
        baseDatos.close();

        for (int i=0; i < listaProductos.size(); i++) {
            Log.e("nombre", listaProductos.get(i).getNombre());
            Log.e("ingredientes", listaProductos.get(i).getIngredientes());
            Log.e("gr", String.valueOf(listaProductos.get(i).getGr()));
            Log.e("precio", String.valueOf(listaProductos.get(i).getPrecio()));
            Log.e("disponible", String.valueOf(listaProductos.get(i).getDisponible()));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.insertarProducto:
                Intent intent = new Intent(ListadoProducto.this, InsertarProducto.class);
                startActivity(intent);
                return true;
            case R.id.preferencias:
                Intent i = new Intent(getApplicationContext(), PrefActivity.class);
                startActivity(i);
                return true;
            case R.id.eliminarProducto:
                Bundle enviarDatos = new Bundle();
                enviarDatos.putSerializable("listaProductos", listaProductos);

                Intent u = new Intent(ListadoProducto.this, EliminarProducto.class);
                u.putExtra("BUNDLE", enviarDatos);
                startActivity(u);
                return true;
        }
        return true;
    }

    public void recuperarProductosDisponibles() {
        Cursor c = baseDatos.obtenerProductosDisponibles();
        listaProductos = new ArrayList<>();

        if (c.getCount() > 0) {
            c.moveToFirst();

            // Mientras que no sea la última posición
            while (!c.isAfterLast()) {
                int id = c.getInt(c.getColumnIndexOrThrow("id"));
                String nombre = c.getString(c.getColumnIndexOrThrow("nombre"));
                String ingredientes = c.getString(c.getColumnIndexOrThrow("ingredientes"));
                double gr = c.getDouble(c.getColumnIndexOrThrow("gr"));
                double precio = c.getDouble(c.getColumnIndexOrThrow("precio"));
                int disponible = c.getInt(c.getColumnIndexOrThrow("disponible"));

                listaProductos.add(new Producto(id, nombre, ingredientes, gr, precio, disponible));

                c.moveToNext();
            }
        }
        c.close();
        baseDatos.close();
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Obtengo el valor del SwitchPreference
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);

        // Establecemos el valor en el TextView
        boolean SwitchPreference = sp.getBoolean("SwitchPreference_key", false);

        if (SwitchPreference) {
            // Mostrar los productos que estén disponibles
            recuperarProductosDisponibles();

            adaptador = new Adaptador(listaProductos);
            recyclerView.setAdapter(adaptador);

            adaptador.setOnClickListener(view -> {
                posicion = recyclerView.getChildAdapterPosition(view);
                adaptador.notifyItemChanged(posicion);

                if (baseDatos.numProductos() != 0) {
                    Producto productoFinal = listaProductos.get(posicion);

                    Intent intent = new Intent(ListadoProducto.this, FichaProducto.class);
                    intent.putExtra("listaProductos", productoFinal);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "¡Debe cargar datos en la base de datos!", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            //Mostrar todos los productos
            recuperarProducto();

            adaptador = new Adaptador(listaProductos);
            recyclerView.setAdapter(adaptador);

            adaptador.setOnClickListener(view -> {
                posicion = recyclerView.getChildAdapterPosition(view);
                adaptador.notifyItemChanged(posicion);

                if (baseDatos.numProductos() != 0) {
                    Producto productoFinal = listaProductos.get(posicion);

                    Intent intent = new Intent(ListadoProducto.this, FichaProducto.class);
                    intent.putExtra("listaProductos", productoFinal);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "¡Debe cargar datos en la base de datos!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
