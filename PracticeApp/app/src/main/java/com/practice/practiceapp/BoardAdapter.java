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

//custom ArrayAdapter
class BoardAdapter extends ArrayAdapter<BoardItem> {

    private Context context;
    private ArrayList<BoardItem> boardItemArrayList;

    //constructor, call on creation
    public BoardAdapter(Context context, ArrayList<BoardItem> objects) {
        super(context, 0, objects);

        this.context = context;
        this.boardItemArrayList = objects;
    }

    //called when rendering the list
    public View getView(int position, View convertView, ViewGroup parent) {

        ListViewHolder holder; // Holder Pattern -> 부하 방지
        //findViewById는 무거운 작업 -> 최소화

        //get the property we are displaying
        BoardItem curBoardItem = boardItemArrayList.get(position);

        //get the inflater and inflate the XML layout for each item
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.board_item, null);
            holder = new ListViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.board_title);
            holder.writer = (TextView) convertView.findViewById(R.id.board_writer);
            holder.content = (TextView) convertView.findViewById(R.id.board_content);
            holder.profile = (ImageView) convertView.findViewById(R.id.board_profile);

            convertView.setTag(holder);
        }
        else {
            holder = (ListViewHolder) convertView.getTag();
        }


//        display trimmed excerpt for description
        int contentLength = curBoardItem.getContent().length();
        if(contentLength >= 100){
            String contentTrim = curBoardItem.getContent().substring(0, 100) + "...";
            holder.content.setText(contentTrim);
        }else{
            holder.content.setText(String.valueOf(curBoardItem.getContent()));
        }

        //set price and rental attributes
        holder.title.setText("#" + String.valueOf(curBoardItem.getTitle()));
        holder.writer.setText(String.valueOf(curBoardItem.getWriter()));

//        get the image associated with this property

        return convertView;
    }
}