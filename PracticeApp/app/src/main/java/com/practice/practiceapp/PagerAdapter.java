package com.practice.practiceapp;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                main_tab MainTab = new main_tab();
                return MainTab;
            case 1:
                board_give_help_tab GiveHelp = new board_give_help_tab();
                return GiveHelp;
            case 2:
                board_get_help_tab GetHelp = new board_get_help_tab();
                return GetHelp;
            case 3:
                etc_tab EtcTab = new etc_tab();
                return EtcTab;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}