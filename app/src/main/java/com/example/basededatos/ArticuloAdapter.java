package com.example.basededatos;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

public class ArticuloAdapter extends RecyclerView.Adapter<ArticuloAdapter.ViewHolder> {

    private List<Articulo> lista;

    public ArticuloAdapter(List<Articulo> lista) {
        this.lista = lista;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_articulo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Articulo a = lista.get(position);
        holder.tvCodigo.setText("Código: " + a.getCodigo());
        holder.tvDescripcion.setText(a.getDescripcion());
        holder.tvPrecio.setText(String.format("S/ %.2f", a.getPrecio()));
        // Íconos según categoría
        if (a.getDescripcion().toLowerCase().contains("mesa")) {
            holder.imgIcono.setImageResource(R.drawable.ic_mesa);
        } else if (a.getDescripcion().toLowerCase().contains("lampara")) {
            holder.imgIcono.setImageResource(R.drawable.ic_lampara);
        } else {
            holder.imgIcono.setImageResource(R.drawable.ic_producto);
        }
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCodigo, tvDescripcion, tvPrecio;
        ImageView imgIcono;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCodigo = itemView.findViewById(R.id.txtCodigo);
            tvDescripcion = itemView.findViewById(R.id.txtDescripcion);
            tvPrecio = itemView.findViewById(R.id.txtPrecio);
            imgIcono = itemView.findViewById(R.id.imagenProducto);
        }
    }
}
