package com.app.jiwon.tekken7_manual.Adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.app.jiwon.tekken7_manual.Fragment.ComboFragment;
import com.app.jiwon.tekken7_manual.Fragment.FrameFragment;
import com.app.jiwon.tekken7_manual.Fragment.ManualFragment;
import com.app.jiwon.tekken7_manual.Fragment.ProfileFragment;

import java.util.ArrayList;

public class CharacterViewPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Fragment> fragmentList = new ArrayList<>();

    public CharacterViewPagerAdapter(FragmentManager fm, String characterName) {
        super(fm);

        fragmentList.add(FrameFragment.newInstance(characterName));
        fragmentList.add(ProfileFragment.newInstance(characterName));
        fragmentList.add(ManualFragment.newInstance());
        fragmentList.add(ComboFragment.newInstance(characterName));
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
