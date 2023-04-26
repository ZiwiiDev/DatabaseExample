package com.example.practica3oliver;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorBoton extends RecyclerView.Adapter<AdaptadorBoton.MiViewHolder> implements View.OnClickListener{

    static ArrayList<Producto> listaProductos;
    private View.OnClickListener listener;
    BaseDatosProducto basedatos;

    public AdaptadorBoton(ArrayList<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @SuppressLint("SuspiciousIndentation")
    @Override
    public void onClick(View view) {
        if(listener != null)
            listener.onClick(view);
    }

    @NonNull
    @Override
    public MiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemproductosboton, null, false);
        view.setOnClickListener( this);
        return new MiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MiViewHolder holder, int position) {
        holder.nombreProducto.setText(listaProductos.get(position).getNombre());
        holder.precioProducto.setText((String.valueOf(listaProductos.get(position).getPrecio())));
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public class MiViewHolder extends RecyclerView.ViewHolder {
        private final TextView nombreProducto;
        private final TextView precioProducto;
        final Button botonProducto;

        @SuppressLint("NotifyDataSetChanged")
        public MiViewHolder(View itemView) {
            super(itemView);
            nombreProducto = itemView.findViewById(R.id.textViewNombreProducto1);
            precioProducto = itemView.findViewById(R.id.textViewPrecioProducto1);
            botonProducto = itemView.findViewById(R.id.botonBorrar1);

            botonProducto.setOnClickListener(view -> {
                //basedatos.eliminarProducto(getAdapterPosition());
                listaProductos.remove(getAdapterPosition());
                notifyDataSetChanged();
            });
        }
    }
}
