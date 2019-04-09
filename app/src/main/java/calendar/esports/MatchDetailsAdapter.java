package calendar.esports;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.lang.System.in;

public class MatchDetailsAdapter extends RecyclerView.Adapter<MatchDetailsAdapter.ViewHolder> {

    private Match matchDetail;
    private Team  mteam1;
    private Team  mteam2;
    private Context context;

    public MatchDetailsAdapter(Context context, Match matchDetail, Team team1, Team team2) {
        this.matchDetail = matchDetail;
        this.context = context;
        this.mteam1 = team1;
        this.mteam2 = team2;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.match_details_row, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {

        Opponents[] opponents = matchDetail.getOpponents();

        if(opponents.length > 0){

            Team team1  = opponents[0].getOpponent();
            holder.team1Name.setText(mteam1.getName());

            if(team1.getImage_url() != null) {
                Picasso.get().load(mteam1.getImage_url().toString()).into(holder.team1Image);
            }

            if(opponents.length == 2){
                Team team2  = opponents[1].getOpponent();
                holder.team2Name.setText(mteam2.getName());

                if(team2.getImage_url() != null) {
                    Picasso.get().load(mteam2.getImage_url().toString()).into(holder.team2Image);
                }
            }

            Player[] playersTeam1  = mteam1.getPlayers();
            holder.player1.setText(playersTeam1[i].getName().toString() );

//            Result[] results = matchDetail.getResults();
//            List<Result> listOfResults = Arrays.asList(results);
//            Collections.reverse(listOfResults);
//            Result[] reversed = listOfResults.toArray(results);
//            holder.team1Result.setText(reversed[reversed.length - 1].getScore().toString());
//            holder.team2Result.setText(reversed[0].getScore().toString());
        }

    }

    @Override
    public int getItemCount() {
        Opponents[] opponents = matchDetail.getOpponents();
        return opponents.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView  team1Name;
        TextView  team1Result;
        ImageView team1Image;
        TextView  team2Name;
        TextView  team2Result;
        ImageView team2Image;

        TextView  player1;

        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            team1Name         = itemView.findViewById(R.id.textView_team1_name);
//            team1Result       = itemView.findViewById(R.id.textView_team1_result);
            team1Image        = itemView.findViewById(R.id.imageView_team1_img);

            team2Name         = itemView.findViewById(R.id.textView_team2_name);
//            team2Result       = itemView.findViewById(R.id.textView_team2_result);
            team2Image        = itemView.findViewById(R.id.imageView_team2_img);

            parentLayout      = itemView.findViewById(R.id.parent_layout);

            player1           = itemView.findViewById(R.id.player1);
        }
    }
}
