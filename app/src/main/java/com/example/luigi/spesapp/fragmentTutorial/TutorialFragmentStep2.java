package com.example.luigi.spesapp.fragmentTutorial;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.luigi.spesapp.R;



public class TutorialFragmentStep2 extends Fragment {

    private String myText;
    private ImageView indicatorImageView;
    ChangePageFragment changePageFragment;

    public TutorialFragmentStep2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tutorial_layout_step, container, false);
        Button next=(Button) rootView.findViewById(R.id.next);
        changePageFragment = (ChangePageFragment) getActivity();
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePageFragment.changhePage(2);
            }
        });
        TextView description = (TextView) rootView.findViewById(R.id.step_description);
        description.setText(getString(R.string.attributi));

        ImageView imageLogo = (ImageView) rootView.findViewById(R.id.logo);
        imageLogo.setImageResource(R.drawable.step0);

        ImageView indicator = (ImageView) rootView.findViewById(R.id.indicator1);
        indicator.setImageResource(R.drawable.two);

        return rootView;

    }

}
