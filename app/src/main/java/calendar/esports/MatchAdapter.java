package calendar.esports;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
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
import java.util.Date;

import static android.app.PendingIntent.getActivity;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.ViewHolder> {

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
        if(opponents.length > 0){

            Team team1  = opponents[0].getOpponent();
            holder.matchTeam1.setText(team1.getName());

            if(team1.getImage_url() != null) {
                Picasso.get().load(team1.getImage_url().toString()).into(holder.matchTeam1Img);
            }

            if(opponents.length == 2){
                Team team2  = opponents[1].getOpponent();
                holder.matchTeam2.setText(team2.getName());

                if(team2.getImage_url() != null) {
                    Picasso.get().load(team2.getImage_url().toString()).into(holder.matchTeam2Img);
                }
            }
        }


        holder.teamGroup.setOnClickListener(View -> {
                Toast.makeText(context, (CharSequence) matchInfo, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, MatchDetails.class);
                intent.putExtra("MatchDetail", matches[position]);
                context.startActivity(intent);

        });

        holder.notificationIcon.setOnClickListener(new View.OnClickListener() {
            int notificationPos = 0;
            public void onClick(View view) {

                if(notificationPos == 0){
                    holder.notificationIcon.setImageResource(R.drawable.ic_notifications_active_black_24dp);
                    notificationPos = 1;

                    Toast.makeText(context, (CharSequence) matches[position].getBegin_at()
                            .toString(), Toast.LENGTH_SHORT).show();
                }

                else if (notificationPos == 1){
                    holder.notificationIcon.setImageResource(R.drawable.ic_notifications_black_24dp);
                    notificationPos = 0;
                }

                //Function to add event to the calendar (with bundle? or args? or import calendar?)
                String time = matches[position].getBegin_at().toString();
                Log.d("MATCHTIME", "onClick: " + time);
                SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
                try {
                    Date mDate = sdf.parse(time);
                    long timeInMilliseconds = mDate.getTime();
                    Log.d("MATCHTIME", "onClick: " + timeInMilliseconds);

//                    Bundle args = new Bundle();
//                    args.putLong("time", timeInMilliseconds);
//                    CalendarFragment fragobj = new CalendarFragment();
//                    fragobj.setArguments(args);
//
//                    FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
//                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                    fragmentTransaction.replace(R.id.header_text, fragobj);
//                    fragmentTransaction.addToBackStack(null);
//                    fragmentTransaction.commit();

                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        });

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
