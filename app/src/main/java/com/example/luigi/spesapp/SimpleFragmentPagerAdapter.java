package com.example.luigi.spesapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.luigi.spesapp.fragmentTutorial.Constants;
import com.example.luigi.spesapp.fragmentTutorial.TutorialFragmentStep1;
import com.example.luigi.spesapp.fragmentTutorial.TutorialFragmentStep2;
import com.example.luigi.spesapp.fragmentTutorial.TutorialFragmentStep3;


/**
 * Created by Simone Cimoli on 28/02/18.
 */

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;

    public SimpleFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }


    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        Bundle args = new Bundle();

        if (position == 0) {

            fragment = new TutorialFragmentStep1();
            args.putString(Constants.FRAGMENT_TEXT, "Fragment 1");
        } else if (position == 1){
            fragment = new TutorialFragmentStep2();
            args.putString(Constants.FRAGMENT_TEXT, "Fragment 2");
        } else if (position == 2){
            fragment = new TutorialFragmentStep3();
            args.putString(Constants.FRAGMENT_TEXT, "Fragment 3");
        }

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }



}
