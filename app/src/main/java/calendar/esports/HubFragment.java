package calendar.esports;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class HubFragment extends Fragment {


    public HubFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //CardView cardView = (CardView) getView().findViewById(R.id.card1);
        
        return inflater.inflate(R.layout.fragment_hub, container, false);
    }

    /*
    void moveToAct(){
        Intent newIntent = new Intent(getActivity(), PlaceholderActivity.class);
        startActivity(newIntent);
        ((Activity) getActivity()).overridePendingTransition(0,0);
    }
    */

}
