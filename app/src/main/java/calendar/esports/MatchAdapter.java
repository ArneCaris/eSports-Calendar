package calendar.esports;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.ViewHolder> {

    Set<String> set = new HashSet<>();
    Set<String> setInfo = new HashSet<>();
    private Match[] matches;
    private Context context;

    public MatchAdapter(Context context, Match[] matches) {
        this.matches = matches;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.match_row, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        String matchInfo = matches[position].getName() + ": " + matches[position].getId().toString();

        String matchHour = new SimpleDateFormat("h:mm a").format(matches[position].getBegin_at());
        holder.matchHour.setText(matchHour);

        String matchMonth = new SimpleDateFormat("MMM").format(matches[position].getBegin_at());
        holder.matchMonth.setText(matchMonth);

        String matchDate = new SimpleDateFormat("d").format(matches[position].getBegin_at());
        holder.matchDate.setText(matchDate);

        String matchWeekDay = new SimpleDateFormat("E").format(matches[position].getBegin_at());
        holder.matchWeekDay.setText(matchWeekDay);

        String matchLeague = matches[position].getLeague().getName().toString();
        holder.matchLeague.setText(matchLeague);

        Opponents[] opponents = matches[position].getOpponents();

        displayMatch(opponents, holder, position);

        holder.teamGroup.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(context, (CharSequence) matchInfo, Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        holder.teamGroup.setOnClickListener(View -> {
                Intent intent = new Intent(context, MatchDetails.class);
                intent.putExtra("MatchDetail", matches[position]);
                context.startActivity(intent);
        });

        holder.notificationIcon.setOnClickListener(new View.OnClickListener() {

            private int notificationPos = 0;
            private static final int MY_NOTIFICATION_ID=1;
            NotificationManager notificationManager;
            Notification myNotification;

            public void onClick(View view) {

                if(notificationPos == 0){
                    holder.notificationIcon.setImageResource(R.drawable.ic_notifications_active_black_24dp);
                    notificationPos = 1;


                    Toast.makeText(context, (CharSequence) matches[position].getBegin_at()
                            .toString(), Toast.LENGTH_SHORT).show();

                    String timeOfEvent = new SimpleDateFormat("dd-MM-yyyy hh:mm a", Locale.getDefault()).format(matches[position].getBegin_at());

                    String message = ("You've set a notification for " + matches[position].getName() + "\n" + "Match starts at: "
                                        + timeOfEvent);

                    myNotification = new NotificationCompat.Builder(context)
                            .setContentTitle(matches[position].getLeague().getName().toString())
                            .setContentText(message)
                            .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                            .setTicker("Notification!")
//                            .setWhen(System.currentTimeMillis())
//                            .setDefaults(Notification.DEFAULT_SOUND)
//                            .setAutoCancel(true)
                            .setSmallIcon(R.drawable.ic_notifications_active_black_24dp)
                            .build();

                    notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
                    notificationManager.notify(MY_NOTIFICATION_ID, myNotification);

//
//                  notify();
//



                }

                else if (notificationPos == 1){
                    holder.notificationIcon.setImageResource(R.drawable.ic_notifications_black_24dp);
                    notificationPos = 0;
                }

                //Function to add event to the calendar (with bundle? or args? or import calendar?)

                String time = matches[position].getBegin_at().toString();
                SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
                ArrayList<String> times = new ArrayList<>();
                try {
                    Date date = sdf.parse(time);
                    Long timeInMilliseconds = date.getTime();
                    Log.d("MATCHTIME", "onClick: " + timeInMilliseconds);

                    SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
                    SharedPreferences.Editor editor = pref.edit();

                    if (opponents.length > 0) {
                        Team team1 = opponents[0].getOpponent();
                        if (opponents.length == 2) {
                            Team team2 = opponents[1].getOpponent();
                            String info = team1.getName() + " VS " + team2.getName() + " - " + matchHour;
                            setInfo.add(info);
                        } else {
                            if (team1.getName() != null) {
                                String info = team1.getName() + " VS TBA" + " - " + matchHour;
                                setInfo.add(info);
                            } else {
                                Team team2 = opponents[1].getOpponent();
                                String info = "TBA VS " + team2.getName() + " - " + matchHour;
                                setInfo.add(info);
                            }
                        }
                    } else {
                        String info = "TBA VS TBA - " + matchHour;
                        setInfo.add(info);
                    }

                    set.add(timeInMilliseconds.toString());
                    editor.putStringSet("key2", setInfo);
                    editor.putStringSet("key", set);
                    editor.commit();

//                    FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
//                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                    fragmentTransaction.add(R.id.calendar_layout, fragobj);
//                    fragmentTransaction.replace(R.id.calendar_view, fragobj);
//                    fragmentTransaction.commit();

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            private void notifyMatch(Context context, Match[] matches) {
//                final int MY_NOTIFICATION_ID=1;
//                NotificationManager notificationManager;
//                Notification myNotification;
//
//
//                myNotification = new NotificationCompat.Builder(context)
//                        .setContentTitle(matches[position].getLeague().getName().toString())
//                        .setContentText("Match starts")
//                        .setTicker("Notification!")
//                        .setWhen(System.currentTimeMillis())
//                        .setSmallIcon(R.drawable.lol)
//                        .build();
//
//                notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
//                notificationManager.notify(MY_NOTIFICATION_ID, myNotification);

//                EditText text = findViewById(R.id.time);
//                int i = Integer.parseInt(text.getText().toString());
//                Intent intent = new Intent(this, MyBroadcastReceiver.class);
//                PendingIntent pendingIntent = PendingIntent.getBroadcast(
//                        this.getApplicationContext(), 234324243, intent, 0);
//                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//                alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
//                        + (i * 1000), pendingIntent);
//                Toast.makeText(this, "Alarm set in " + i + " seconds",Toast.LENGTH_LONG).show();
            }
        });

    }

    private void displayMatch(Opponents[] opponents, ViewHolder holder, final int position) {
        if(opponents.length > 0){

            Team team1  = opponents[0].getOpponent();
//            holder.matchTeam1.setText(team1.getName());

            if (team1.getName() != null) {
                holder.matchTeam1.setText(team1.getName());
            } else {
                holder.matchTeam1.setText("TBD");
            }

            if(team1.getImage_url() != null) {
                Picasso.get().load(team1.getImage_url().toString()).into(holder.matchTeam1Img);
            } else {
                holder.matchTeam1Img.setAlpha(0);
            }

            if(opponents.length == 2){
                Team team2  = opponents[1].getOpponent();

                if (team2.getName() != null) {
                    holder.matchTeam2.setText(team2.getName());
                } else {
                    holder.matchTeam2.setText("TBD");
                }

                if(team2.getImage_url() != null) {
                    Picasso.get().load(team2.getImage_url().toString()).into(holder.matchTeam2Img);
                } else {
                    holder.matchTeam2Img.setAlpha(0);
                }
            }
        }
    }


    @Override
    public int getItemCount() {
        return matches.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView matchMonth;
        TextView matchDate;
        TextView matchWeekDay;

        TextView  matchLeague;
        ImageView matchTeam1Img;
        ImageView matchTeam2Img;
        TextView  matchTeam1;
        TextView  matchTeam2;

        RelativeLayout parentLayout;
        RelativeLayout timeGroup;
        RelativeLayout teamGroup;
        RelativeLayout notificationGroup;

        ImageView notificationIcon;
        TextView  matchHour;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            matchMonth        = itemView.findViewById(R.id.textView_match_month);
            matchDate         = itemView.findViewById(R.id.textView_match_date);
            matchWeekDay      = itemView.findViewById(R.id.textView_match_weekday);

            matchLeague       = itemView.findViewById(R.id.textView_match_league_title);
            matchTeam1        = itemView.findViewById(R.id.textView_match_team1);
            matchTeam2        = itemView.findViewById(R.id.textView_match_team2);
            matchTeam1Img     = itemView.findViewById(R.id.imageView_team1_img);
            matchTeam2Img     = itemView.findViewById(R.id.imageView_team2_img);

            notificationIcon  = itemView.findViewById(R.id.notification_icon);
            matchHour         = itemView.findViewById(R.id.textView_match_hour);

            parentLayout      = itemView.findViewById(R.id.parent_layout);
            timeGroup         = itemView.findViewById(R.id.time_group);
            notificationGroup = itemView.findViewById(R.id.notification_group);
            teamGroup         = itemView.findViewById(R.id.team_group);

        }
    }
}
