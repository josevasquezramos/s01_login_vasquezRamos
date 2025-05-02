package com.example.s01_login_vasquezramos.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.s01_login_vasquezramos.db.DatabaseHelper;
import com.example.s01_login_vasquezramos.model.Alumno;

public class AlumnoRepository {

    private DatabaseHelper dbHelper;

    public AlumnoRepository(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public boolean registrarAlumno(Alumno alumno) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("codigo", alumno.getCodigo());
        values.put("dni", alumno.getDni());
        values.put("clave", alumno.getClave());
        long result = db.insert(DatabaseHelper.TABLE_ALUMNOS, null, values);
        db.close();
        return result != -1;
    }

    public boolean validarCredenciales(String codigo, String dni, String clave) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + DatabaseHelper.TABLE_ALUMNOS +
                " WHERE codigo = ? AND dni = ? AND clave = ?";
        Cursor cursor = db.rawQuery(query, new String[]{codigo, dni, clave});
        boolean valido = cursor.moveToFirst();
        cursor.close();
        db.close();
        return valido;
    }
}
