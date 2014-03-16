package com.wayproyect.que_me_gusta.vistas;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Toast;
import com.wayproyect.que_me_gusta.R;
import com.wayproyect.que_me_gusta.adapters.ExpandableListAdapter;
import com.wayproyect.que_me_gusta.entidades.QueMeGusta;
import com.wayproyect.que_me_gusta.enumeradores.Tipo;
import com.wayproyect.que_me_gusta.persistencia.QueMeGustaBD;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ADMIN
 * Date: 12/10/13
 * Time: 12:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class QueNo extends Activity {
    List<String> groupList;
    List<QueMeGusta> childList;
    Map<String, List<QueMeGusta>> que_no_me_gusta;
    ExpandableListView expListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.que_me_gusta);

        createGroupList();

        createCollection();

        expListView = (ExpandableListView) findViewById(R.id.que_me_gusta_list);
        final ExpandableListAdapter expListAdapter = new ExpandableListAdapter(this, groupList, que_no_me_gusta, false);
        expListView.setAdapter(expListAdapter);

        //setGroupIndicatorToRight();

        expListView.setOnChildClickListener(new OnChildClickListener() {
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                final QueMeGusta selected = expListAdapter.getChild(groupPosition, childPosition);
                Toast.makeText(getBaseContext(), "ID: " + selected.getId() + "\nNOMBRE:" + selected.getNombre() + "\nTIPO: " + selected.getTipo() + ",\nMeGusta:" + selected.isMeGusta(), Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }

    private void createGroupList() {
        groupList = new ArrayList<String>();
        for (Tipo i : Tipo.values()) {
            groupList.add(i.getNombre());
        }
    }

    private void createCollection() {
        // preparing laptops collection(child)
        QueMeGustaBD meGustaBD = new QueMeGustaBD(this, "QueMeGustaBD", null);
        que_no_me_gusta = new LinkedHashMap<String, List<QueMeGusta>>();
        for (String group : groupList) {
            loadChild(meGustaBD.getItems(false, group));
            que_no_me_gusta.put(group, childList);
        }
    }

    private void loadChild(List<QueMeGusta> items) {
        childList = new ArrayList<QueMeGusta>();
        if (items == null)
            return;
        for (QueMeGusta model : items)
            childList.add(model);
    }

    private void setGroupIndicatorToRight() {
        /* Get the screen width */
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;

        expListView.setIndicatorBounds(width - getDipsFromPixel(35), width
                - getDipsFromPixel(5));
    }

    // Convert pixel to dip
    public int getDipsFromPixel(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }

}