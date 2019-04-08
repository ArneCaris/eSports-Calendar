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
    private Context context;

    public MatchDetailsAdapter( Context context, Match matchDetail) {
        this.matchDetail = matchDetail;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.match_details_row, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {
        System.out.print(matchDetail);
//
        Opponents[] opponents = matchDetail.getOpponents();
        Team team  = opponents[i].getOpponent();

        holder.teamName.setText(team.getName().toString());
        Picasso.get().load(team.getImage_url().toString()).into(holder.teamImage);

        Result[] results = matchDetail.getResults();
        List<Result> listOfResults = Arrays.asList(results);
        Collections.reverse(listOfResults);
        Result[] reversed = listOfResults.toArray(results);
        holder.teamResult.setText(reversed[0].getScore().toString());
        holder.teamResult.setText(reversed[reversed.length - 1].getScore().toString());
    }

    @Override
    public int getItemCount() {
        Opponents[] opponents = matchDetail.getOpponents();
        return opponents.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView teamName;
        TextView teamResult;
        RelativeLayout parentLayout;
        ImageView teamImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            teamName         = itemView.findViewById(R.id.textView_team_name);
            teamResult       = itemView.findViewById(R.id.textView_team_result);
            parentLayout     = itemView.findViewById(R.id.parent_layout);
            teamImage        = itemView.findViewById(R.id.imageView_team_img);
        }
    }
}
