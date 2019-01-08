package com.example.friki.imc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalcularImcActivity extends AppCompatActivity {
    private EditText alturaText;
    private EditText edadText;
    private EditText pesoText;
    private RadioButton mujerButton;
    private RadioButton hombreButton;
    private String sexo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitycalcularimc);
        alturaText = (EditText) findViewById(R.id.altutext);
        edadText = (EditText) findViewById(R.id.EdadEditText);
        pesoText = (EditText) findViewById(R.id.PesoEditText);
        mujerButton = (RadioButton) findViewById(R.id.MujerRadioButton);
        hombreButton = (RadioButton) findViewById(R.id.HombreRadioButton);
    }

    //metodo para el boton calcular
    public void ResultadoIMC(View view) {
        try {
            Intent intent = new Intent(this, ResultadoImcActivity.class);
            Bundle extras = new Bundle();
            Double resultadoIMC = 22.02;
            Double pesoIdeal = 230.98;
            Double peso = Double.parseDouble(pesoText.getText().toString());
            Double altura = Double.parseDouble(alturaText.getText().toString());
            Double alturacm = altura / 100;
            BigDecimal resultadoIMCFinal = null;


            if (mujerButton.isChecked() == true) {
                resultadoIMC = (peso / (alturacm * alturacm));
                BigDecimal formatNumber = new BigDecimal(resultadoIMC);
                resultadoIMCFinal = formatNumber.setScale(2, RoundingMode.DOWN);
                pesoIdeal = (45.5 + 0.67 * (altura - 152.4));
                sexo = "Mujer";

            }
            if (hombreButton.isChecked() == true) {
                resultadoIMC = ((peso / (alturacm * alturacm)));
                // esto sirve para recortar a 2 decimales el resultado de imc
                BigDecimal formatNumber = new BigDecimal(resultadoIMC);
                resultadoIMCFinal = formatNumber.setScale(2, RoundingMode.DOWN);
                pesoIdeal = (50 + 0.75 + (altura - 152.4));
                sexo = "Hombre";
            }

            extras.putString("alturaDato", alturaText.getText().toString());
            extras.putString("edadDato", edadText.getText().toString());
            extras.putString("pesoDato", pesoText.getText().toString());
            extras.putString("resultadoImc", resultadoIMCFinal.toString());
            extras.putString("sexoFinal", sexo);
            extras.putString("pesoIdeal", pesoIdeal.toString());
            intent.putExtras(extras);

            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Campos vacios", Toast.LENGTH_LONG).show();
        }
    }


}
