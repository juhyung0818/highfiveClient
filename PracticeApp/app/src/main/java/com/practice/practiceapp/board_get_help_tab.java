package com.practice.practiceapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class board_get_help_tab extends Fragment implements AdapterCommunicate {

    Home home;

    public View view;
    private Button write_btn_get;

    RecyclerView recyclerView_get;
    LinearLayoutManager layoutManager;
    RecyclerAdapter recyclerAdapter_get;

    ArrayList<CardView_Item> items;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // 프레그먼트는 setContentView가 아니라 inflater로 레이아웃을 inflate 하고
        // inflater를 이용해 반환되는 view를 onCreateView에서 return 해주면 화면에 보여진다.

        home = (Home) getActivity();

        view = inflater.inflate(R.layout.fragment_board_get_help, container, false);

        recyclerView_get = (RecyclerView)view.findViewById(R.id.recyclerview_get);
        layoutManager = new LinearLayoutManager(getActivity());
//        recyclerView_get.setHasFixedSize(true);
        recyclerView_get.setLayoutManager(layoutManager);

        items = new ArrayList<CardView_Item>();
        CardView_Item[] item=new CardView_Item[10];
        for(int i=0;i<10;i++) {
            item[i]=new CardView_Item();
            item[i].initialize();
            items.add(item[i]);
        }

        recyclerView_get.setAdapter(new RecyclerAdapter(this, items));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) { // You can now change UI
        super.onActivityCreated(savedInstanceState);

        // 글쓰기 버튼, 리스너 등록
        write_btn_get = (Button) view.findViewById(R.id.write_btn_get);
        write_btn_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent write_page = new Intent(getActivity(), board_write.class);
                write_page.putExtras(home.getIntent().getExtras());
                startActivity(write_page);
            }
        });
    }

    @Override
    public void callback() {

    }

    @Override
    public void callback(String url) {
        Toast tst = Toast.makeText(getActivity(), url, Toast.LENGTH_SHORT);
        tst.show();
    }
}