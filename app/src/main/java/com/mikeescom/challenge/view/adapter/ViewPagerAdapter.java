package com.mikeescom.challenge.view.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.mikeescom.challenge.view.fragment.AnimationFragment;
import com.mikeescom.challenge.view.fragment.GraphFragment;
import com.mikeescom.challenge.view.fragment.LocationFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private String title[] = {"One", "Two", "Three"};

    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = LocationFragment.getInstance(position);
        switch (position) {
            case 0: return LocationFragment.getInstance(position);
            case 1: return GraphFragment.getInstance(position);
            case 2: return AnimationFragment.getInstance(position);
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
