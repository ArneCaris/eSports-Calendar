package calendar.esports.Instructions;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import calendar.esports.R;

public class SlideActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_slide);

        BottomNavigationView introNav = findViewById(R.id.slide_nav);


        final Slide1Fragment slide1Fragment = new Slide1Fragment();
        final Slide2Fragment slide2Fragment = new Slide2Fragment();
        final Slide3Fragment slide3Fragment = new Slide3Fragment();
        final Slide4Fragment slide4Fragment = new Slide4Fragment();

        introNav.setOnNavigationItemSelectedListener(menuItem -> {
            int i = menuItem.getItemId();
            if (i == R.id.slide1) {
                setFragment(slide1Fragment);
                return true;
            } else if (i == R.id.slide2) {
                setFragment(slide2Fragment);
                return true;
            } else if (i == R.id.slide3) {
                setFragment(slide3Fragment);
                return true;
            } else if (i == R.id.slide4) {
                setFragment(slide4Fragment);
                return true;
            }
            return false;
        });


    }
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.introFrame, fragment);
        fragmentTransaction.commit();
    }
}
