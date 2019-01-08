package com.example.friki.imc;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import OpenHelper.SQLite_OpenHelper;

public class VerDatosGuarActivity extends AppCompatActivity {
    private EditText nombreET;
    private ListView listaVw;
    ArrayList<String> lista;
    ArrayAdapter adaptador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_datos_guar);
        nombreET = (EditText) findViewById(R.id.nombreET);
        listaVw = (ListView) findViewById(R.id.listaView);

        SQLite_OpenHelper helper = new SQLite_OpenHelper(getApplicationContext(), null, null, 1);
        lista = helper.llenar_li();
        adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista);
        listaVw.setAdapter(adaptador);

    }

    public void Buscar(View view) {

        SQLite_OpenHelper helper = new SQLite_OpenHelper(getApplicationContext(), null, null, 1);
        String nombre = nombreET.getText().toString();
        if (!nombre.isEmpty()) {
            lista = helper.BuscarPorNombre(nombre);
            adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista);
            listaVw.setAdapter(adaptador);

        } else {
            Toast.makeText(this, "debes introducir el Nombre que deseas buscar", Toast.LENGTH_SHORT).show();
        }


    }


}


