package calendar.esports;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import calendar.esports.Instructions.SlideActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);

        TextView kek = (TextView) v.findViewById(R.id.newsBtn);
        kek.setOnClickListener((View) ->{
            Intent i = new Intent(getActivity(), NewsActivity.class);
            startActivity(i);
        });


        Button tb = (Button) v.findViewById(R.id.testBtn);
        tb.setOnClickListener((View) -> {
            Intent b = new Intent(getActivity(), CreditsActivity.class);
            startActivity(b);
        });


        return v;
    }

}
