package com.practice.practiceapp;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import java.util.ArrayList;

public class etc_tab extends Fragment {

    Home home;
    Bundle inBundle;
    View view;
    GridView gv_etc;
    ArrayList<MenuItemEtc> menuItemEtcArrayList;
    MenuItemEtc[] menuItemEtc;
    GridViewAdapter gridViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_etc, container, false);
        gv_etc = (GridView) view.findViewById(R.id.my_gridView);
        menuItemEtcArrayList = new ArrayList<MenuItemEtc>();

        menuItemEtc = new MenuItemEtc[6];
        Resources menu_names = getResources();
        String[] menu_names_str = menu_names.getStringArray(R.array.menu_item_etc);
        for(int i=0; i<6; i++) {
            menuItemEtc[i] = new MenuItemEtc(menu_names_str[i], "@drawable/profile_gray");
            menuItemEtcArrayList.add(menuItemEtc[i]);
        }
        gridViewAdapter = new GridViewAdapter(getActivity(), menuItemEtcArrayList);
        gv_etc.setAdapter(gridViewAdapter);

        //------------------------------------
        home = (Home) getActivity();
        inBundle = home.getIntent().getExtras(); // Home에 저장되어있던 프로필 받아옴
        String name = inBundle.get("NAME").toString();
        String surname = inBundle.get("SURNAME").toString();
        TextView txt = (TextView) view.findViewById(R.id.my_name);
        txt.setText(surname + name);

        // 로그아웃 버튼 구현
        Button logout_btn = (Button) view.findViewById(R.id.logout);
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout(home.loginFrom);
                home.finish();
            }
        });
        //------------------------------------

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        gv_etc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                switch(position) {
                    case 0:
                        Intent editProfile = new Intent(getContext(), etc_edit_profile.class);
                        editProfile.putExtras(inBundle);
                        startActivity(editProfile);
                        break;
                    case 1:
                        Intent highfive = new Intent(getContext(), etc_highfive.class);
                        highfive.putExtras(inBundle);
                        startActivity(highfive);
                        break;
                    case 2:
                        Intent settings = new Intent(getContext(), etc_settings.class);
                        settings.putExtras(inBundle);
                        startActivity(settings);
                        break;
                    case 3:
                        Intent replied = new Intent(getContext(), etc_board_replied.class);
                        replied.putExtras(inBundle);
                        startActivity(replied);
                        break;
                    case 4:
                        Intent mine = new Intent(getContext(), etc_board_mine.class);
                        mine.putExtras(inBundle);
                        startActivity(mine);
                        break;
                    case 5:
                        Intent liked = new Intent(getContext(), etc_board_liked.class);
                        liked.putExtras(inBundle);
                        startActivity(liked);
                        break;
                }
            }
        });
    }

    public void logout(String loginFrom) {
        if(loginFrom.equals("FACEBOOK")) {
            Toast tst4 = Toast.makeText(getActivity(), "FB Logout", Toast.LENGTH_SHORT);
            tst4.show();
            LoginManager.getInstance().logOut();
        }
        else if(loginFrom.equals("GOOGLE")){
            Toast tst3 = Toast.makeText(getActivity(), "Google Logout", Toast.LENGTH_SHORT);
            tst3.show();
            home.googleSignOut(new ResultCallback<Status>() {
                @Override
                public void onResult(@NonNull Status status) {
                    Toast tst2 = Toast.makeText(getActivity(), "status : "+ status.getStatusMessage(), Toast.LENGTH_SHORT);
                    tst2.show();
                }
            });
        }
    }
}
