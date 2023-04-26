package com.example.practica3oliver;

import android.content.Context;
import android.content.Intent;
import android.graphics.ImageFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adaptador extends RecyclerView.Adapter<Adaptador.MiViewHolderPrincipal> implements View.OnClickListener{

    static ArrayList<Producto> listaProductos;
    private View.OnClickListener listener;

    public Adaptador(ArrayList<Producto> listaProductos) {
        Adaptador.listaProductos = listaProductos;
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null)
            listener.onClick(view);
    }

    @NonNull
    @Override
    public MiViewHolderPrincipal onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemproductos, null, false);
        view.setOnClickListener( this);
        return new MiViewHolderPrincipal(view);
    }

    @Override
    public void onBindViewHolder(MiViewHolderPrincipal holder, int position) {
        holder.nombre.setText(listaProductos.get(position).getNombre());
        holder.precio.setText(String.valueOf(listaProductos.get(position).getPrecio()));

        if (listaProductos.get(position).getDisponible() == 1) {
            holder.iconoSiNo.setImageResource(R.mipmap.icono_si_foreground);
        } else {
            holder.iconoSiNo.setImageResource(R.mipmap.icono_no_foreground);
        }
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public static class MiViewHolderPrincipal extends RecyclerView.ViewHolder {
            private final TextView nombre;
            private final TextView precio;
            private final ImageView iconoSiNo;

        public MiViewHolderPrincipal(View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.textViewNombreProducto);
            precio = itemView.findViewById(R.id.textViewPrecioProducto);
            iconoSiNo = itemView.findViewById(R.id.iconoSiNo);
        }
    }
}
