package com.crud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static int versao = 1;
    private static String nomeDB = "CrudMB.db";

    String[] sql = {
            "CREATE TABLE Locador(locador TEXT PRIMARY KEY, livro TEXT);"
    };

    public DBHelper(@Nullable Context context) {
        super(context, nomeDB, null, versao);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for (int i = 0; i < sql.length; i++) {
            db.execSQL(sql[i]);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        versao++;
        db.execSQL("DROP TABLE IF EXISTS Locador");
        onCreate(db);
    }

    public long Insert_Locador(String locador, String livro) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("locador", locador);
        values.put("livro", livro);
        return db.insert("Locador", null, values);

    }

    public long Update_Locador(String locador, String livro) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("livro", livro);
        return db.update("Locador", values, "locador=?", new String[]{locador});
    }

    public long Delete_Locador(String locador) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete("Locador", "locador=?", new String[]{locador});
    }
    public Cursor SelectAll_Locador(){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM Locador", null);
    }

    public Cursor SelectByLocador(String locador){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM Locador  WHERE locador=?", new String[]{locador});
    }

}

