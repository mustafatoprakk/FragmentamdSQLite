package com.example.fragmenttry;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PageAdapter extends FragmentStatePagerAdapter {

    int counttab;
    public PageAdapter(@NonNull FragmentManager fm, int behaviorResumeOnlyCurrentFragment, int counttab) {
        super(fm,behaviorResumeOnlyCurrentFragment);
        this.counttab=counttab;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FirstFragment();
            case 1:
                return  new SeconfFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return counttab;
    }
}
