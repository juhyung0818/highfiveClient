package com.practice.practiceapp;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class hf_PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public hf_PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                HighfiveGet_tab hfGet = new HighfiveGet_tab();
                return hfGet;
            case 1:
                HighfiveGive_tab hfGive = new HighfiveGive_tab();
                return hfGive;
            case 2:
                HighfiveDone_tab hfDone = new HighfiveDone_tab();
                return hfDone;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}