package calendar.esports;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
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
        View view = inflater.inflate(R.layout.fragment_hub, container, false);


        CardView csgo = (CardView) view.findViewById(R.id.csgo);
        csgo.setOnClickListener((View) -> {
            String game = "csgo";
            Intent i = new Intent(getActivity(), MatchActivity.class);
            i.putExtra("game", game);
            startActivity(i);
        });

        CardView lol = (CardView) view.findViewById(R.id.lol);
        lol.setOnClickListener((View) -> {
            String game = "lol";
            Intent i = new Intent(getActivity(), MatchActivity.class);
            i.putExtra("game", game);
            startActivity(i);
        });

        CardView dota2 = (CardView) view.findViewById(R.id.dota2);
        dota2.setOnClickListener((View) -> {
            String game = "dota2";
            Intent i = new Intent(getActivity(), MatchActivity.class);
            i.putExtra("game", game);
            startActivity(i);
        });

        CardView ow = (CardView) view.findViewById(R.id.ow);
        ow.setOnClickListener((View ) -> {
            String game = "ow";
            Intent i = new Intent(getActivity(), MatchActivity.class);
            i.putExtra("game", game);
            startActivity(i);
        });
        return view;
    }

}
