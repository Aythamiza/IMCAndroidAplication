package com.example.friki.imc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    //metodo para el boton calcular imc
    public void CalcularIMC(View view) {
        Intent calimc = new Intent(this, CalcularImcActivity.class);
        startActivity(calimc);


    }

    public void Verdatos(View view) {
        Intent calimc = new Intent(this, VerDatosGuarActivity.class);
        startActivity(calimc);


    }


}


