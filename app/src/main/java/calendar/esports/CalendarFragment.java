package calendar.esports;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class CalendarFragment extends Fragment {

    CompactCalendarView compactCalendar;
    TextView eventsView;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM - YYYY", Locale.getDefault());
    Event eventsViewText = null;
    Context context;

    public CalendarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        context = container.getContext();
        compactCalendar = (CompactCalendarView) view.findViewById(R.id.calendar_view);
        eventsView = (TextView) view.findViewById(R.id.events_view);
        compactCalendar.setUseThreeLetterAbbreviation(true);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);


        Set<String> set = pref.getStringSet("key", null);
        Set<String> setInfo = pref.getStringSet("key2", null);
        Log.d("SETT", "onCreateView: " + set);
//        long time = pref.getLong("time", 0);
//        String info = pref.getString("info", "");

        Log.d("SHAREDPREF", "wow: " + pref.getString("info", ""));

        List<String> listTime = new ArrayList<>(set);
        List<String> listInfo = new ArrayList<>(setInfo);
        for (int i = 1; i <= set.size(); i++) {
            Long date = Long.valueOf(listTime.get(i-1));
            String info = listInfo.get(i-1);
            Event event1 = new Event(Color.RED, date, info);

            compactCalendar.addEvent(event1);
        }




        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                eventsView.setText("");
                List<Event> events = compactCalendar.getEvents(dateClicked);
                Log.d("EVENT", "Day was clicked: " + dateClicked + " with events " + events);
                Pattern pattern = Pattern.compile("data=([^}]*)");
                String myString = events.toString();
                Matcher matcher = pattern.matcher(myString);

                while (matcher.find()) {
                    eventsView.append(matcher.group(1) + "\n");
                }
                if (eventsView.getText().toString().matches("")) {
                    eventsView.setText("No events scheduled for this day!");
                }


            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {

            }
        });
        return view;
    }


}
