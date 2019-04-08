package calendar.esports;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.Serializable;
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

        String oldTimeString = new SimpleDateFormat("E  h:mm a").format(matches[position].getBegin_at());
        holder.matchBegin.setText(oldTimeString);
        holder.matchTitle.setText(matches[position].getName());

        holder.matchTitle.setOnClickListener(View -> {

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

                    SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
                    SharedPreferences.Editor editor = pref.edit();

                    editor.putLong("time", timeInMilliseconds);
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

        });


    }


    @Override
    public int getItemCount() {
        return matches.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView matchBegin;
        TextView matchTitle;
        RelativeLayout parentLayout;
        ImageView notificationIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            matchBegin       = itemView.findViewById(R.id.textView_match_time);
            matchTitle       = itemView.findViewById(R.id.textView_match_title);
            parentLayout     = itemView.findViewById(R.id.parent_layout);
            notificationIcon = itemView.findViewById(R.id.notification_icon);
        }
    }

}
