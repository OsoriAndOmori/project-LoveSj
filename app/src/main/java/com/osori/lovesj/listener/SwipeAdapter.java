package com.osori.lovesj.listener;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

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
            case 0:
                return new OsoriHusbandFragment();
            case 1:
                return new OsoriFragment();
            case 2:
                return new OsoriWifeFragment();
            default:
                return new OsoriFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
