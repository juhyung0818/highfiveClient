package com.practice.practiceapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class etc_board_liked extends AppCompatActivity {

    ArrayList<BoardItem> items_liked;
    BoardAdapter boardAdapter_liked;
    ListView lv_liked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etc_board_liked);

        lv_liked = (ListView) findViewById(R.id.lv_liked);
        items_liked = new ArrayList<BoardItem>();
        BoardItem[] itemarray = new BoardItem[10];
        for(int i=0; i<10; i++) {
            itemarray[i] = new BoardItem();
            items_liked.add(itemarray[i]);
        }
        boardAdapter_liked = new BoardAdapter(getApplication(), items_liked);

        lv_liked.setAdapter(boardAdapter_liked);

    }

    @Override
    protected void onStart() {
        super.onStart();

        lv_liked.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent openPost = new Intent(getApplication(), board_post.class);
                startActivity(openPost);
            }
        });

        lv_liked.setOnScrollListener(new EndlessScrollListener(10) {
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
            items_liked.add(itemarray[i]);
        }
        boardAdapter_liked.notifyDataSetChanged();
    }
}
