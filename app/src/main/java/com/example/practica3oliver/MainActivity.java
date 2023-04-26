package com.example.practica3oliver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    ImageButton botonEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botonEnviar = findViewById(R.id.botonEnviar);

        botonEnviar.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ListadoProducto.class);
            startActivity(intent);
        });
    }
}
