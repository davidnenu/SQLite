package com.example.dm2.sqlite;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView titulo;
    private TextView isbn;
    private TextView autor;
    private TextView t;

    private MySQLiteHelper usdbh;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titulo=findViewById(R.id.titulo);
        autor=findViewById(R.id.autor);
        isbn=findViewById(R.id.isbn);
        t=findViewById(R.id.lista);

        //Abrimos la base de datos "DBUsuarios" en modo de escritura
        usdbh = new MySQLiteHelper(this, "LIBROS", null, 1);
        db = usdbh.getWritableDatabase();




    }

    public void aniadir(View v){
        if (db != null) {
                if(titulo.getText().length()>0&&autor.getText().length()>0&&isbn.getText().length()>0) {
                    try {
                        db.execSQL("INSERT INTO Libro (isbn,titulo,autor) VALUES (" + isbn.getText() + " , '" + titulo.getText().toString() + "','" + autor.getText().toString() + "')");
                        Toast.makeText(MainActivity.this, "INSERTADO (" + isbn.getText() + " , " + titulo.getText().toString() + "," + autor.getText().toString() + ")", Toast.LENGTH_LONG).show();
                    } catch (SQLException ex) {

                        Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(MainActivity.this, "Falta algun dato", Toast.LENGTH_LONG).show();
                }
        }
    }
    public void borrar(View v){
        if (db != null) {
            if(isbn.getText().length()>0) {
                try {
                    db.execSQL("DELETE FROM Libro WHERE isbn=" + isbn.getText() );
                    Toast.makeText(MainActivity.this, "BORRADO " + isbn.getText(), Toast.LENGTH_LONG).show();
                } catch (SQLException ex) {

                    Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
            else{
                Toast.makeText(MainActivity.this, "Falta el isbn del libro a borrar", Toast.LENGTH_LONG).show();
            }
        }
    }
    public void salir(View v){
        db.close();
        finish();
    }
    public void modificar(View v) {
        if (db != null) {
            if(titulo.getText().length()>0&&autor.getText().length()>0&&isbn.getText().length()>0) {
                try {
                    db.execSQL("UPDATE Libro SET titulo='"+titulo.getText().toString()+"', autor='"+autor.getText().toString()+"' WHERE isbn="+isbn.getText() );
                    Toast.makeText(MainActivity.this, "MODIFICADO " + isbn.getText(), Toast.LENGTH_LONG).show();
                } catch (SQLException ex) {

                    Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
            else{
                Toast.makeText(MainActivity.this, "Falta el isbn del libro a modificar o alguno de sus datos", Toast.LENGTH_LONG).show();
            }
        }
    }
    public void listar(View v){
        if (db != null) {
            Cursor c =db.rawQuery("SELECT isbn,titulo,autor from Libro",null);
            t.setText("");

            if (c.moveToFirst()){
                //Recorremos el cursor hasta que no haya más registros.
                do {
                    int isbn = c.getInt(0);
                    String titulo =c.getString(1);
                    String autor = c.getString(2);
                    t.append (isbn +" - "+titulo + " - " +autor+"\n" );
                }while (c.moveToNext());
            }


        }
    }
}
