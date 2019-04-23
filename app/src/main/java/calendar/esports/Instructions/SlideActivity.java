package calendar.esports.Instructions;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import java.util.ArrayList;
import java.util.List;

import calendar.esports.MainActivity;
import calendar.esports.R;

public class SlideActivity extends FragmentActivity {

    MyPageAdapter pageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_slide);

        List<Fragment> fragments = getFragments();
        pageAdapter = new MyPageAdapter(getSupportFragmentManager(), fragments);
        ViewPager pager = (ViewPager) findViewById(R.id.view_pager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabDots);
        tabLayout.setupWithViewPager(pager, true);

        pager.setAdapter(pageAdapter);


        //Shows the instructions only once
        /*SharedPreferences preferences = getSharedPreferences("ActivityPref", Context.MODE_PRIVATE);
        if (preferences.getBoolean("activity_executed", false)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            SharedPreferences.Editor ed = preferences.edit();
            ed.putBoolean("activity_executed", true);
            ed.commit();
        }*/



        Button btn = (Button) findViewById(R.id.skip_btn);
        btn.setOnClickListener((View) -> {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        });

    }
    private  List<Fragment> getFragments(){
        List<Fragment> fList = new ArrayList<>();
        fList.add(new Slide1Fragment());
        fList.add(new Slide2Fragment());
        fList.add(new Slide3Fragment());
        fList.add(new Slide4Fragment());
        return fList;
    }

    private class MyPageAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragments;

        public MyPageAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }
        @Override
        public Fragment getItem(int position) {
            return this.fragments.get(position);
        }
        @Override
        public int getCount() {
            return this.fragments.size();
        }

    }

}
