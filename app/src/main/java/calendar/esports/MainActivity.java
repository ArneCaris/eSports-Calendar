package calendar.esports;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        //fragments
        final HomeFragment homeFragment = new HomeFragment();
        final HubFragment hubFragment = new HubFragment();
        final CalendarFragment calendarFragment = new CalendarFragment();

        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
                int id = menuItem.getItemId();
                if (id == R.id.home){
                    setFragment(homeFragment);
                    return true;
                } else if (id == R.id.directory){
                    setFragment(hubFragment);
                    return true;
                } else if (id == R.id.calendar) {
                    setFragment(calendarFragment);
                    return true;
                }

                return false;
        });
    }
    //transactions for moving between fragments
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
    }
}
