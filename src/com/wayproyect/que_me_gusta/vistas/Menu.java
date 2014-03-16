package com.wayproyect.que_me_gusta.vistas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.wayproyect.que_me_gusta.R;


/**
 * Created with IntelliJ IDEA.
 * User: ADMIN
 * Date: 12/10/13
 * Time: 12:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class Menu extends Activity {
    private Button btn_si;
    private Button btn_no;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        init();

    }

    private void init() {
        this.btn_si = (Button) findViewById(R.id.btn_si);
        this.btn_si.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_siOnClick(view);
            }
        });
        this.btn_no = (Button) findViewById(R.id.btn_no);
        this.btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_noOnClick(view);
            }
        });
    }

    private void btn_noOnClick(View view) {
        Intent i = new Intent(this, QueNo.class);
        startActivity(i);
    }

    private void btn_siOnClick(View view) {
        Intent i = new Intent(this, QueSi.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        boolean elemento = super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.menuAgregar) {
            Intent intent = new Intent(this, Agregar.class);
            startActivity(intent);
            elemento = true;
        }
        return elemento;

    }
}