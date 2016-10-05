package com.practice.practiceapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class etc_board_replied extends AppCompatActivity {

    ArrayList<BoardItem> items_replied;
    BoardAdapter boardAdapter_replied;
    ListView lv_replied;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etc_board_replied);

        lv_replied = (ListView) findViewById(R.id.lv_replied);
        items_replied = new ArrayList<BoardItem>();
        BoardItem[] itemarray = new BoardItem[10];
        for(int i=0; i<10; i++) {
            itemarray[i] = new BoardItem();
            items_replied.add(itemarray[i]);
        }
        boardAdapter_replied = new BoardAdapter(getApplication(), items_replied);

        lv_replied.setAdapter(boardAdapter_replied);

    }

    @Override
    protected void onStart() {
        super.onStart();

        lv_replied.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        lv_replied.setOnScrollListener(new EndlessScrollListener(10) {
            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {
                if(page < 5) {
                    customLoadMoreData(page);
                    return true;
                }
                else {
                    return false;
                }
            }
        });
    }

    public void customLoadMoreData(int offset) {
        BoardItem[] itemarray = new BoardItem[10];
        for(int i=0; i<10; i++) {
            itemarray[i] = new BoardItem();
            items_replied.add(itemarray[i]);
        }
        boardAdapter_replied.notifyDataSetChanged();
    }
}
