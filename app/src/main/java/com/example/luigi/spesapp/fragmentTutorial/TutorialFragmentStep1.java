package com.example.luigi.spesapp.fragmentTutorial;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.luigi.spesapp.R;
import com.example.luigi.spesapp.TutorialActivity;


public class TutorialFragmentStep1 extends Fragment {

    private String myText;
    private ImageView indicatorImageView;
    public TutorialFragmentStep1() {

    }

    ChangePageFragment changePageFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.tutorial_layout_step, container, false);
        changePageFragment = (ChangePageFragment) getActivity();

        Button next=(Button) rootView.findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePageFragment.changhePage(1);
            }
        });


        return rootView;

    }

}
