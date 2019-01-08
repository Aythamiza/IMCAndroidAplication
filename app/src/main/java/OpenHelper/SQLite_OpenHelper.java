package OpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.textservice.SpellCheckerInfo;
import android.widget.Toast;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class SQLite_OpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "IMC";
    private static final int SCHEMA_VERSION = 1;


    public SQLite_OpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, SCHEMA_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table datos (_ID integer primary key autoincrement, Nombre text, NombreCompleto text," +
                " Edad text, Altura text, Peso text, IMC text, Sexo text, Ideal text)";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //metodo para abrir la base datos para escribir en ella
    public void abrir() {
        this.getWritableDatabase();
    }

    public void leer() {
        this.getReadableDatabase();
    }

    // metodo para cerrar la base de datos
    public void cerrar() {
        this.close();
    }

    //metodo para escribir registros en la tabla datos

    public void insertarregistro(String nom, String nomcompleto, String edad, String altura, String peso,
                                 String IMC, String sexo, String ideal) {
        ContentValues valores = new ContentValues();
        valores.put("Nombre", nom);
        valores.put("NombreCompleto", nomcompleto);
        valores.put("Edad", edad);
        valores.put("Altura", altura);
        valores.put("Peso", peso);
        valores.put("IMC", IMC);
        valores.put("Sexo", sexo);
        valores.put("Ideal", ideal);
        this.getWritableDatabase().insert("datos", null, valores);
    }

    public ArrayList llenar_li() {
        ArrayList<String> lista = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        String q = "select * from datos";
        Cursor reg = database.rawQuery(q, null);
        if (reg.moveToFirst()) {
            do {
                lista.add(" " + "Nombre:" + " " + reg.getString(2) + " " + "Edad:" + " " + reg.getString(3) + " " + "Altura:" + " " + reg.getString(4) + " "
                        + "Peso:" + " " + reg.getString(5) + " " + "IMC:" + " " + reg.getString(6) + " " + "Sexo:" + " " + reg.getString(7) + " " + "PesoIdeal:" + " " + reg.getString(8));
            } while (reg.moveToNext());
        }
        return lista;
    }

    public ArrayList BuscarPorNombre(String nombre) {
        ArrayList<String> lista = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        String q = "select * from datos where Nombre ='" + nombre + "'";
        Cursor reg = database.rawQuery(q, null);
        if (reg.moveToFirst()) {
            do {
                lista.add(" " + "Nombre:" + " " + reg.getString(2) + " " + "Edad:" + " " + reg.getString(3) + " " + "Altura:" + " " + reg.getString(4) + " "
                        + "Peso:" + " " + reg.getString(5) + " " + "IMC:" + " " + reg.getString(6) + " " + "Sexo:" + " " + reg.getString(7) + " " + "PesoIdeal:" + " " + reg.getString(8));
            } while (reg.moveToNext());
        }
        return lista;
    }

}
