package com.example.s01_login_vasquezramos.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "siigaa.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_ALUMNOS = "alumnos";
    public static final String TABLE_DOCENTES = "docentes";

    private static final String CREATE_TABLE_ALUMNOS =
            "CREATE TABLE " + TABLE_ALUMNOS + " (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "codigo TEXT NOT NULL UNIQUE," +
                    "dni TEXT NOT NULL UNIQUE," +
                    "clave TEXT NOT NULL);";

    private static final String CREATE_TABLE_DOCENTES =
            "CREATE TABLE " + TABLE_DOCENTES + " (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "usuario TEXT NOT NULL UNIQUE," +
                    "tarjeta TEXT NOT NULL UNIQUE," +
                    "clave TEXT NOT NULL);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ALUMNOS);
        db.execSQL(CREATE_TABLE_DOCENTES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALUMNOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DOCENTES);
        onCreate(db);
    }
}
