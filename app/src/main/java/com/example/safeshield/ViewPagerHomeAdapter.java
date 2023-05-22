package com.example.safeshield;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerHomeAdapter extends FragmentPagerAdapter {

    public ViewPagerHomeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return new EmegencySOSFragment();
        }
        else if(position==1){
            return new SmsFragment();
        }
        else{
            return new HospitalFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0){
            return "Emergency SOS";
        }
        else if(position==1){
            return "SMS/MMS";
        }
        else{
            return "Hospital";
        }
    }
}
