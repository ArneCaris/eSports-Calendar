package calendar.esports;


import android.app.ActionBar;
import android.app.usage.UsageEvents;
import android.content.Context;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;
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


    CompactCalendarView compactCalendar;
    TextView eventsView;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM - YYYY", Locale.getDefault());
    Event eventsViewText = null;

    public CalendarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Long time = null;
        if (getArguments() != null) {
            time = getArguments().getLong("time");
        }
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        compactCalendar = (CompactCalendarView) view.findViewById(R.id.calendar_view);
        eventsView = (TextView) view.findViewById(R.id.events_view);
        compactCalendar.setUseThreeLetterAbbreviation(true);

        Event ev1 = new Event(Color.RED, 1553842800000L, "test event");
        compactCalendar.addEvent(ev1);
        Event ev2 = new Event(Color.RED, 1553846400000L, "wow double event");
        compactCalendar.addEvent(ev2);
        Event ev3 = new Event(Color.RED, 1553932800000L, "oh boi its a triple");
        compactCalendar.addEvent(ev3);
        if (time != null) {
            Event ev4 = new Event(Color.RED, time, "Auto added event with its time");
            compactCalendar.addEvent(ev4);
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
