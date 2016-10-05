package com.practice.practiceapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HighfiveDone_tab extends Fragment {

    View view;
    ListView hf_lv_done;
    ArrayList<BoardItem> hf_items_done;
    BoardAdapter hf_boardAdapter_done;

    public HighfiveDone_tab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_highfive_done_tab, container, false);



        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        hf_lv_done = (ListView) view.findViewById(R.id.hf_lv_done);
        hf_items_done = new ArrayList<BoardItem>();
        BoardItem[] itemarray = new BoardItem[10];
        for (int i = 0; i < 10; i++) {
            itemarray[i] = new BoardItem();
            hf_items_done.add(itemarray[i]);
        }
        hf_boardAdapter_done = new BoardAdapter(getActivity(), hf_items_done);

        hf_lv_done.setAdapter(hf_boardAdapter_done);

        hf_lv_done.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        hf_lv_done.setOnScrollListener(new EndlessScrollListener(10) {
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
            hf_items_done.add(itemarray[i]);
        }
        hf_boardAdapter_done.notifyDataSetChanged();
    }
}
