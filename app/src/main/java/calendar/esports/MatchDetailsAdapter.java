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

    private Team  mteam1;
    private Team  mteam2;
    private Context context;

    public MatchDetailsAdapter(Context context, Team team1, Team team2) {
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

        Player[] playersTeam1  = mteam1.getPlayers();
        Player[] playersTeam2  = mteam2.getPlayers();
        if(playersTeam1.length > 0){
            holder.player1.setText(playersTeam1[i].getName().toString() );
        }

        if(playersTeam2.length > 0){
            holder.player2.setText(playersTeam2[i].getName().toString());
        }

    }

    @Override
    public int getItemCount() {
        if(mteam1.getPlayers().length > mteam2.getPlayers().length){
            return mteam2.getPlayers().length;
        } else {
            return mteam1.getPlayers().length;

        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView  player1;
        TextView  player2;

        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parentLayout      = itemView.findViewById(R.id.parent_layout);
            player1           = itemView.findViewById(R.id.player1);
            player2           = itemView.findViewById(R.id.player2);

        }
    }
}
