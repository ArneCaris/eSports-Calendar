package calendar.esports;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Bundle;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import static android.content.Context.ALARM_SERVICE;
import static android.support.v4.content.ContextCompat.getSystemService;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.ViewHolder> {

    Set<String> set = new HashSet<>();
    Set<String> setInfo = new HashSet<>();
    private Match[] matches;
    private Context context;
    private String games;



    public MatchAdapter(Context context, Match[] matches, String games) {
        this.matches = matches;
        this.context = context;
        this.games = games;
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

        String gameIcon = games ;

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

            private int           notificationPos = 0;
            private Intent        intent;
            private PendingIntent pendingIntent;

            public void onClick(View view) {
                intent = new Intent(context, MyBroadcastReceiver.class);
                pendingIntent = PendingIntent.getBroadcast(context.getApplicationContext(),
                        234324243, intent, 0);

            
                if(notificationPos == 0){
                    holder.notificationIcon.setImageResource(R.drawable.game_logo1);
                    notificationPos = 1;


                    notifyMatch(context, matches);

                    // notifier();
                }

                else if (notificationPos == 1){
                    holder.notificationIcon.setImageResource(R.drawable.ic_notifications_black_24dp);
                    notificationPos = 0;
                    cancelMatchNotification(context);
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

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            private void cancelMatchNotification(Context context) {
                AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
                alarmManager.cancel(pendingIntent);


                NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.cancel(AlarmNotificationService.MY_NOTIFICATION_ID);
            }

//             private void notifier(){
//                 NotificationManager notificationManager;
//                 Notification myNotification;
//                 String timeOfEvent = new SimpleDateFormat("dd-MM-yyyy hh:mm a", Locale.ENGLISH).format(matches[position].getBegin_at());
//                 String defLogo = "game_logo1" ;

//                 if (gameIcon.equals("csgo")) {
//                     defLogo = defLogo.replace("1", "2");
//                 } else if (gameIcon.equals("lol")) {
//                     defLogo = defLogo.replace("1", "5");
//                 } else if (gameIcon.equals("ow")) {
//                     defLogo = defLogo.replace("1", "4");
//                 } else if (gameIcon.equals("dota2")) {
//                     defLogo = defLogo.replace("1", "3");
//                 }


//                 int gameIdentifier = context.getResources().getIdentifier(defLogo, "drawable",
//                         context.getPackageName());

//                 String message = ("You've set a notification for " + matches[position].getName() + "\n" + "Match starts at: "
//                         + timeOfEvent);

//                 myNotification = new NotificationCompat.Builder(context)
//                         .setContentTitle(matches[position].getLeague().getName().toString())
//                         .setContentText(message)
//                         .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
//                         .setTicker("Notification!")
// //                            .setWhen(System.currentTimeMillis())
// //                            .setDefaults(Notification.DEFAULT_SOUND)
// //                            .setAutoCancel(true)
//                         .setSmallIcon(gameIdentifier)
//                         .build();

//                 notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
//                 notificationManager.notify(MY_NOTIFICATION_ID, myNotification);
//             }

            private void notifyMatch(Context context, Match[] matches) {
                long sec = getInterval( matches[position].getBegin_at().toString());
                long now = System.currentTimeMillis();
                if(now < sec) Log.d("CompareTime", "notifyMatch: Now:" + now + " < Sec: " + sec );
                AlarmManager alarmManager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, now, pendingIntent);
            }

            private long getInterval(String milis) {

                SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
                Date date = null;
                try {
                    date = sdf.parse(milis);
                    Long timeInMilliseconds = date.getTime();
                    Log.d("getMilis", "getMilis: " + timeInMilliseconds);
                    return timeInMilliseconds;
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });

    }

    private void displayMatch(Opponents[] opponents, ViewHolder holder, final int position) {
        if(opponents.length > 0){

            Team team1  = opponents[0].getOpponent();

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


