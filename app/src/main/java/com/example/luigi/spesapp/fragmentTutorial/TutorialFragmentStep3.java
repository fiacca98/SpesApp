package com.example.luigi.spesapp.fragmentTutorial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.luigi.spesapp.MainActivity;
import com.example.luigi.spesapp.R;


public class TutorialFragmentStep3 extends Fragment {

    private String myText;
    private ImageView indicatorImageView;
    ChangePageFragment changePageFragment;
    public TutorialFragmentStep3() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tutorial_layout_step, container, false);
        Button skip=(Button) rootView.findViewById(R.id.skip);
        skip.setVisibility(View.INVISIBLE);
        Button next=(Button) rootView.findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        TextView description = (TextView) rootView.findViewById(R.id.step_description);
        description.setText(getString(R.string.spesa));

        ImageView imageLogo = (ImageView) rootView.findViewById(R.id.logo);
        imageLogo.setImageResource(R.drawable.step2);

        ImageView indicator = (ImageView) rootView.findViewById(R.id.indicator1);
        indicator.setImageResource(R.drawable.three);


        return rootView;

    }

    public void setIndicatorImage(int imageId){

        indicatorImageView.setImageResource(imageId);
    }
}
