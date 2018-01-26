package com.example.dm2.sqlite;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
public class MySQLiteHelper extends SQLiteOpenHelper {
    //Sentencia SQL para crear la tabla de Usuarios
    String sqlCreate =
            "CREATE TABLE Libro (isbn INTEGER PRIMARY KEY, titulo TEXT, autor TEXT)";
    public MySQLiteHelper(Context contexto, String nombre,
                                CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Se ejecuta la sentencia SQL de creación de la tabla
        //db.execSQL("DROP TABLE IF EXISTS Libro");
        db.execSQL(sqlCreate);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
 /*NOTA: Por simplicidad del ejemplo aquí utilizamos directamente
 la opción de eliminar la tabla anterior y crearla de nuevo vacia
 con el nuevo formato.
 Sin embargo lo normal será que haya que migrar datos de la
 tabla antigua a la nueva, por lo que este método deberia
 ser más elaborado.
 */
        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS Libro");
        //Se crea la nueva version de la tabla
        db.execSQL(sqlCreate);
    }
}

