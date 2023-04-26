package com.example.practica3oliver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EliminarProducto extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Producto> listaProductos;
    private BaseDatosProducto baseDatos;
    private AdaptadorBoton adaptadorBoton;
    private int posicion;
    private Button botonVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_producto);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1)); // Contexto y el número de columnas

        baseDatos = new BaseDatosProducto(getApplicationContext());

        Log.e("numero_filas", String.valueOf(baseDatos.numProductos()));

        botonVolver = findViewById(R.id.botonVolverEliminar);

        botonVolver.setOnClickListener(view -> {
            Intent intent = new Intent(EliminarProducto.this, ListadoProducto.class);
            startActivity(intent);
            this.finish();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = getIntent();
        Bundle reciboDatos = intent.getBundleExtra("BUNDLE");
        listaProductos = (ArrayList<Producto>) reciboDatos.getSerializable("listaProductos");

        adaptadorBoton = new AdaptadorBoton (listaProductos);
        recyclerView.setAdapter(adaptadorBoton);

        adaptadorBoton.setOnClickListener(v -> {
            posicion = recyclerView.getChildAdapterPosition(v);
            adaptadorBoton.notifyItemChanged(posicion);
            baseDatos.eliminarProducto(listaProductos.get(posicion).getId());


            Bundle enviarDatos = new Bundle();
            enviarDatos.putSerializable("listaProductosEliminados", listaProductos);

            Intent u = new Intent(EliminarProducto.this, ListadoProducto.class);
            u.putExtra("BUNDLE", enviarDatos);
            startActivity(u);
        });
    }
}
