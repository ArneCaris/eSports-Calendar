package calendar.esports;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
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
    ArrayList<String> listTimeSP = new ArrayList<>();
    ArrayList<String> listInfoSP = new ArrayList<>();


    CompactCalendarView compactCalendar;
    TextView headerTxt;
    TextView eventsView;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM - YYYY", Locale.ENGLISH);
    Event eventsViewText = null;
    Context context;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            listTime = savedInstanceState.getStringArrayList("time");
            listInfo = savedInstanceState.getStringArrayList("info");
        }
    }

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
        SharedPreferences.Editor editor = pref.edit();

        Set<String> setTime = pref.getStringSet("key", null);
        Set<String> setInfo = pref.getStringSet("key2", null);
        Set<String> setTimeAfter = pref.getStringSet("timeAfter", null);
        Set<String> setInfoAfter = pref.getStringSet("infoAfter", null);

        if (setTime != null && setInfo != null){// && setTimeAfter != null && setInfoAfter != null) {
            Log.d("SETTwow", "timeSet: " + pref.getStringSet("timeAfter", null));
            Log.d("SETTwow", "infoSet: " + pref.getStringSet("infoAfter", null));

            ArrayList<String> listTimeDummy = new ArrayList<>(setTime);
            ArrayList<String> listInfoDummy = new ArrayList<>(setInfo);
            //ArrayList<String> listTimeSPDummy = new ArrayList<>(setTimeAfter);
            //ArrayList<String> listInfoSPDummy = new ArrayList<>(setInfoAfter);


            Log.d("SETTwow", "timeDummy: " + listTimeDummy);
            Log.d("SETTwow", "infoDummy: " + listInfoDummy);

            for (int y = 0; y < setTime.size(); y++) {
                if (!listTime.contains(listTimeDummy.get(y))) {
                    listTime.add(listTimeDummy.get(y));
                    listInfo.add(listInfoDummy.get(y));
                }
            }

//            for (int z = 0; z < setTimeAfter.size(); z++) {
//                if (!listTimeSP.contains(listTimeSPDummy.get(z)) && !listTimeSP.contains(listTimeDummy.get(z))) {
//                    listTimeSP.add(listTimeSPDummy.get(z));
//                    listInfoSP.add(listInfoSPDummy.get(z));
//                }
//            }
            for (int x = 0; x < listTimeSP.size(); x++) {

                Long dateSP = Long.valueOf(listTimeSP.get(x));
                String infoSP = listInfoSP.get(x);

                Event event2 = new Event(Color.BLUE, dateSP, infoSP);
                compactCalendar.addEvent(event2);
            }

            for (int i = 0; i < listTime.size(); i++) {

                Long date = Long.valueOf(listTime.get(i));
                String info = listInfo.get(i);

                Event event1 = new Event(Color.RED, date, info);
                compactCalendar.addEvent(event1);
            }
            
            Set<String> setTimeSP = new HashSet<>(listTime);
            Set<String> setInfoSP = new HashSet<>(listInfo);
            editor.putStringSet("timeAfter", setTimeSP);
            editor.putStringSet("infoAfter", setInfoSP);
            editor.commit();
        }


       headerTxt.setOnClickListener((View) -> {
           editor.clear();
           editor.commit();
           eventsView.setText("No events scheduled for this day!");
           compactCalendar.removeAllEvents();
       });


        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                eventsView.setText("");
                List<Event> events = compactCalendar.getEvents(dateClicked);
                Log.d("EVENTGAMECLICK", "Day was clicked: " + dateClicked + " with events " + events);
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

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("time", listTime );
        outState.putStringArrayList("info", listInfo);
    }



}
