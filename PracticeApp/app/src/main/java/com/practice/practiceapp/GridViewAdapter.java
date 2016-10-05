package com.practice.practiceapp;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class GridViewAdapter extends ArrayAdapter<MenuItemEtc>{

    private Context context;
    private ArrayList<MenuItemEtc> menuItemEtcArrayList;

    //constructor, call on creation
    public GridViewAdapter(Context context, ArrayList<MenuItemEtc> objects) {
        super(context, 0, objects);

        this.context = context;
        this.menuItemEtcArrayList = objects;
    }

    //called when rendering the list
    public View getView(int position, View convertView, ViewGroup parent) {

        GridViewHolder holder; // Holder Pattern -> 부하 방지

        //get the property we are displaying
        MenuItemEtc curMenuItem = menuItemEtcArrayList.get(position);

        //get the inflater and inflate the XML layout for each item
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.menu_item_etc, null);
            holder = new GridViewHolder();
            holder.menu_icon_etc = (ImageView) convertView.findViewById(R.id.menu_icon_etc);
            holder.menu_name_etc = (TextView) convertView.findViewById(R.id.menu_name_etc);

            convertView.setTag(holder);
        }
        else {
            holder = (GridViewHolder) convertView.getTag();
        }


        //set price and rental attributes
        holder.menu_name_etc.setText(String.valueOf(curMenuItem.getMenu_name()));

//        get the image associated with this property

        return convertView;
    }

}
