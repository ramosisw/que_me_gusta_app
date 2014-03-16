package com.wayproyect.que_me_gusta.vistas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.wayproyect.que_me_gusta.R;
import com.wayproyect.que_me_gusta.entidades.QueMeGusta;
import com.wayproyect.que_me_gusta.enumeradores.Tipo;
import com.wayproyect.que_me_gusta.persistencia.QueMeGustaBD;

/**
 * Created with IntelliJ IDEA.
 * User: ADMIN
 * Date: 17/10/13
 * Time: 09:40 AM
 * To change this template use File | Settings | File Templates.
 */
public class Agregar extends Activity {

    private Spinner tipo;
    private Button btn_agregar;
    private EditText txt_nombre;
    private RadioButton si_me_gusta;
    private RadioButton no_me_gusta;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregarnuevo);
        init();
    }

    private void init() {
        this.tipo = (Spinner) findViewById(R.id.tipo);
        this.tipo.setAdapter(new ArrayAdapter<Tipo>(this, android.R.layout.simple_spinner_item, Tipo.values()));
        this.txt_nombre = (EditText) findViewById(R.id.txt_nombre);
        this.si_me_gusta = (RadioButton) findViewById(R.id.si_me_gusta);
        this.no_me_gusta = (RadioButton) findViewById(R.id.no_me_gusta);
        this.btn_agregar = (Button) findViewById(R.id.btnGuardar);
        this.btn_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_agregarOnClick(view);

            }
        });

    }

    private void btn_agregarOnClick(View view) {
        try {
            QueMeGusta queMeGusta = new QueMeGusta();
            queMeGusta.setNombre(txt_nombre.getText().toString());
            queMeGusta.setMeGusta((si_me_gusta.isChecked() ? (no_me_gusta.isChecked() ? false : true) : false));
            queMeGusta.setTipo((Tipo) tipo.getSelectedItem());
            /*Toast.makeText(getBaseContext(),
                    "Tipo: " + queMeGusta.getTipo() +
                            "\nTipo: " + queMeGusta.getTipo().getValor() +
                            "\nNombre: " + queMeGusta.getNombre() +
                            "\nMeGusta: " + queMeGusta.isMeGusta(),
                    Toast.LENGTH_LONG).show();
             */

            QueMeGustaBD queMeGustaBD = new QueMeGustaBD(this, "QueMeGustaBD", null);
            queMeGustaBD.guardar(queMeGusta);
            Toast.makeText(getBaseContext(),
                    "Se Guardo Correctamente =)",
                    Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, Menu.class);
            startActivity(i);

        } catch (Exception e) {

        }
    }
}