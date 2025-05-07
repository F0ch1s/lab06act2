package com.example.basededatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    // Constructor
    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name,
                                 @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase BaseDeDatos) {
        // Crear la tabla 'articulos'
        BaseDeDatos.execSQL("CREATE TABLE articulos (" +
                "codigo INTEGER PRIMARY KEY, " +
                "descripcion TEXT, " +
                "precio REAL)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase BaseDeDatos, int oldVersion, int newVersion) {
        // Elimina la tabla si existe y la vuelve a crear
        BaseDeDatos.execSQL("DROP TABLE IF EXISTS articulos");
        onCreate(BaseDeDatos);
    }
}
