package com.wayproyect.que_me_gusta.persistencia;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import com.wayproyect.que_me_gusta.entidades.QueMeGusta;
import com.wayproyect.que_me_gusta.enumeradores.Tipo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ADMIN
 * Date: 14/10/13
 * Time: 06:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class QueMeGustaBD extends SQLiteOpenHelper {

    private static int version = 12;

    public QueMeGustaBD(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE tbl_que_me_gusta (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT, " +
                "tipo INTEGER," +
                " meGusta BOOLEAN" +
                ");");
        //CREATE Aderezos , 1
        sqLiteDatabase.execSQL("INSERT INTO tbl_que_me_gusta  VALUES (null,'Queso Amarillo', 0, 1);");
        //CREATE Carne Procesada, 2
        sqLiteDatabase.execSQL("INSERT INTO tbl_que_me_gusta  VALUES (null,'Jamon', 1, 1);");
        sqLiteDatabase.execSQL("INSERT INTO tbl_que_me_gusta  VALUES (null,'Pastel', 1, 1);");
        sqLiteDatabase.execSQL("INSERT INTO tbl_que_me_gusta  VALUES (null,'Salchicha', 1, 1);");
        sqLiteDatabase.execSQL("INSERT INTO tbl_que_me_gusta  VALUES (null,'Winis', 1, 1);");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tbl_que_me_gusta;");
        onCreate(sqLiteDatabase);
    }

    public List<QueMeGusta> getItems(boolean meGusta, String group) {
        List<QueMeGusta> noMeGusta = new ArrayList<QueMeGusta>();
        SQLiteDatabase baseDatos = this.getReadableDatabase();
        int tipo = this.getValorTipo(group);
        Cursor cursor = baseDatos.rawQuery("SELECT * FROM tbl_que_me_gusta WHERE tipo=" + tipo + " AND meGusta=" + (meGusta ? 1 : 0), null);
        if (cursor.moveToFirst()) {
            do {
                QueMeGusta MeGusta = new QueMeGusta();
                MeGusta.setId(cursor.getInt(0));
                MeGusta.setNombre(cursor.getString(1));
                MeGusta.setTipo(Tipo.values()[cursor.getInt(2)]);
                MeGusta.setMeGusta(cursor.getInt(3) == 1 ? true : false);
                noMeGusta.add(MeGusta);
            } while (cursor.moveToNext());
        }
        return noMeGusta;
    }

    private int getValorTipo(String item) {
        for (Tipo i : Tipo.values()) {
            if (i.getNombre().equals(item)) {
                return i.getValor();
            }
        }
        return 0;
    }

    public void guardar(QueMeGusta queMeGusta) {
        SQLiteDatabase baseDatos = this.getWritableDatabase();
        SQLiteStatement statement = baseDatos.compileStatement("INSERT INTO tbl_que_me_gusta VALUES(?,?,?,?);");
        statement.bindNull(1);  //Manual _id.
        statement.bindString(2, queMeGusta.getNombre());
        statement.bindLong(3, queMeGusta.getTipo().getValor());
        statement.bindLong(4, queMeGusta.isMeGusta() ? 1 : 0);
        statement.executeInsert();
    }
}
