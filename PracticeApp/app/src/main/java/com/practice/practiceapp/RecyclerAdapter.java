package com.practice.practiceapp;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.CardViewHolder> {

    ArrayList<CardView_Item> items;
    CardViewHolder pvh;
    AdapterCommunicate listener;

    public RecyclerAdapter(ArrayList<CardView_Item> items){
        this.items = items;
    }

    public RecyclerAdapter(AdapterCommunicate listener, ArrayList<CardView_Item> items) {
        this.listener = listener;
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_item, viewGroup, false);
        pvh = new CardViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(CardViewHolder cardViewHolder, int i) {
        cardViewHolder.title.setText("# "+ items.get(i).title);
        cardViewHolder.writer.setText("@ " + items.get(i).writer);
        cardViewHolder.date.setText(items.get(i).date);
        cardViewHolder.from.setText(items.get(i).from);
        cardViewHolder.url = items.get(i).url;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView title;
        TextView writer;
        TextView date;
        TextView from;
        ImageView profileImage;
        String url;

        CardViewHolder(View itemView) {
            super(itemView);

            cv = (CardView)itemView.findViewById(R.id.cardview);
            title = (TextView)itemView.findViewById(R.id.card_title);
            writer = (TextView)itemView.findViewById(R.id.card_writer);
            date = (TextView)itemView.findViewById(R.id.card_date);
            from = (TextView)itemView.findViewById(R.id.card_from);
            profileImage = (ImageView)itemView.findViewById(R.id.card_profile_img);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    url += " " + getAdapterPosition();
                    listener.callback(url);
                }
            });
        }
    }

}