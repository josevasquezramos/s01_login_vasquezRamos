package com.example.s01_login_vasquezramos.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.s01_login_vasquezramos.db.DatabaseHelper;
import com.example.s01_login_vasquezramos.model.Docente;

public class DocenteRepository {

    private final DatabaseHelper dbHelper;

    public DocenteRepository(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public boolean registrarDocente(Docente docente) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("usuario", docente.getUsuario());
        values.put("tarjeta", docente.getTarjeta());
        values.put("clave", docente.getClave());

        long result = db.insert(DatabaseHelper.TABLE_DOCENTES, null, values);
        db.close();
        return result != -1;
    }

    public boolean validarCredenciales(String usuario, String tarjeta, String clave) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + DatabaseHelper.TABLE_DOCENTES +
                " WHERE usuario = ? AND tarjeta = ? AND clave = ?";
        Cursor cursor = db.rawQuery(query, new String[]{usuario, tarjeta, clave});
        boolean valido = cursor.moveToFirst();
        cursor.close();
        db.close();
        return valido;
    }
}
