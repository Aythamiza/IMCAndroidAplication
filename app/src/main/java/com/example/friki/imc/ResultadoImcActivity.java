package com.example.friki.imc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import OpenHelper.SQLite_OpenHelper;

public class ResultadoImcActivity extends AppCompatActivity {
    private TextView tvAltura;
    private TextView tvPeso;
    private TextView tvEdad;
    private TextView tvIMC;
    private TextView tvNombre;
    private EditText etNombre;
    private TextView tvApellido;
    private EditText etApellido;
    private Button grabarDatosButton, grabarDatosBueno;


    SQLite_OpenHelper db = new SQLite_OpenHelper(this, null, null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        setContentView(R.layout.activity_resultado_imc);
        tvNombre = (TextView) findViewById(R.id.Nombre);
        tvApellido = (TextView) findViewById(R.id.Apellidos);
        etNombre = (EditText) findViewById(R.id.NombreEdiText);
        etApellido = (EditText) findViewById(R.id.ApellidosEditText);
        grabarDatosButton = (Button) findViewById(R.id.GrabarDatosButton);
        grabarDatosBueno = (Button) findViewById(R.id.GrabarDatosBueno);


        tvAltura = (TextView) findViewById(R.id.AlturaTV);
        String alturaDato = extras.getString("alturaDato");

        tvPeso = (TextView) findViewById(R.id.PesoDatoTV);
        String pesoDato = extras.getString("pesoDato");

        tvEdad = (TextView) findViewById(R.id.edadTV);
        String edadDato = extras.getString("edadDato");

        tvIMC = (TextView) findViewById(R.id.ImcDatoTV);
        String IMCDato = extras.getString("resultadoImc");

        String SexoDato = extras.getString("SexoFinal");

        tvAltura.setText(alturaDato);
        tvPeso.setText(pesoDato);
        tvEdad.setText(edadDato);
        tvIMC.setText(IMCDato);

    }

    public void RegresarIMC(View view) {
        super.onBackPressed();

    }

    public void GrabarDatos(View view) {

        tvNombre.setVisibility(View.VISIBLE);
        tvApellido.setVisibility(View.VISIBLE);
        etNombre.setVisibility(View.VISIBLE);
        etApellido.setVisibility(View.VISIBLE);
        grabarDatosBueno.setVisibility(View.VISIBLE);
        grabarDatosButton.setVisibility(View.INVISIBLE);
    }

    public void GrabarDatosBD(View view) {
        try {
            Bundle extras = getIntent().getExtras();
            String sexoDato = extras.getString("sexoFinal");
            String pesoIdeal = extras.getString("pesoIdeal");
            String editnombre = etNombre.getText().toString();
            String editapellido = etApellido.getText().toString();

            if (!editapellido.isEmpty() && !editnombre.isEmpty()) {

                db.abrir();
                String nombreCompleto = String.valueOf(etNombre.getText()) + " " + String.valueOf(etApellido.getText());

                db.insertarregistro(String.valueOf(etNombre.getText()), String.valueOf(nombreCompleto), String.valueOf(tvEdad.getText()), String.valueOf(tvAltura.getText()),
                        String.valueOf(tvPeso.getText()), String.valueOf(tvIMC.getText()), String.valueOf(sexoDato),
                        String.valueOf(pesoIdeal));

                db.cerrar();
                Toast.makeText(getApplicationContext(), "Registro ALmacenado con exito", Toast.LENGTH_LONG).show();
            } else

                Toast.makeText(getApplicationContext(), "Campos vacios", Toast.LENGTH_LONG).show();

        } catch (Exception e) {

        }

    }
}
