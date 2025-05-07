package com.example.basededatos;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListaArticulosActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArticuloAdapter adapter;
    private List<Articulo> listaArticulos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_articulos);

        recyclerView = findViewById(R.id.recyclerArticulos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase db = admin.getReadableDatabase();

        listaArticulos = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM articulos", null);

        while (cursor.moveToNext()) {
            int codigo = cursor.getInt(0);
            String descripcion = cursor.getString(1);
            double precio = cursor.getDouble(2);

            listaArticulos.add(new Articulo(codigo, descripcion, precio));
        }

        adapter = new ArticuloAdapter(listaArticulos);
        recyclerView.setAdapter(adapter);
        cursor.close();
        db.close();

        adapter = new ArticuloAdapter(listaArticulos);
        recyclerView.setAdapter(adapter);
    }
}
