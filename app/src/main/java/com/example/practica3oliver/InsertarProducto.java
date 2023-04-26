package com.example.practica3oliver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class InsertarProducto extends AppCompatActivity {

    private Button botonVolver;
    private Button botonGuardar;
    private BaseDatosProducto baseDatos;
    private EditText editTextNombre;
    private EditText editTextPrecio;
    private EditText editTextIngredientes;
    private EditText editTextPeso;
    private CheckBox checkBoxDisponible;
    private ArrayList<Producto> listaProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_producto);

        baseDatos = new BaseDatosProducto(getApplicationContext());

        editTextNombre = findViewById(R.id.editTextNombre);
        editTextPrecio = findViewById(R.id.editTextPrecio);
        editTextIngredientes = findViewById(R.id.editTextIngredientes);
        editTextPeso = findViewById(R.id.editTextPeso);
        checkBoxDisponible = findViewById(R.id.checkBoxDisponible);

        botonVolver = findViewById(R.id.botonVolver);
        botonGuardar = findViewById(R.id.botonGuardar);

        botonVolver.setOnClickListener(view -> {
            Intent intent = new Intent(InsertarProducto.this, ListadoProducto.class);
            intent.putExtra("enviarDatos", listaProductos);
            startActivity(intent);
        });

        botonGuardar.setOnClickListener(view -> {
            insertarProducto();
        });
    }

    public void insertarProducto() {
        if (!editTextNombre.getText().toString().isEmpty() && !editTextPrecio.getText().toString().isEmpty() &&
                !editTextIngredientes.getText().toString().isEmpty() && !editTextPeso.getText().toString().isEmpty()) {

            if (checkBoxDisponible.isChecked()) {
                baseDatos.insertarProducto(editTextNombre.getText().toString(), Double.parseDouble(editTextPrecio.getText().toString().trim()),
                        editTextIngredientes.getText().toString(), Double.parseDouble(editTextPeso.getText().toString().trim()),1);
            } else {
                baseDatos.insertarProducto(editTextNombre.getText().toString(), Double.parseDouble(editTextPrecio.getText().toString().trim()),
                        editTextIngredientes.getText().toString(), Double.parseDouble(editTextPeso.getText().toString().trim()),0);
            }

            recuperarProducto();

            Toast.makeText(getApplicationContext(), "¡Se ha insertado correctamente el producto!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "¡Debes rellenar todos los datos!", Toast.LENGTH_SHORT).show();
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
}
