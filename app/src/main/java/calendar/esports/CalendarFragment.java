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

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static android.support.constraint.Constraints.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class CalendarFragment extends Fragment {

    ArrayList<String> listTime = new ArrayList<>();
    ArrayList<String> listInfo = new ArrayList<>();

    CompactCalendarView compactCalendar;
    TextView headerTxt;
    TextView eventsView;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM - YYYY", Locale.ENGLISH);
    Event eventsViewText = null;
    Context context;

    public CalendarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        context = container.getContext();
        compactCalendar = (CompactCalendarView) view.findViewById(R.id.calendar_view);
        eventsView = (TextView) view.findViewById(R.id.events_view);
        compactCalendar.setUseThreeLetterAbbreviation(true);
        headerTxt = (TextView) view.findViewById(R.id.header_text);
        headerTxt.setText(dateFormatMonth.format(compactCalendar.getFirstDayOfCurrentMonth()));

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);


        Set<String> setTime = pref.getStringSet("key", null);
        Set<String> setInfo = pref.getStringSet("key2", null);

        if (setTime != null && setInfo != null) {
            Log.d("SETTwow", "timeSet: " + PreferenceManager.getDefaultSharedPreferences(context));
            Log.d("SETTwow", "infoSet: " + setInfo);

            List<String> listTimeDummy = new ArrayList<>(setTime);
            List<String> listInfoDummy = new ArrayList<>(setInfo);

            Log.d("SETTwow", "timeDummy: " + listTimeDummy);
            Log.d("SETTwow", "infoDummy: " + listInfoDummy);

            for (int y = 1; y <= listTimeDummy.size(); y++) {

                listTime.add(listTimeDummy.get(y-1));
                listInfo.add(listInfoDummy.get(y-1));

                Log.d("LOOPCHECK", "onCreateView: " + listInfo.get(y-1));

                Long date = Long.valueOf(listTime.get(y-1));
                String info = listInfo.get(y-1);

                Event event1 = new Event(Color.RED, date, info);
                compactCalendar.addEvent(event1);

            }
        }

        headerTxt.setOnClickListener((View) -> {
            compactCalendar.removeAllEvents();
            //pref.edit().remove().apply();

        });


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
                headerTxt.setText(dateFormatMonth.format(firstDayOfNewMonth));
                Log.d(TAG, "Month was scrolled to: " + firstDayOfNewMonth);
            }
        });
        return view;
    }


}
