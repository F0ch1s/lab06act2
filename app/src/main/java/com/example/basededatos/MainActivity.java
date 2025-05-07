package com.example.basededatos;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText et_codigo, et_descripcion, et_precio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_codigo = findViewById(R.id.txt_codigo);
        et_descripcion = findViewById(R.id.txt_descripcion);
        et_precio = findViewById(R.id.txt_precio);
    }

    public void Registrar(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String codigo = et_codigo.getText().toString();
        String descripcion = et_descripcion.getText().toString();
        String precio = et_precio.getText().toString();

        if (!codigo.isEmpty() && !descripcion.isEmpty() && !precio.isEmpty()) {
            Cursor cursor = db.rawQuery("SELECT codigo FROM articulos WHERE codigo = ?", new String[]{codigo});
            if (cursor.moveToFirst()) {
                Toast.makeText(this, "El código ya está registrado", Toast.LENGTH_SHORT).show();
            } else {
                ContentValues registro = new ContentValues();
                registro.put("codigo", codigo);
                registro.put("descripcion", descripcion);
                registro.put("precio", precio);

                db.insert("articulos", null, registro);
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();

                limpiarCampos();
            }
            cursor.close();
            db.close();
        } else {
            Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    public void Buscar(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase db = admin.getReadableDatabase();

        String codigo = et_codigo.getText().toString();

        if (!codigo.isEmpty()) {
            Cursor cursor = db.rawQuery("SELECT descripcion, precio FROM articulos WHERE codigo = ?", new String[]{codigo});
            if (cursor.moveToFirst()) {
                et_descripcion.setText(cursor.getString(0));
                et_precio.setText(cursor.getString(1));
            } else {
                Toast.makeText(this, "No existe el artículo", Toast.LENGTH_SHORT).show();
            }
            cursor.close();
            db.close();
        } else {
            Toast.makeText(this, "Debe introducir el código del artículo", Toast.LENGTH_SHORT).show();
        }
    }

    public void Eliminar(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String codigo = et_codigo.getText().toString();

        if (!codigo.isEmpty()) {
            int cantidad = db.delete("articulos", "codigo = ?", new String[]{codigo});
            db.close();

            if (cantidad == 1) {
                Toast.makeText(this, "Artículo eliminado exitosamente", Toast.LENGTH_SHORT).show();
                limpiarCampos();
            } else {
                Toast.makeText(this, "El artículo no existe", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Debe introducir el código del artículo", Toast.LENGTH_SHORT).show();
        }
    }

    public void Modificar(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String codigo = et_codigo.getText().toString();
        String descripcion = et_descripcion.getText().toString();
        String precio = et_precio.getText().toString();

        if (!codigo.isEmpty() && !descripcion.isEmpty() && !precio.isEmpty()) {
            ContentValues registro = new ContentValues();
            registro.put("descripcion", descripcion);
            registro.put("precio", precio);

            int cantidad = db.update("articulos", registro, "codigo = ?", new String[]{codigo});
            db.close();

            if (cantidad == 1) {
                Toast.makeText(this, "Artículo modificado correctamente", Toast.LENGTH_SHORT).show();
                limpiarCampos();
            } else {
                Toast.makeText(this, "El artículo no existe", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    public void verLista(View view) {
        Intent intent = new Intent(this, ListaArticulosActivity.class);
        startActivity(intent);
    }

    // Limpia los campos
    private void limpiarCampos() {
        et_codigo.setText("");
        et_descripcion.setText("");
        et_precio.setText("");
    }
}
