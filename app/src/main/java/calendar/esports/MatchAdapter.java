package calendar.esports;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;

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

        String matchHour = new SimpleDateFormat("h:mm a").format(matches[position].getBegin_at());
        holder.matchHour.setText(matchHour);

        String matchMonth = new SimpleDateFormat("MMM").format(matches[position].getBegin_at());
        holder.matchMonth.setText(matchMonth);

        String matchDate = new SimpleDateFormat("d").format(matches[position].getBegin_at());
        holder.matchDate.setText(matchDate);

        String matchWeekDay = new SimpleDateFormat("E").format(matches[position].getBegin_at());
        holder.matchWeekDay.setText(matchWeekDay);

//        String oldTimeString = new SimpleDateFormat("E  h:mm a").format(matches[position].getBegin_at());
//        holder.matchBegin.setText(oldTimeString);
//        holder.matchTitle.setText(matches[position].getName());
//
//        holder.matchTitle.setOnClickListener(View -> {
//
//                Toast.makeText(context, (CharSequence) matchInfo, Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(context, MatchDetails.class);
//                intent.putExtra("MatchDetail", matches[position]);
//                context.startActivity(intent);
//
//        });
//
//        holder.notificationIcon.setOnClickListener(new View.OnClickListener() {
//            int notificationPos = 0;
//            public void onClick(View view) {
//
//                if(notificationPos == 0){
//                    holder.notificationIcon.setImageResource(R.drawable.ic_notifications_active_black_24dp);
//                    notificationPos = 1;
//
//                    Toast.makeText(context, (CharSequence) matches[position].getBegin_at()
//                            .toString(), Toast.LENGTH_SHORT).show();
//                }
//
//                else if (notificationPos == 1){
//                    holder.notificationIcon.setImageResource(R.drawable.ic_notifications_black_24dp);
//                    notificationPos = 0;
//                }
//                //Function to add event to the calendar (with bundle? or args? or import calendar?)
//                String time = matches[position].getBegin_at().toString();
//                Log.d("MATCHTIME", "onClick: " + time);
//                SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
//                try {
//                    Date mDate = sdf.parse(time);
//                    long timeInMilliseconds = mDate.getTime();
//                    Log.d("MATCHTIME", "onClick: " + timeInMilliseconds);
//
////                    Bundle args = new Bundle();
////                    args.putLong("time", timeInMilliseconds);
////                    CalendarFragment fragobj = new CalendarFragment();
////                    fragobj.setArguments(args);
////
////                    FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
////                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
////                    fragmentTransaction.replace(R.id.header_text, fragobj);
////                    fragmentTransaction.addToBackStack(null);
////                    fragmentTransaction.commit();
//
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        });

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

        }
    }

}
