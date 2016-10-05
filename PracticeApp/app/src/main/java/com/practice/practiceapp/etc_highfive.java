package com.practice.practiceapp;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class etc_highfive extends AppCompatActivity {

    ViewPager hf_viewPager;
    hf_PagerAdapter hf_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etc_highfive);

        Toolbar hf_toolbar = (Toolbar) findViewById(R.id.hf_toolbar);
        setSupportActionBar(hf_toolbar);
        final TextView hf_toolbar_title = (TextView) findViewById(R.id.hf_toolbar_title);

        // 탭 구현
        TabLayout hf_tabLayout = (TabLayout) findViewById(R.id.hf_tab_layout);
        hf_tabLayout.addTab(hf_tabLayout.newTab().setText("받음"));
        hf_tabLayout.addTab(hf_tabLayout.newTab().setText("요청"));
        hf_tabLayout.addTab(hf_tabLayout.newTab().setText("완료"));
        hf_tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        hf_viewPager = (ViewPager) findViewById(R.id.hf_pager);
        hf_adapter = new hf_PagerAdapter
                (getSupportFragmentManager(), hf_tabLayout.getTabCount());
        hf_viewPager.setAdapter(hf_adapter);
        hf_viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(hf_tabLayout));
        hf_tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                hf_viewPager.setCurrentItem(tab.getPosition());
                switch (tab.getPosition()) {
                    case 0:
                        hf_toolbar_title.setText("받음");
                        break;
                    case 1:
                        hf_toolbar_title.setText("요청");
                        break;
                    case 2:
                        hf_toolbar_title.setText("완료");
                        break;
                    case 3:
                        hf_toolbar_title.setText("기타");
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
