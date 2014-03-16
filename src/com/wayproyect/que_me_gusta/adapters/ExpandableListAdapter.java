package com.wayproyect.que_me_gusta.adapters;

import java.util.List;
import java.util.Map;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.wayproyect.que_me_gusta.R;
import com.wayproyect.que_me_gusta.entidades.QueMeGusta;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Activity context;
    private Map<String, List<QueMeGusta>> Collections;
    private List<String> items;
    private boolean meGusta;

    public ExpandableListAdapter(Activity context, List<String> items, Map<String, List<QueMeGusta>> Collections, boolean meGusta) {
        this.context = context;
        this.Collections = Collections;
        this.items = items;
        this.meGusta = meGusta;
    }

    public QueMeGusta getChild(int groupPosition, int childPosition) {
        return Collections.get(items.get(groupPosition)).get(childPosition);
    }

    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String chield = getChild(groupPosition, childPosition).getNombre();
        LayoutInflater inflater = context.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.child_item, null);
        }

        TextView item = (TextView) convertView.findViewById(R.id.item);

        ImageView delete = (ImageView) convertView.findViewById(R.id.delete);
        delete.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Do you want to remove?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                List<QueMeGusta> child = Collections.get(items.get(groupPosition));
                                child.remove(childPosition);
                                notifyDataSetChanged();
                            }
                        });
                builder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        item.setText(chield);
        return convertView;
    }

    public int getChildrenCount(int groupPosition) {
        return Collections.get(items.get(groupPosition)).size();
    }

    public Object getGroup(int groupPosition) {
        return items.get(groupPosition);
    }

    public int getGroupCount() {
        return items.size();
    }

    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String laptopName = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.group_item, null);
        }
        TextView item = (TextView) convertView.findViewById(R.id.items);
        item.setTypeface(null, Typeface.BOLD);
        item.setText(laptopName);
        return convertView;
    }

    public boolean hasStableIds() {
        return true;
    }

    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}