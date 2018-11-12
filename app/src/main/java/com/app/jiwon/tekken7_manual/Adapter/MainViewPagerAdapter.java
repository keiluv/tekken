package com.app.jiwon.tekken7_manual.Adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.app.jiwon.tekken7_manual.Fragment.DictionaryFragment;
import com.app.jiwon.tekken7_manual.Fragment.MoreFragment;
import com.app.jiwon.tekken7_manual.Fragment.SelectFragment;
import com.app.jiwon.tekken7_manual.Fragment.ShortCutsFragment;

import java.util.ArrayList;

public class MainViewPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Fragment> fragmentList = new ArrayList<>();

    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);

        fragmentList.add(SelectFragment.newInstance());
        fragmentList.add(DictionaryFragment.newInstance());
        fragmentList.add(ShortCutsFragment.newInstance());
        fragmentList.add(MoreFragment.newInstance());
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
