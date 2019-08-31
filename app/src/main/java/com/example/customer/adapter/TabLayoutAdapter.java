package com.example.customer.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TabLayoutAdapter extends FragmentPagerAdapter {
    private final Context context;
    private final ArrayList<Fragment> fragmentList;
    private List<String> list_Title;

    public TabLayoutAdapter(FragmentManager fragmentManager, Context context, ArrayList<Fragment> fragmentList, List<String> list_title) {
        super(fragmentManager);
        this.context = context;
        this.fragmentList = fragmentList;
        this.list_Title = list_title;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return list_Title.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return list_Title.get(position);
    }
}
