package br.ulbra.appdehamburgueria;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Hamburgueria.db";
    public static final String TABLE_USERS = "usuarios";
    public static final String TABLE_PRODUCTS = "produtos";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_USERS + " (id_usuario INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, email TEXT UNIQUE, senha TEXT, telefone TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public boolean inserirUsuario(String nome, String email, String senha, String telefone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", nome);
        contentValues.put("email", email);
        contentValues.put("senha", senha);
        contentValues.put("telefone", telefone);
        long result = db.insert(TABLE_USERS, null, contentValues);
        return result != -1;
    }

    public Cursor buscarUsuario(String email, String senha) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE email = ? AND senha = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email.trim(), senha.trim()});
        return cursor;
    }
}
