package com.example.practica3oliver;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class FichaProducto extends AppCompatActivity {

    private Button botonVolver1;
    private Button botonGuardar1;
    private BaseDatosProducto baseDatos1;
    private EditText editTextNombre1;
    private EditText editTextPrecio1;
    private EditText editTextIngredientes1;
    private EditText editTextPeso1;
    private CheckBox checkBoxDisponible1;
    private TextView reciboNombre;
    private TextView reciboPrecio;
    private TextView reciboIngredientes;
    private TextView reciboPeso;
    Producto productoFinal;

    private List<Integer> productoID;
    private ArrayList<Producto> listaProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficha_producto);

        baseDatos1 = new BaseDatosProducto(getApplicationContext());

        editTextNombre1 = findViewById(R.id.editTextModificarNombre);
        editTextPrecio1 = findViewById(R.id.editTextModificarPrecio);
        editTextIngredientes1 = findViewById(R.id.editTextModificarIngredientes);
        editTextPeso1 = findViewById(R.id.editTextModificarPeso);
        checkBoxDisponible1 = findViewById(R.id.checkboxModificarDisponible);

        botonVolver1 = findViewById(R.id.botonModificarVolver);
        botonGuardar1 = findViewById(R.id.botonModificarGuardar);

        reciboNombre = findViewById(R.id.reciboNombre);
        reciboPrecio = findViewById(R.id.reciboPrecio);
        reciboIngredientes = findViewById(R.id.reciboIngredientes);
        reciboPeso = findViewById(R.id.reciboPeso);

        baseDatos1 = new BaseDatosProducto(FichaProducto.this);
    }

    public void recuperarProductoModificado() {
        Cursor c = baseDatos1.obtenerUnSoloProducto();
        listaProductos = new ArrayList<>();
        productoID = new ArrayList<>();

        while (true) {
            assert c != null;
            if (!c.moveToNext())
                break;
            @SuppressLint("Range") int productID = c.getInt(c.getColumnIndex("id"));
            productoID.add(productID);
        }
        c.close();
        baseDatos1.close();
    }

    @Override
    protected void onStart() {
        super.onStart();

        Bundle parametros_ListadoProductos = getIntent().getExtras();
        if(parametros_ListadoProductos != null){
            baseDatos1 = new BaseDatosProducto(getApplicationContext());
            String mensaje = parametros_ListadoProductos.getString("listaProductos");
            Producto producto = (Producto) parametros_ListadoProductos.getSerializable("listaProductos");

            reciboNombre.setText(producto.getNombre());
            reciboPrecio.setText(String.valueOf(producto.getPrecio()));
            reciboIngredientes.setText(producto.getIngredientes());
            reciboPeso.setText(String.valueOf(producto.getGr()));
            if (producto.getDisponible() == 1){
                checkBoxDisponible1.setChecked(true);
            } else {
                checkBoxDisponible1.setChecked(false);
            }
        }

        botonVolver1.setOnClickListener(view -> {
            Intent i = new Intent(FichaProducto.this, ListadoProducto.class);
            startActivity(i);
        });

        botonGuardar1.setOnClickListener(view -> {
            modificarProducto();
        });
    }

    public void modificarProducto() {
        if (!editTextNombre1.getText().toString().isEmpty() && !editTextPrecio1.getText().toString().isEmpty() &&
                !editTextIngredientes1.getText().toString().isEmpty() && !editTextPeso1.getText().toString().isEmpty()) {

            String nombreAntiguo = reciboNombre.getText().toString();
            String nombre = editTextNombre1.getText().toString();
            double precio = Double.parseDouble(editTextPrecio1.getText().toString().trim());
            String ingredientes = editTextIngredientes1.getText().toString();
            double peso = Double.parseDouble(editTextPeso1.getText().toString().trim());

            if (checkBoxDisponible1.isChecked()) {
                baseDatos1.editarProducto(new Producto(nombre, ingredientes, peso, precio,1), nombreAntiguo);
            } else {
                baseDatos1.editarProducto(new Producto(nombre, ingredientes, peso, precio,0), nombreAntiguo);
            }
            recuperarProductoModificado();

            Toast.makeText(getApplicationContext(), "¡Se ha actualizado el producto!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "¡Debes rellenar todos los datos!", Toast.LENGTH_SHORT).show();
        }
    }
}
