package com.osori.lovesj.listener;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.osori.lovesj.fragment.OsoriFragment;
import com.osori.lovesj.fragment.OsoriHusbandFragment;
import com.osori.lovesj.fragment.OsoriWifeFragment;

public class SwipeAdapter extends FragmentStatePagerAdapter {
    public SwipeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
//            case 0:
//                return new OsoriFragment();
            case 0:
                return new OsoriHusbandFragment();
            case 1:
                return new OsoriWifeFragment();
            default:
                return new OsoriHusbandFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
