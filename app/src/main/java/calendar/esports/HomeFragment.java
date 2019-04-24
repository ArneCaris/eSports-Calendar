package calendar.esports;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;
import com.synnapps.carouselview.ViewListener;

import calendar.esports.MainActivity;
import calendar.esports.Model.Points;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    private int[] mImages = new int[] {
        R.drawable.placeholder, R.drawable.teo_wide, R.drawable.estream
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);

        CarouselView carouselView = (CarouselView) v.findViewById(R.id.carouselView);
        carouselView.setPageCount(mImages.length);
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(mImages[position]);
            }
        });


        carouselView.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                String csgoNews = "https://www.hltv.org/";
                String owNews = "https://playoverwatch.com/en-us/news";
                switch (position) {
                    case 0:
                        Intent i = new Intent(getActivity(), NewsActivity.class);
                        startActivity(i);
                        break;
                    case 1:
                        Uri uri = Uri.parse(csgoNews);
                        Intent ii = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(ii);
                        break;
                    case 2:
                        Uri uri1 = Uri.parse(owNews);
                        Intent in = new Intent(Intent.ACTION_VIEW, uri1);
                        startActivity(in);
                        break;
                }



                /*if (position == 0) {
                    Intent i = new Intent(getActivity(), NewsActivity.class);
                    startActivity(i);
                }*/
            }
        });



        return v;
    }
}
