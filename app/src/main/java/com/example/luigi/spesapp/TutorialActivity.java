package com.example.luigi.spesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.luigi.spesapp.fragmentTutorial.ChangePageFragment;


public class TutorialActivity extends AppCompatActivity implements ChangePageFragment /*interfaccia*/{

    ViewPager viewPager;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutorial_activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        SimpleFragmentPagerAdapter adapter = new SimpleFragmentPagerAdapter(this,getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        changhePage(position);


    }


    @Override
    public void changhePage(int position) {
        viewPager.setCurrentItem(position);
    }

}
