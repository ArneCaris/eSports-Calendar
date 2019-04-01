package calendar.esports.Instructions;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import calendar.esports.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Slide1Fragment extends Fragment {



    public Slide1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_slide1, container, false);



        ImageView img = (ImageView) v.findViewById(R.id.img1);

        return v;
    }



}
