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
import com.squareup.picasso.Transformation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

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
        final int radius = 5;
        final int margin = 5;
        final Transformation transformation = new RoundedCornersTransformation(radius, margin);

        Player[] playersTeam1  = mteam1.getPlayers();
        Player[] playersTeam2  = mteam2.getPlayers();
        if(playersTeam1.length > 0){
            holder.playerName1.setText(playersTeam1[i].getName().toString() );
            if(playersTeam1[i].getRole() != null)
                holder.playerRole1.setText(playersTeam1[i].getRole().toString());
            if(playersTeam1[i].getImage_url() != null)
                Picasso.get().load(playersTeam1[i].getImage_url().toString()).transform(transformation).transform(new CropCircleTransformation()).into(holder.playerImg1);
        }

        if(playersTeam2.length > 0){
            holder.playerName2.setText(playersTeam2[i].getName().toString());
            if(playersTeam2[i].getRole() != null)
                holder.playerRole2.setText(playersTeam2[i].getRole().toString());
            if(playersTeam2[i].getImage_url() != null)
                Picasso.get().load(playersTeam2[i].getImage_url().toString()).transform(transformation).transform(new CropCircleTransformation()).into(holder.playerImg2);
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

        TextView  playerName1;
        TextView  playerName2;
        TextView  playerRole1;
        TextView  playerRole2;
        ImageView playerImg1;
        ImageView playerImg2;

        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parentLayout          = itemView.findViewById(R.id.parent_layout);
            playerName1           = itemView.findViewById(R.id.playerName1);
            playerName2           = itemView.findViewById(R.id.playerName2);
            playerRole1           = itemView.findViewById(R.id.playerRole1);
            playerRole2           = itemView.findViewById(R.id.playerRole2);
            playerImg1            = itemView.findViewById(R.id.playerImg1);
            playerImg2            = itemView.findViewById(R.id.playerImg2);

        }
    }
}
