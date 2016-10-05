package com.practice.practiceapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class etc_board_mine extends AppCompatActivity {

    ArrayList<BoardItem> items_mine;
    BoardAdapter boardAdapter_mine;
    ListView lv_mine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etc_board_mine);

        lv_mine = (ListView) findViewById(R.id.lv_mine);
        items_mine = new ArrayList<BoardItem>();
        BoardItem[] itemarray = new BoardItem[10];
        for(int i=0; i<10; i++) {
            itemarray[i] = new BoardItem();
            items_mine.add(itemarray[i]);
        }
        boardAdapter_mine = new BoardAdapter(getApplication(), items_mine);

        lv_mine.setAdapter(boardAdapter_mine);

    }

    @Override
    protected void onStart() {
        super.onStart();

        lv_mine.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        lv_mine.setOnScrollListener(new EndlessScrollListener(10) {
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
            items_mine.add(itemarray[i]);
        }
        boardAdapter_mine.notifyDataSetChanged();
    }
}
